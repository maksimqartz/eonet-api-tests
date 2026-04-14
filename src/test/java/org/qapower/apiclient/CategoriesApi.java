package org.qapower.apiclient;

import io.restassured.response.Response;

import static org.qapower.endpoints.Endpoints.CATEGORIES;

public class CategoriesApi extends BaseClient {
  
  public Response getCategories() {
    return get(CATEGORIES);
  }
  
  public Response getCategories(int limit) {
    return get(CATEGORIES, "limit", limit);
  }
}
