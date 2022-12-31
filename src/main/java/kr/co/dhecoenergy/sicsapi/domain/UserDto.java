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
public class UserDto {
  private Long userId;

  private String loginId;

  private String userName;

  private String password;

  private Integer loginFailCnt;

  private Date latestLoginDt;

  private Date latestLoginFailDt;

  private Date latestPasswordChangeDt;

  private UserSttus userSttus;

  private Long imageAtchmnflGroupId;

  private String userDc;

  private String registerId;

  private Date registDt;

  private String updaterId;

  private Date updateDt;
}
