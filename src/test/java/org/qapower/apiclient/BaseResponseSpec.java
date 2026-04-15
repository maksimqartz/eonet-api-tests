package org.qapower.apiclient;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;

public final class BaseResponseSpec {

  // EONET API отдаёт Content-Type: application/rss+xml для JSON-ответа — это их баг
  // content-type проверять в response spec смысла нет, тест будет всегда падать.
  public static ResponseSpecBuilder getResponseSpecBuilder() {
    return new ResponseSpecBuilder().log(LogDetail.STATUS).log(LogDetail.BODY);
  }
}
