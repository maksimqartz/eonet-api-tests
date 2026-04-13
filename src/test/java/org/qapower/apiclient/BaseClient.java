package org.qapower.apiclient;

import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseClient {

  protected RequestSpecification request() {
    return BaseRequestSpec.getRequestSpecBuilder().build();
  }
  
  protected ResponseSpecification response() {
    return BaseResponseSpec.getResponseSpecBuilder().build();
  }
}
