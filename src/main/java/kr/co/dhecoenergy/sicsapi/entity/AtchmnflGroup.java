package kr.co.dhecoenergy.sicsapi.entity;

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
@Table(name = "atchmnfl_group")
public class AtchmnflGroup {
  @Id
  @Column(name = "atchmnfl_group_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long atchmnflGroupId;

}
