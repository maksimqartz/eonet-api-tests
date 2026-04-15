package org.qapower.tests;

import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.qapower.apiclient.CategoriesApi;
import org.qapower.apiclient.EventsApi;

import java.util.function.Supplier;
import java.util.stream.Stream;
import org.qapower.apiclient.MagnitudesApi;
import org.qapower.apiclient.SourcesApi;
import org.qapower.dto.CategoriesResponse;
import org.qapower.dto.EventsResponse;
import org.qapower.dto.MagnitudesResponse;
import org.qapower.dto.SourcesResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.qapower.assertions.GetAsserts.assertGetResponseBodyIsNotEmpty;
import static org.qapower.assertions.GetAsserts.assertGetResponseIs200;

public class SmokeTest extends BaseTest {

  static Stream<Arguments> apiProvider() {
    return Stream.of(
        Arguments.of("Events",
            (Supplier<Response>) () -> new EventsApi().getEvents(1),
            EventsResponse.class),
        Arguments.of("Categories",
            (Supplier<Response>) () -> new CategoriesApi().getCategories(),
            CategoriesResponse.class),
        Arguments.of("Magnitudes",
            (Supplier<Response>) () -> new MagnitudesApi().getMagnitudes(),
            MagnitudesResponse.class),
        Arguments.of("Sources",
            (Supplier<Response>) () -> new SourcesApi().getSources(),
            SourcesResponse.class)
    );
  }

  @ParameterizedTest(name = "{0} should return 200, non-empty body, and valid structure")
  @MethodSource("apiProvider")
  void smokeTest(String name, Supplier<Response> request, Class<?> responseType) {
    Response response = request.get();
    assertGetResponseIs200(response);
    assertGetResponseBodyIsNotEmpty(response);
    assertNotNull(response.as(responseType), name + " deserialization returned null");
  }
}
