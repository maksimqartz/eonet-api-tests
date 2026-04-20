package org.qapower.assertions;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

import io.restassured.response.Response;
import org.apache.http.HttpStatus;

public class ResponseAsserts {

  private ResponseAsserts() {}

  public static void assertResponseStatusOk(Response response) {
    response.then().statusCode(HttpStatus.SC_OK);
  }

  public static void assertResponseBodyIsNotEmpty(Response response) {
    response.then().body("$", not(empty()));
  }
}
