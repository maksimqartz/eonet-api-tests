package org.qapower.apiclient;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.parsing.Parser;

public final class BaseResponseSpec {

  private BaseResponseSpec() {}

  // EONET API отдаёт Content-Type: application/rss+xml для JSON-ответа — это их баг
  // content-type проверять в response spec смысла нет, тест будет всегда падать.
  public static ResponseSpecBuilder getResponseSpecBuilder() {
    return new ResponseSpecBuilder()
        .registerParser("application/rss+xml", Parser.JSON)
        .log(LogDetail.STATUS)
        .log(LogDetail.BODY);
  }
}
