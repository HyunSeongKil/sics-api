package kr.co.dhecoenergy.sicsapi.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import kr.co.dhecoenergy.sicsapi.domain.AtchmnflGroupDto;
import kr.co.dhecoenergy.sicsapi.entity.AtchmnflGroup;
import kr.co.dhecoenergy.sicsapi.repository.AtchmnflGroupRepository;
import kr.co.dhecoenergy.sicsapi.service.AtchmnflGroupService;
import kr.vaiv.sdt.cmmn.misc.CmmnBeanUtils;

@Service
public class AtchmnflGroupServiceImpl implements AtchmnflGroupService {

  private AtchmnflGroupRepository repo;

  public AtchmnflGroupServiceImpl(AtchmnflGroupRepository repo) {
    super();
    this.repo = repo;
  }

  @Override
  @Transactional
  public long regist() {
    AtchmnflGroup entity = AtchmnflGroup.builder().registDt(new Date()).build();
    return repo.save(entity).getAtchmnflGroupId();
  }

  @Override
  @Transactional
  public void deleteById(Long atchmnflGroupId) {
    repo.deleteById(atchmnflGroupId);
  }

  @Override
  public void update(AtchmnflGroupDto dto) {
    //
    Optional<AtchmnflGroup> opt = repo.findById(dto.getAtchmnflGroupId());
    if (opt.isEmpty()) {
      throw new NotFoundException("null");
    }

    //
    AtchmnflGroup entity = opt.get();
    AtchmnflGroup newEntity = AtchmnflGroup.builder()
        .atchmnflGroupId(entity.getAtchmnflGroupId())
        .registDt(entity.getRegistDt())
        .updateDt(new Date())
        .build();

    //
    repo.save(newEntity);
  }

}
