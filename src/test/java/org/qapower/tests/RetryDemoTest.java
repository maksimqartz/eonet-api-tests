package org.qapower.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.Test;
import org.qapower.apiclient.RetryFilter;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RetryDemoTest {

  /**
   * Demonstrates HTTP-level retry. Points at a dead port so RetryFilter
   * executes all MAX_ATTEMPTS, logs warnings, then rethrows the exception.
   * assertThrows makes the test PASS while keeping the retry logs visible.
   */
  @Test
  void retryFilter_exhaustsAttemptsOnDeadPort() {
    var spec = new RequestSpecBuilder()
        .setBaseUri("http://localhost:19999")
        .addFilter(new RetryFilter())
        .build();

    assertThrows(RuntimeException.class, () ->
        RestAssured.given()
            .spec(spec)
            .when().get("/events")
            .then().extract().response()
    );
  }
}