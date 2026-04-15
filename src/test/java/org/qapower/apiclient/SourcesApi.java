package org.qapower.apiclient;

import static org.qapower.endpoints.Endpoints.SOURCES;

import io.restassured.response.Response;

public class SourcesApi extends BaseClient {

  public Response getSources() {
    return get(SOURCES);
  }
}
