package org.qapower.dto.enums;

public enum EventStatus {
  OPEN,
  CLOSED,
  ALL;

  public String getValue() {
    return name().toLowerCase();
  }
}
