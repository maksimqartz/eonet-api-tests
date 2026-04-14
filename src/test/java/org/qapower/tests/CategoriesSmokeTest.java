package org.qapower.tests;

import org.junit.jupiter.api.Test;
import org.qapower.apiclient.CategoriesApi;
import org.qapower.assertions.CategoriesResponseAssert;
import org.qapower.dto.CategoriesResponse;

import static org.qapower.assertions.GetAsserts.assertGetResponseIs200;


public class CategoriesSmokeTest extends BaseTest {
  
  private final CategoriesApi api = new CategoriesApi();
  
  @Test
  void getEventsCode200() {
    assertGetResponseIs200(api.getCategories());
  }
  
  @Test
  void getEventsBodyValid() {
    CategoriesResponse body = api.getCategories(1)
      .getBody()
      .jsonPath()
      .getObject("", CategoriesResponse.class);
    
    CategoriesResponseAssert.assertThatCategories(body)
      .hasTitle()
      .hasCategories()
      .firstEventIsValid();
  }
}
