package kr.co.dhecoenergy.sicsapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device")
public class Device {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "device_id")
  private Long deviceId;

  @Column(name = "device_name")
  private String deviceName;

  @Column(name = "serial_no")
  private String serialNo;

  @Column(name = "model_name")
  private String modelName;

  // 장치 경도
  @Column(name = "device_longitude_value")
  private Double deviceLongitudeValue;

  // 장치 위도
  @Column(name = "device_latitude_value")
  private Double deviceLatitudeValue;

  // 구역 번호
  @Column(name = "area_no")
  private Long areaNo;

  @Column(name = "device_dc", length = 1024)
  private String deviceDc;

  @Column(name = "register_id")
  private String registerId;

  @Column(name = "regist_dt")
  private Date registDt;

  @Column(name = "updater_id")
  private String updaterId;

  @Column(name = "update_dt")
  private Date updateDt;

}
