package org.qapower.tests;

import org.junit.jupiter.api.Test;
import org.qapower.apiclient.EventsApi;
import org.qapower.assertions.EventsResponseAssert;
import org.qapower.dto.EventsResponse;

import static org.qapower.assertions.GetAsserts.assertGetResponseIs200;


public class EventsSmokeTest extends BaseTest {
  
  private final EventsApi api = new EventsApi();
  
  @Test
  void eventsGetCodeIs200() {
    assertGetResponseIs200(api.getEvents(1));
  }
  
  @Test
  void eventsGetBodyIsValid() {
    EventsResponse body = api.getEvents(1)
      .getBody()
      .jsonPath()
      .getObject("", EventsResponse.class);
    
    EventsResponseAssert.assertThatEvents(body)
      .hasTitle()
      .hasEvents()
      .firstEventIsValid();
  }
}
