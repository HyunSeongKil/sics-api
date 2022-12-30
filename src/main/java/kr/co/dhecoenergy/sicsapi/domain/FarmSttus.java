package kr.co.dhecoenergy.sicsapi.domain;

public enum FarmSttus {
  OK("OK"),
  UNKNOWN("UNKNOWN");

  private String name;

  FarmSttus(String name) {
    this.name = name;
  }
}
