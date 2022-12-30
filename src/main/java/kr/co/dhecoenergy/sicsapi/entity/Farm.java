package kr.co.dhecoenergy.sicsapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.co.dhecoenergy.sicsapi.domain.CropsType;
import kr.co.dhecoenergy.sicsapi.domain.FarmSttus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "farm")
/**
 * 농가
 */
public class Farm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "farm_id")
  private Long farmId;

  @Column(name = "farm_name")
  private String farmName;

  /**
   * 농장주 아이디
   */
  @Column(name = "farmer_id")
  private Long farmerId;

  @Column(name = "farm_sttus")
  private FarmSttus farmSttus;

  // 농장 주소
  @Column(name = "farm_address")
  private String farmAddress;

  // 농장 경도
  @Column(name = "farm_longitude_value")
  private Double farmLongitudeValue;

  // 농장 위도
  @Column(name = "farm_latitude_value")
  private Double farmLatitudeValue;

  // 작물 종류
  @Column(name = "crops_type")
  private CropsType cropsType;

  @Column(name = "farm_dc", length = 1024)
  private String farmDc;

  @Column(name = "register_id")
  private String registerId;

  @Column(name = "regist_dt")
  private Date registDt;

  @Column(name = "updater_id")
  private String updaterId;

  @Column(name = "update_dt")
  private Date updateDt;
}
