package org.qapower.apiclient;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.qapower.config.Config;

public final class BaseRequestSpec {
  
  public static RequestSpecBuilder getRequestSpecBuilder() {
    RequestSpecBuilder builder = new RequestSpecBuilder()
      .setBaseUri(Config.getBaseUrl())
      .setBasePath("/api/v3")
      .setAccept(ContentType.JSON);
    
    if (System.getProperty("debug") != null) {
      builder.log(LogDetail.ALL);
    }
    
    return builder;
  }
}
