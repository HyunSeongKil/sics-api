package kr.co.dhecoenergy.sicsapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtchmnflDto {
  private Long atchmnflId;

  private Long atchmnflGroupId;

  private String originalFilename;

  private String savedFilename;

  private Long fileSize;

  private String savedSubPath;

  private String contentType;
}
