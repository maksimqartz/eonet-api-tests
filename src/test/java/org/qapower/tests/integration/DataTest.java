package org.qapower.tests.integration;

import org.junitpioneer.jupiter.RetryingTest;
import org.qapower.apiclient.EventsApi;
import org.qapower.assertions.EventsResponseAssert;
import org.qapower.dto.EventsResponse;
import org.qapower.tests.BaseTest;

public class DataTest extends BaseTest {

  private final EventsApi api = new EventsApi();

  @RetryingTest(3)
  void eventsResponse_deserializes() {
    EventsResponse response = api.getEvents(1).as(EventsResponse.class);

    EventsResponseAssert.assertThatEvents(response)
        .hasTitle()
        .hasEvents()
        .firstEventIsValid();
  }
}