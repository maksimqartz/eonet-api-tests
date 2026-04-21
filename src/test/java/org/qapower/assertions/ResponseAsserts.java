package org.qapower.assertions;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

public class ResponseAsserts {

  private ResponseAsserts() {}

  @Step("Response status should be 200 OK")
  public static void assertResponseStatusOk(Response response) {
    response.then().statusCode(HttpStatus.SC_OK);
  }

  @Step("Response body should not be empty")
  public static void assertResponseBodyIsNotEmpty(Response response) {
    response.then().body("$", not(empty()));
  }
}
