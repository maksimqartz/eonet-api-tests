package org.qapower.apiclient;

import static org.qapower.endpoints.Endpoints.PATH_API_VERSION;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import org.qapower.config.Config;

public final class BaseRequestSpec {

  private static final int TIMEOUT_MS = 8_000;

  private BaseRequestSpec() {}

  public static RequestSpecBuilder getRequestSpecBuilder() {
    // EONET возвращает Content-Type: application/rss+xml даже для JSON-тела,
    // поэтому форсируем Jackson для .as(Class) — иначе RestAssured ищет JAXB-маппер.
    // Сам парсер для этого content-type регистрируется глобально в BaseTest.
    RestAssuredConfig httpConfig =
        RestAssuredConfig.config()
            .httpClient(
                HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout", TIMEOUT_MS)
                    .setParam("http.socket.timeout", TIMEOUT_MS))
            .objectMapperConfig(
                ObjectMapperConfig.objectMapperConfig()
                    .defaultObjectMapperType(ObjectMapperType.JACKSON_2));

    RequestSpecBuilder builder =
        new RequestSpecBuilder()
            .setBaseUri(Config.getBaseUrl())
            .setBasePath(PATH_API_VERSION)
            .setAccept(ContentType.JSON)
            .setConfig(httpConfig);

    if (System.getProperty("debug") != null) {
      builder.log(LogDetail.ALL);
    }
    return builder;
  }
}
