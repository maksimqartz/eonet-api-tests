package org.qapower.apiclient;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class BaseClient {

  protected RequestSpecification request() {
    return BaseRequestSpec.getRequestSpecBuilder().build();
  }

  protected ResponseSpecification response() {
    return BaseResponseSpec.getResponseSpecBuilder().build();
  }

  protected Response get(String path) {
    return given()
      .spec(request())
      .when()
      .get(path)
      .then()
      .spec(response())
      .extract()
      .response();
  }

  protected Response get(String path, String paramName, Object value) {
    return given()
      .spec(request())
      .queryParam(paramName, value)
      .when()
      .get(path)
      .then()
      .spec(response())
      .extract()
      .response();
  }
}
