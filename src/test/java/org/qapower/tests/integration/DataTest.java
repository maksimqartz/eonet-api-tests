package org.qapower.tests.integration;

import org.junit.jupiter.api.Test;
import org.qapower.apiclient.EventsApi;
import org.qapower.assertions.EventsResponseAssert;
import org.qapower.dto.EventsResponse;
import org.qapower.tests.BaseTest;

public class DataTest extends BaseTest {

  private final EventsApi api = new EventsApi();

  @Test
  void eventsResponse_deserializes() {
    EventsResponse response = api.getEvents(1).as(EventsResponse.class);

    EventsResponseAssert.assertThatEvents(response)
        .hasTitle()
        .hasEvents()
        .firstEventIsValid();
  }
}
