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
  private String userName;

  private String registerId;
  private Date registDt;
  private String updaterId;
  private Date updateDt;
}
