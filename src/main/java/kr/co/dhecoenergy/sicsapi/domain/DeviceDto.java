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
public class DeviceDto {
  private long deviceId;

  private long farmId;

  private String deviceName;

  private String serialNo;

  private String modelName;

  // 장치 경도
  private double deviceLongitudeValue;

  // 장치 위도
  private double deviceLatitudeValue;

  // 구역 번호
  private long areaNo;

  private String deviceDc;

  private String registerId;

  private Date registDt;

  private String updaterId;

  private Date updateDt;
}
