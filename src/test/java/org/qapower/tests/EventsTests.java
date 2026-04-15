package org.qapower.tests;

import org.junit.jupiter.api.Test;
import org.qapower.apiclient.EventsApi;
import org.qapower.assertions.EventsResponseAssert;
import org.qapower.dto.EventsResponse;

public class EventsTests extends BaseTest {

  private final EventsApi api = new EventsApi();

  @Test
  void eventsGetBodyIsValid() {
    EventsResponse body = api.getEvents(1)
        .getBody().jsonPath().getObject("", EventsResponse.class);

    EventsResponseAssert.assertThatEvents(body)
        .hasTitle().hasEvents().firstEventIsValid();
  }
}
