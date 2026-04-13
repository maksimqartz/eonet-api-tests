package org.qapower.tests;

import org.junit.jupiter.api.Test;
import org.qapower.apiclient.EventsApi;
import org.qapower.dto.Event;
import org.qapower.dto.EventsResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class EventsSmokeTest extends BaseTest {
  
  private final EventsApi api = new EventsApi();
  
  @Test
  void eventsGetCodeIs200() {
    api.getEvents(1)
      .then()
      .statusCode(200);
  }
  
  @Test
  void eventsGetBodyIsValid() {
    EventsResponse body = api.getEvents(1)
      .getBody()
      .jsonPath()
      .getObject("", EventsResponse.class);
    
    assertNotNull(body.title());
    assertNotNull(body.events());
    assertFalse(body.events().isEmpty());
    
    Event event = body.events().getFirst();
    assertNotNull(event.id());
    assertNotNull(event.title());
  }
}
