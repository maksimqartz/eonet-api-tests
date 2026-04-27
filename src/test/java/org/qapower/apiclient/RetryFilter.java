package org.qapower.apiclient;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RetryFilter implements Filter {

  private static final Logger log = LoggerFactory.getLogger(RetryFilter.class);

  private static final int MAX_ATTEMPTS = 3;
  private static final long BASE_DELAY_MS = 500;
  private static final double JITTER_FACTOR = 0.2;

  // 408 Request Timeout — server dropped the connection; GET is idempotent, safe to retry
  // 429 Too Many Requests — rate-limit signal; apply normal backoff (server will recover)
  // 5xx — transient server / proxy errors
  private static final Set<Integer> RETRIABLE_STATUSES = Set.of(408, 429, 500, 502, 503, 504);

  // Static counter — one value across the entire JVM run regardless of how many
  // RetryFilter instances exist (BaseRequestSpec creates one; RetryDemoTest creates another)
  private static final AtomicInteger RETRY_COUNT = new AtomicInteger(0);

  static {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      int count = RETRY_COUNT.get();
      if (count > 0) {
        log.warn("FLAKINESS REPORT: {} HTTP retry attempt(s) made during this run", count);
      } else {
        log.info("FLAKINESS REPORT: 0 retries — all requests succeeded on first attempt");
      }
    }, "flakiness-reporter"));
  }

  // Exponential backoff: 500ms → 1000ms → 2000ms, with ±20% jitter
  // Jitter prevents synchronized retry storms when multiple tests fail simultaneously
  private static long computeDelay(int attempt) {
    long base = BASE_DELAY_MS * (1L << (attempt - 1));
    double jitter = 1.0 + JITTER_FACTOR * (Math.random() * 2 - 1);
    return (long) (base * jitter);
  }

  // Unwraps the exception cause chain — REST-assured wraps network errors
  // in its own RuntimeException, so we must walk the chain to find the root.
  private static boolean isNetworkException(Throwable t) {
    Throwable cause = t;
    while (cause != null) {
      if (cause instanceof SocketTimeoutException
          || cause instanceof SocketException) {
        return true;
      }
      cause = cause.getCause();
    }
    return false;
  }

  private static void sleep(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public Response filter(FilterableRequestSpecification requestSpec,
      FilterableResponseSpecification responseSpec,
      FilterContext ctx) {
    Exception lastException = null;

    for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
      try {
        Response response = ctx.next(requestSpec, responseSpec);
        int status = response.statusCode();

        if (!RETRIABLE_STATUSES.contains(status) || attempt == MAX_ATTEMPTS) {
          return response;  // fast path: most requests return here on attempt 1
        }

        long delay = computeDelay(attempt);
        log.warn("Retry attempt {} of {} after status {}. Waiting {}ms...",
            attempt, MAX_ATTEMPTS, status, delay);
        RETRY_COUNT.incrementAndGet();
        sleep(delay);

      } catch (Exception e) {
        if (!isNetworkException(e) || attempt == MAX_ATTEMPTS) {
          if (e instanceof RuntimeException re) throw re;
          throw new RuntimeException(e);
        }
        lastException = e;
        long delay = computeDelay(attempt);
        log.warn("Retry attempt {} of {} after exception: {}. Waiting {}ms...",
            attempt, MAX_ATTEMPTS, e.getMessage(), delay);
        RETRY_COUNT.incrementAndGet();
        sleep(delay);
      }
    }

    // Unreachable in practice — the loop always returns or rethrows on the last attempt
    // Present to satisfy the compiler's control-flow analysis
    throw new RuntimeException("All " + MAX_ATTEMPTS + " attempts failed", lastException);
  }
}