package org.qapower.tests;

import org.junit.jupiter.api.Test;
import org.qapower.apiclient.CategoriesApi;
import org.qapower.assertions.CategoriesResponseAssert;
import org.qapower.dto.CategoriesResponse;

public class CategoriesTest extends BaseTest {

  private final CategoriesApi api = new CategoriesApi();

  @Test
  void getCategoriesBodyValid() {
    CategoriesResponse body =
        api.getCategories(1).getBody().jsonPath().getObject("", CategoriesResponse.class);

    CategoriesResponseAssert.assertThatCategories(body)
        .hasTitle()
        .hasCategories()
        .firstCategoryIsValid();
  }
}
