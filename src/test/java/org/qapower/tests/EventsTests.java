package org.qapower.tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.qapower.apiclient.EventsApi;
import org.qapower.assertions.EventsResponseAssert;
import org.qapower.dto.EventsResponse;

public class EventsTests extends BaseTest {

  static final EventsApi api = new EventsApi();
  static EventsResponse body;

  @BeforeAll
  static void setUp() {
    body = api.getEvents(1).getBody().jsonPath().getObject("", EventsResponse.class);
  }

  @Test
  void getEventsBodyValid() {
    EventsResponseAssert.assertThatEvents(body).hasTitle().hasEvents().firstEventIsValid();
  }
}
