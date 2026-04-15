package org.qapower.assertions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.restassured.response.Response;
import java.util.List;

public class ContractAsserts {

  // JsonPath is used instead of deserializing the entire response into a DTO,
  // allowing direct validation of the contract based on actual response fields.
  public static void assertFieldExists(Response response, String path) {
    assertNotNull(
        response.jsonPath().get(path),
        String.format("Expected field '%s' to exist in the response, but it was null.", path));
  }

  public static void assertNonEmptyArray(Response response, String path) {
    List<?> list = response.jsonPath().getList(path);
    assertNotNull(
        list,
        String.format("Expected array '%s' to exist in the response, but it was null.", path));
    assertFalse(
        list.isEmpty(),
        String.format("Expected non-empty array '%s' in the response, but it was empty.", path));
  }

  public static void assertFieldIsString(Response response, String path) {
    Object value = response.jsonPath().get(path);
    assertNotNull(
        value,
        String.format("Expected field '%s' to exist in the response, but it was null.", path));
    assertInstanceOf(
        String.class,
        value,
        String.format(
            "Expected field '%s' to be a string, but it was %s.",
            path, value.getClass().getSimpleName()));
  }
}
