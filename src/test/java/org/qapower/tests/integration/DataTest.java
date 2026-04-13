package org.qapower.tests.integration;

import org.junit.jupiter.api.Test;
import org.qapower.apiclient.EventsApi;
import org.qapower.dto.Event;
import org.qapower.dto.EventsResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.qapower.tests.BaseTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataTest extends BaseTest {
  
  private final ObjectMapper mapper = new ObjectMapper();
  private final EventsApi api = new EventsApi();
  
  @Test
  void eventsResponse_deserializes() throws Exception {
    String json = api.getEvents(1).getBody().asString();
    
    EventsResponse response = mapper.readValue(json, EventsResponse.class);
    
    assertNotNull(response.title());
    assertNotNull(response.events());
    assertFalse(response.events().isEmpty());
    
    Event event = response.events().getFirst();
    assertNotNull(event.id());
    assertNotNull(event.title());
  }
}
