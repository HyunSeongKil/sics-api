package kr.co.dhecoenergy.sicsapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "global_variables")
public class GlobalVariables {
  @Id
  @Column(name = "key")
  private String key;

  @Column(name = "value")
  private String value;

  @Column(name = "group_name")
  private String groupName;

  @Column(name = "dc")
  private String dc;

  @Column(name = "register_id")
  private String registerId;

  @Column(name = "regist_dt")
  private Date registDt;

  @Column(name = "updater_id")
  private String updaterId;

  @Column(name = "update_dt")
  private Date updateDt;
}
