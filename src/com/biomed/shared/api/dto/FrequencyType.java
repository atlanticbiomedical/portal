package com.biomed.shared.api.dto;

public enum FrequencyType {
  Anesthesia("Anesthesia"),
  Annual("Annual"),
  ERT("ERT"),
  Imaging("Imaging"),
  Legacy("Legacy"),
  Medgas("Medgas"),
  Neptune("Neptune"),
  Quarterly("Quarterly"),
  RoomAirExchange("Room Air Exchange"),
  Semi("Semi"),
  Sterilizer("Sterilizer"),
  TraceGas("Trace Gas");

  private final String label;

  FrequencyType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
