package org.qapower.assertions;

import io.qameta.allure.Step;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.qapower.dto.Event;
import org.qapower.dto.EventsResponse;
import org.qapower.dto.enums.EventStatus;

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

  public EventsResponseAssert hasExactlyEvents(int count) {
    assertEquals(count, actual.events().size());
    return this;
  }

  public EventsResponseAssert firstEventIsValid() {
    Event event = actual.events().getFirst();
    assertNotNull(event.id());
    assertNotNull(event.title());
    return this;
  }

  @Step("Checking that all events have geometry dates within the last {days} days")
  public EventsResponseAssert hasEventsInPeriod(int days) {
    Instant boundary = Instant.now().minus(days, ChronoUnit.DAYS);
    assertAll(actual.events().stream()
        .map(event -> () -> {
          boolean hasRecentDate = event.geometry().stream()
              .anyMatch(geo -> geo.date().isAfter(boundary));
          assertTrue(hasRecentDate,
              "Event '%s' has no geometry dates within last %d days".formatted(event.id(), days));
        }));
    return this;
  }

  @Step("Checking that all events have correct closed status: {status}")
  public EventsResponseAssert hasCorrectClosedStatus(EventStatus status) {
    switch (status) {
      case OPEN -> actual.events().forEach(event ->
          assertNull(event.closed(),
              "Event '%s' should be open but is closed".formatted(event.id())));

      case CLOSED -> actual.events().forEach(event ->
          assertNotNull(event.closed(),
              "Event '%s' should be closed".formatted(event.id())));

      case ALL -> {} // Open and Closed events are expected, no additional checks needed
    }
    return this;
  }
}
