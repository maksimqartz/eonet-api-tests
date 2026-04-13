package org.qapower.apiclient;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

public final class BaseResponseSpec {
  
  public static ResponseSpecBuilder getResponseSpecBuilder() {
    return new ResponseSpecBuilder() // EONET API отдаёт Content-Type: application/rss+xml для JSON-ответа — это их баг. Проверять content-type в response spec смысла нет, тест будет всегда падать.
      .log(LogDetail.STATUS).log(LogDetail.BODY);
  }
}
