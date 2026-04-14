package org.qapower.apiclient;

import io.restassured.response.Response;

import static org.qapower.endpoints.Endpoints.EVENTS;

public class EventsApi extends BaseClient {
  
  public Response getEvents(int limit) {
    return get(EVENTS, "limit", limit);
  }
}
