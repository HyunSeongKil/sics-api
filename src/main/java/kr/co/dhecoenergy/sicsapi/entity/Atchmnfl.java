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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "atchmnfl")
public class Atchmnfl {
  @Id
  @Column(name = "atchmnfl_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long atchmnflId;

  @Column(name = "atchmnfl_group_id")
  private long atchmnflGroupId;

  private String savedSubPath;

  private String originalFilename;

  private String savedFilename;

  private long fileSize;

  private String contentType;

  @Column(name = "register_id")
  private String registerId;

  @Column(name = "regist_dt")
  private Date registDt;

  @Column(name = "updater_id")
  private String updaterId;

  @Column(name = "update_dt")
  private Date updateDt;

}
