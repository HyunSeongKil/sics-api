package kr.co.dhecoenergy.sicsapi.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FarmDto {
  private long farmId;

  private String farmName;

  /**
   * 농장주 아이디
   */
  private long farmerId;

  private FarmSttus farmSttus;

  // 농장 주소
  private String farmAddress;

  // 농장 경도
  private double farmLongitudeValue;

  // 농장 위도
  private double farmLatitudeValue;

  // 작물 종류
  private CropsType cropsType;

  private long imageAtchmnflGroupId;

  private String farmDc;

  private String registerId;

  private Date registDt;

  private String updaterId;

  private Date updateDt;
}
