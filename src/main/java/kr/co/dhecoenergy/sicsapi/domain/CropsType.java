package kr.co.dhecoenergy.sicsapi.domain;

/**
 * 작물 종류
 */
public enum CropsType {
  PEACH("복숭아"),
  APPLE("사과");

  private String name;

  CropsType(String name) {
    this.name = name;
  }
}
