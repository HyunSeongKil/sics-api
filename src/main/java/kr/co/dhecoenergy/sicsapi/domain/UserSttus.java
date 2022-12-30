package kr.co.dhecoenergy.sicsapi.domain;

public enum UserSttus {
  OK("OK"),
  BLOCKED("BLOCKED");

  private String name;

  UserSttus(String name) {
    this.name = name;
  }
}
