package org.qapower.tests.contract;

import static org.qapower.assertions.ContractAsserts.assertFieldExists;
import static org.qapower.assertions.ContractAsserts.assertFieldIsString;
import static org.qapower.assertions.ContractAsserts.assertNonEmptyArray;

import io.restassured.response.Response;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.qapower.apiclient.SourcesApi;
import org.qapower.tests.BaseTest;
import org.qapower.utils.Lazy;

public class SourcesContractTest extends BaseTest {

  private static final Supplier<Response> response =
      new Lazy<>(() -> new SourcesApi().getSources());

  @Test
  void rootContainsRequiredFields() {
    assertFieldExists(response.get(), "title");
    assertFieldExists(response.get(), "description");
    assertFieldExists(response.get(), "link");
    assertFieldExists(response.get(), "sources");
  }

  @Test
  void sourcesIsNotEmptyArray() {
    assertNonEmptyArray(response.get(), "sources");
  }

  @Test
  void sourcesContainsRequiredFields() {
    assertFieldExists(response.get(), "sources[0].id");
    assertFieldExists(response.get(), "sources[0].title");
    assertFieldExists(response.get(), "sources[0].source");
    assertFieldExists(response.get(), "sources[0].link");
  }

  @Test
  void sourcesIdIsNonNullString() {
    assertFieldIsString(response.get(), "sources[0].id");
  }
}
