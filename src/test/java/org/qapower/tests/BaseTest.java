package org.qapower.tests;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

  @BeforeAll
  static void setUpBase() {
    // EONET отдаёт Content-Type: application/rss+xml с JSON-телом.
    // Регистрируем JSON-парсер глобально, чтобы любые пути RestAssured
    // (включая неявный парсинг для логирования) не пытались читать тело как XML.
    RestAssured.registerParser("application/rss+xml", Parser.JSON);
    RestAssured.defaultParser = Parser.JSON;
  }
}
