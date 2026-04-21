package org.qapower.apiclient;

import static org.qapower.endpoints.Endpoints.MAGNITUDES;

import io.restassured.response.Response;

public class MagnitudesApi extends BaseClient {

  public Response getMagnitudes() {
    return get(MAGNITUDES);
  }
}
