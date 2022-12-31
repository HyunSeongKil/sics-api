package kr.co.dhecoenergy.sicsapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.co.dhecoenergy.sicsapi.domain.UserSttus;
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
@Table(name = "user")
/**
 * 사용자
 */
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long userId;

  @Column(name = "login_id")
  private String loginId;

  @Column(name = "user_name")
  private String userName;

  @Column(name = "password", length = 1024)
  private String password;

  @Column(name = "login_fail_cnt")
  private Integer loginFailCnt;

  @Column(name = "latest_login_dt")
  private Date latestLoginDt;

  @Column(name = "latest_login_fail_dt")
  private Date latestLoginFailDt;

  @Column(name = "latest_password_change_dt")
  private Date latestPasswordChangeDt;

  @Column(name = "user_sttus")
  @Enumerated(EnumType.STRING)
  private UserSttus userSttus;

  @Column(name = "image_atchmnfl_group_id")
  private Long imageAtchmnflGroupId;

  @Column(name = "user_dc", length = 1024)
  private String userDc;

  @Column(name = "register_id")
  private String registerId;

  @Column(name = "regist_dt")
  private Date registDt;

  @Column(name = "updater_id")
  private String updaterId;

  @Column(name = "update_dt")
  private Date updateDt;
}
