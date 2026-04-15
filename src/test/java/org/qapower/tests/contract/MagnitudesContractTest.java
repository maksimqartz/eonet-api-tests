package org.qapower.tests.contract;

import static org.qapower.assertions.ContractAsserts.assertFieldExists;
import static org.qapower.assertions.ContractAsserts.assertFieldIsString;
import static org.qapower.assertions.ContractAsserts.assertNonEmptyArray;

import io.restassured.response.Response;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.qapower.apiclient.MagnitudesApi;
import org.qapower.utils.Lazy;

public class MagnitudesContractTest {

  private static final Supplier<Response> response =
      new Lazy<>(() -> new MagnitudesApi().getMagnitudes());

  @Test
  void rootContainsRequiredFields() {
    assertFieldExists(response.get(), "title");
    assertFieldExists(response.get(), "description");
    assertFieldExists(response.get(), "link");
    assertFieldExists(response.get(), "magnitudes");
  }

  @Test
  void magnitudesIsNotEmptyArray() {
    assertNonEmptyArray(response.get(), "magnitudes");
  }

  @Test
  void magnitudesContainsRequiredFields() {
    assertFieldExists(response.get(), "magnitudes[0].id");
    assertFieldExists(response.get(), "magnitudes[0].name");
    assertFieldExists(response.get(), "magnitudes[0].unit");
    assertFieldExists(response.get(), "magnitudes[0].description");
    assertFieldExists(response.get(), "magnitudes[0].link");
  }

  @Test
  void magnitudesIdIsNonNullString() {
    assertFieldIsString(response.get(), "magnitudes[0].id");
  }
}
