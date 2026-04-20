package org.qapower.apiclient;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import java.util.Map;

import static org.qapower.endpoints.Endpoints.EVENTS;

public class EventsApi extends BaseClient {

  @Step("Получить события с параметром limit={limit}")
  public Response getEvents(int limit) {
    return get(EVENTS, "limit", limit);
  }
  @Step("Получить события с параметрами: {params}")
  public Response getEvents(Map<String, Object> params) {
    return get(EVENTS, params);
  }
}
