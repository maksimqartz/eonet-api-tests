package org.qapower.assertions;

import io.restassured.response.Response;

public class GetAsserts {
  
  public static void assertGetResponseIs200(Response response) {
    response.then().statusCode(200);
  }
}
