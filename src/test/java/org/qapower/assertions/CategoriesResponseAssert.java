package org.qapower.assertions;

import org.qapower.dto.CategoriesResponse;
import org.qapower.dto.Category;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoriesResponseAssert {

  private final CategoriesResponse actual;

  public CategoriesResponseAssert(CategoriesResponse actual) {
    this.actual = actual;
  }

  public static CategoriesResponseAssert assertThatCategories(CategoriesResponse actual) {
    return new CategoriesResponseAssert(actual);
  }

  public CategoriesResponseAssert hasTitle() {
    assertNotNull(actual.title());
    return this;
  }

  public CategoriesResponseAssert hasCategories() {
    assertNotNull(actual.categories());
    assertFalse(actual.categories().isEmpty());
    return this;
  }

  public CategoriesResponseAssert firstEventIsValid() {
    Category event = actual.categories().getFirst();
    assertNotNull(event.id());
    assertNotNull(event.title());
    return this;
  }
}
