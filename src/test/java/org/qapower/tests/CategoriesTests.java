package org.qapower.tests;

import org.junit.jupiter.api.Test;
import org.qapower.apiclient.CategoriesApi;
import org.qapower.assertions.CategoriesResponseAssert;
import org.qapower.dto.CategoriesResponse;

public class CategoriesTests extends BaseTest {

  private final CategoriesApi api = new CategoriesApi();

  @Test
  void getEventsBodyValid() {
    CategoriesResponse body =
        api.getCategories(1).getBody().jsonPath().getObject("", CategoriesResponse.class);

    CategoriesResponseAssert.assertThatCategories(body)
        .hasTitle()
        .hasCategories()
        .firstEventIsValid();
  }
}
