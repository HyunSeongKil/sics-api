package kr.co.dhecoenergy.sicsapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device_control")
public class DeviceControl {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "device_control_id")
  private Long deviceControlId;

  @Column(name = "device_id")
  private Long deviceId;

}
