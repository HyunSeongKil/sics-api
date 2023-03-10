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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "device_control")
public class DeviceControl {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "device_control_id")
  private long deviceControlId;

  @Column(name = "device_id")
  private long deviceId;

}
