package org.qapower.apiclient;

import io.restassured.response.Response;

import static org.qapower.endpoints.Endpoints.MAGNITUDES;

public class MagnitudesApi extends BaseClient {

  public Response getMagnitudes() {
    return get(MAGNITUDES);
  }
}
