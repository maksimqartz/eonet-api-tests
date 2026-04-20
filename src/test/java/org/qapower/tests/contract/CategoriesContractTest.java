package org.qapower.tests.contract;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.qapower.assertions.ContractAsserts.assertFieldExists;
import static org.qapower.assertions.ContractAsserts.assertFieldIsString;
import static org.qapower.assertions.ContractAsserts.assertNonEmptyArray;

import io.restassured.response.Response;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;
import org.qapower.apiclient.CategoriesApi;
import org.qapower.tests.BaseTest;
import org.qapower.utils.Lazy;

public class CategoriesContractTest extends BaseTest {

  private static final Supplier<Response> response =
      new Lazy<>(() -> new CategoriesApi().getCategories());

  // TODO: Внедрить повсеместно через assert + step
  @Test
  void categoriesResponseMatchesContract() {
    response.get().then().body(matchesJsonSchemaInClasspath("schemas/categories.json"));
  }

  @Test
  void rootContainsRequiredFields() {
    assertFieldExists(response.get(), "title");
    assertFieldExists(response.get(), "description");
    assertFieldExists(response.get(), "link");
    assertFieldExists(response.get(), "categories");
  }

  @Test
  void categoriesIsNotEmptyArray() {
    assertNonEmptyArray(response.get(), "categories");
  }

  @Test
  void categoriesContainsRequiredFields() {
    assertFieldExists(response.get(), "categories[0].id");
    assertFieldExists(response.get(), "categories[0].title");
    assertFieldExists(response.get(), "categories[0].link");
    assertFieldExists(response.get(), "categories[0].description");
    assertFieldExists(response.get(), "categories[0].layers");
  }

  @Test
  void categoriesIdIsNonNullString() {
    assertFieldIsString(response.get(), "categories[0].id");
  }
}
