package com.biomed.shared.api.dto;

public enum State {
  AL("Alabama"),
  AK("Alaska"),
  AZ("Arizona"),
  AR("Arkansas"),
  CA("California"),
  CO("Colorado"),
  CT("Connecticut"),
  DC("District of Columbia"),
  DE("Delaware"),
  FL("Florida"),
  GA("Georgia"),
  HI("Hawaii"),
  ID("Idaho"),
  IL("Illinois"),
  IN("Indiana"),
  IA("Iowa"),
  KS("Kansas"),
  KY("Kentucky"),
  LA("Louisiana"),
  ME("Maine"),
  MD("Maryland"),
  MA("Massachusetts"),
  MI("Michigan"),
  MN("Minnesota"),
  MS("Mississippi"),
  MO("Missouri"),
  MT("Montana"),
  NE("Nebraska"),
  NV("Nevada"),
  NH("New Hampshire"),
  NJ("New Jersey"),
  NM("New Mexico"),
  NY("New York"),
  NC("North Carolina"),
  ND("North Dakota"),
  OH("Ohio"),
  OK("Oklahoma"),
  OR("Oregon"),
  PA("Pennsylvania"),
  RI("Rhode Island"),
  SC("South Carolina"),
  SD("South Dakota"),
  TN("Tennessee"),
  TX("Texas"),
  UT("Utah"),
  VT("Vermont"),
  VA("Virginia"),
  WA("Washington"),
  WV("West Virginia"),
  WI("Wisconsin"),
  WY("Wyoming");

  final String value;

  State(String value) {
    this.value = value;
  }

  public String toString() {
    return value;
  }

  String getKey() {
    return name();
  }

  public static State fromString(String str) {
    if (str != null && !str.isEmpty()) {
      return State.valueOf(str);
    } else {
      return null;
    }
  }

  public static String toString(State state) {
    if (state == null) {
      return null;
    } else {
      return state.name();
    }
  }
}
