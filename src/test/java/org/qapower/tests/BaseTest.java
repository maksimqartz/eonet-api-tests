package org.qapower.tests;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

  @BeforeAll
  static void setUp() {
    RestAssured.defaultParser = Parser.JSON;
    RestAssured.registerParser("application/rss+xml", Parser.JSON);

    RestAssured.config =
        RestAssured.config()
            .httpClient(
                io.restassured.config.HttpClientConfig.httpClientConfig()
                    .setParam("http.connection.timeout", 8000)
                    .setParam("http.socket.timeout", 8000));
  }
}
