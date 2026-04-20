package org.qapower.apiclient;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import java.util.Map;

import static io.restassured.RestAssured.given;

public abstract class BaseClient {

  // Specs строятся один раз - статическая инициализация при загрузке класса
  private static final RequestSpecification REQUEST_SPEC =
      BaseRequestSpec.getRequestSpecBuilder().build();
  private static final ResponseSpecification RESPONSE_SPEC =
      BaseResponseSpec.getResponseSpecBuilder().build();

  @Step("GET {path}")
  protected Response get(String path) {
    return given().spec(REQUEST_SPEC)
        .when().get(path)
        .then().spec(RESPONSE_SPEC)
        .extract().response();
  }

  @Step("GET {path}?{paramName}={value}")
  protected Response get(String path, String paramName, Object value) {
    return given().spec(REQUEST_SPEC)
        .queryParam(paramName, value)
        .when().get(path)
        .then().spec(RESPONSE_SPEC)
        .extract().response();
  }

  @Step("GET {path} with params: {params}")
  protected Response get(String path, Map<String, Object> params) {
    return given().spec(REQUEST_SPEC)
        .queryParams(params)
        .when().get(path)
        .then().spec(RESPONSE_SPEC)
        .extract().response();
  }
}
