package org.qapower.tests.contract;

import static org.qapower.assertions.ContractAsserts.assertFieldExists;
import static org.qapower.assertions.ContractAsserts.assertFieldIsString;
import static org.qapower.assertions.ContractAsserts.assertNonEmptyArray;

import io.restassured.response.Response;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.qapower.apiclient.EventsApi;
import org.qapower.tests.BaseTest;
import org.qapower.utils.Lazy;

public class EventsContractTest extends BaseTest {

  private static final Supplier<Response> response = new Lazy<>(() -> new EventsApi().getEvents(1));

  @Test
  void rootContainsRequiredFields() {
    assertFieldExists(response.get(), "title");
    assertFieldExists(response.get(), "description");
    assertFieldExists(response.get(), "link");
    assertFieldExists(response.get(), "events");
  }

  @Test
  void eventsIsNotEmptyArray() {
    assertNonEmptyArray(response.get(), "events");
  }

  @Test
  void eventsContainsRequiredFields() {
    assertFieldExists(response.get(), "events[0].id");
    assertFieldExists(response.get(), "events[0].title");
    assertFieldExists(response.get(), "events[0].categories");
    assertFieldExists(response.get(), "events[0].geometry");
  }

  @Test
  void eventIdIsNonNullString() {
    assertFieldIsString(response.get(), "events[0].id");
  }

  @Test
  void categoriesAreValid() {
    assertNonEmptyArray(response.get(), "events[0].categories");
    assertFieldExists(response.get(), "events[0].categories[0].id");
    assertFieldExists(response.get(), "events[0].categories[0].title");
  }

  @Test
  void geometryIsValid() {
    assertNonEmptyArray(response.get(), "events[0].geometry");
    assertFieldExists(response.get(), "events[0].geometry[0].date");
    assertFieldExists(response.get(), "events[0].geometry[0].type");
    assertFieldExists(response.get(), "events[0].geometry[0].coordinates");
  }
}
