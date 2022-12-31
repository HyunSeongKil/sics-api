package kr.co.dhecoenergy.sicsapi.service;

import kr.co.dhecoenergy.sicsapi.domain.AtchmnflGroupDto;

public interface AtchmnflGroupService {
  long regist();

  void update(AtchmnflGroupDto dto);

  void deleteById(Long atchmnflGroupId);
}
