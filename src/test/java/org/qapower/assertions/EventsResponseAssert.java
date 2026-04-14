package org.qapower.assertions;

import org.qapower.dto.Event;
import org.qapower.dto.EventsResponse;

import static org.junit.jupiter.api.Assertions.*;

public class EventsResponseAssert {
  
  private final EventsResponse actual;
  
  public EventsResponseAssert(EventsResponse actual) {
    this.actual = actual;
  }
  
  public static EventsResponseAssert assertThatEvents(EventsResponse actual) {
    return new EventsResponseAssert(actual);
  }
  
  public EventsResponseAssert hasTitle() {
    assertNotNull(actual.title());
    return this;
  }
  
  public EventsResponseAssert hasEvents() {
    assertNotNull(actual.events());
    assertFalse(actual.events().isEmpty());
    return this;
  }
  
  public EventsResponseAssert firstEventIsValid() {
    Event event = actual.events().getFirst();
    assertNotNull(event.id());
    assertNotNull(event.title());
    return this;
  }
}
