package org.qapower.apiclient;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class EventsApi extends BaseClient {
  
  public Response getEvents(int limit) {
    return given()
        .spec(request())
        .queryParam("limit", limit)
      .when()
        .get("/events")
      .then()
        .spec(response())
        .extract()
        .response();
  }
}
