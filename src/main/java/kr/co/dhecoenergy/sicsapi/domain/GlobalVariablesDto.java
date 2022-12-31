package kr.co.dhecoenergy.sicsapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GlobalVariablesDto {
  private String key;

  private String value;

  private String groupName;

  private String dc;
}
