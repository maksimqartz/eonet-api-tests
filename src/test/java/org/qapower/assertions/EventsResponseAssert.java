package org.qapower.assertions;

import static org.junit.jupiter.api.Assertions.*;

import io.qameta.allure.Step;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.qapower.dto.Category;
import org.qapower.dto.Event;
import org.qapower.dto.EventsResponse;
import org.qapower.dto.enums.EventStatus;

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
    assertFalse(actual.events().isEmpty(), "Cannot validate first event: events list is empty");
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

  @Step("Checking that events are filtered by start and end dates correctly")
  public EventsResponseAssert hasEventsWithinCorrectDateRange(LocalDate start, LocalDate end) {
    Instant startInstant = start.atStartOfDay(ZoneOffset.UTC).toInstant();
    Instant endInstant = end.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();

    assertAll(actual.events().stream()
        .map(event -> () -> {
          boolean hasDateInRange = event.geometry().stream()
              .anyMatch(geo -> !geo.date().isBefore(startInstant)
                  && geo.date().isBefore(endInstant));
          assertTrue(hasDateInRange,
              "Event '%s' has no geometry dates within %s to %s"
                  .formatted(event.id(), start, end));
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

  @Step("Checking that all events have correct filtered categories: {categoryFilter}")
  public EventsResponseAssert hasCorrectCategories(String categoryFilter) {
    List<String> expectedIds = List.of(categoryFilter.split(","));

    assertAll(actual.events().stream()
        .map(event -> () -> {
          List<String> eventCategoryIds = event.categories().stream()
              .map(Category::id)
              .toList();
          boolean hasMatch = eventCategoryIds.stream()
              .anyMatch(expectedIds::contains);
          assertTrue(hasMatch,
              "Event '%s' has categories %s, expected at least one of %s"
                  .formatted(event.id(), eventCategoryIds, expectedIds));
        }));
    return this;
  }
}
