package org.qapower.apiclient;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.qapower.config.Config;

import static org.qapower.endpoints.Endpoints.PATH_API_VERSION;

public final class BaseRequestSpec {
  
  public static RequestSpecBuilder getRequestSpecBuilder() {
    RequestSpecBuilder builder = new RequestSpecBuilder()
      .setBaseUri(Config.getBaseUrl())
      .setBasePath(PATH_API_VERSION)
      .setAccept(ContentType.JSON);
    
    if (System.getProperty("debug") != null) {
      builder.log(LogDetail.ALL);
    }
    
    return builder;
  }
}
