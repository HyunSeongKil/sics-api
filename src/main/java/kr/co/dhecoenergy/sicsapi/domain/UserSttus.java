package kr.co.dhecoenergy.sicsapi.domain;

public enum UserSttus {
  OK("OK"),
  BLOCKED("BLOCKED");

  private String code;

  UserSttus(String code) {
    this.code = code;
  }

  public String getCode() {
    return this.code;
  }
}
