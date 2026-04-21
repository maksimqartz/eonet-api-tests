package org.qapower.assertions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.restassured.response.Response;
import java.util.List;

public final class ContractAsserts {

  private ContractAsserts() {}

  // JsonPath is used instead of deserializing the entire response into a DTO,
  // allowing direct validation of the contract based on actual response fields.
  public static void assertFieldExists(Response response, String path) {
    assertNotNull(
        response.jsonPath().get(path),
        "Expected field '%s' to exist in the response, but it was null.".formatted(path));
  }

  public static void assertNonEmptyArray(Response response, String path) {
    List<?> list = response.jsonPath().getList(path);
    assertNotNull(
        list, "Expected array '%s' to exist in the response, but it was null.".formatted(path));
    assertFalse(
        list.isEmpty(),
        "Expected non-empty array '%s' in the response, but it was empty.".formatted(path));
  }

  public static void assertFieldIsString(Response response, String path) {
    Object value = response.jsonPath().get(path);
    assertNotNull(
        value, "Expected field '%s' to exist in the response, but it was null.".formatted(path));
    assertInstanceOf(
        String.class,
        value,
        "Expected field '%s' to be a string, but it was %s."
            .formatted(path, value.getClass().getSimpleName()));
  }
}
