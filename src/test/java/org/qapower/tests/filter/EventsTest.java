package org.qapower.tests.filter;

import static org.qapower.assertions.ResponseAsserts.assertResponseStatusOk;

import io.restassured.response.Response;
import java.util.Map;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.qapower.apiclient.EventsApi;
import org.qapower.assertions.EventsResponseAssert;
import org.qapower.dto.EventsResponse;
import org.qapower.dto.enums.EventStatus;
import org.qapower.tests.BaseTest;

class EventsTest extends BaseTest {

  private static final int SAFE_LIMIT = 5;
  private final EventsApi api = new EventsApi();

  @ParameterizedTest(name = "Checking an events request with limit filter, value: {0}")
  @ValueSource(ints = {0, 1, 10})
  void eventsResponseStructureWithDifferentLimits(int limit) {
    Response response = api.getEvents(limit);

    assertResponseStatusOk(response);
    EventsResponseAssert.assertThatEvents(response.as(EventsResponse.class))
        .hasTitle()
        .hasExactlyEvents(limit);
  }

  @ParameterizedTest(name = "Checking events with status filter: {0}")
  @EnumSource(EventStatus.class)
  void eventsResponseStructureWithDifferentStatusFilters(EventStatus status) {
    Response response = api.getEvents(
        Map.of("status", status.getValue(), "limit", SAFE_LIMIT));

    assertResponseStatusOk(response);
    EventsResponseAssert.assertThatEvents(response.as(EventsResponse.class))
        .hasTitle()
        .hasEvents()
        .hasCorrectClosedStatus(status);
  }

  @ParameterizedTest(name = "Checking an events request with days filter, value: {0}")
  @ValueSource(ints = {3, 7, 30})
  void eventsResponseStructureInDifferentPeriods(int days) {
    Response response = api.getEvents(Map.of("days", days));

    assertResponseStatusOk(response);
    EventsResponseAssert.assertThatEvents(response.as(EventsResponse.class))
        .hasTitle().hasEvents().hasEventsInPeriod(days);
  }


}
