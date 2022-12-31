package kr.co.dhecoenergy.sicsapi.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kr.co.dhecoenergy.sicsapi.domain.GlobalVariablesDto;
import kr.co.dhecoenergy.sicsapi.domain.GlobalVariablesSearchDto;
import kr.co.dhecoenergy.sicsapi.entity.GlobalVariables;
import kr.co.dhecoenergy.sicsapi.misc.SicsUtils;
import kr.co.dhecoenergy.sicsapi.repository.GlobalVariablesRepository;
import kr.co.dhecoenergy.sicsapi.service.GlobalVariablesService;
import kr.vaiv.sdt.cmmn.misc.CmmnBeanUtils;

@Service
public class GlobalVariablesServceImpl implements GlobalVariablesService {

  private GlobalVariablesRepository repo;

  public GlobalVariablesServceImpl(GlobalVariablesRepository repo) {
    super();
    this.repo = repo;
  }

  @Override
  public String regist(GlobalVariablesDto dto) {
    GlobalVariables entity = SicsUtils.dtoToEntity(dto, GlobalVariables.class);

    return repo.save(entity).getKey();
  }

  @Override
  public void update(GlobalVariablesDto dto) {
    GlobalVariables entity = GlobalVariables.builder()
        .key(dto.getKey())
        .value(dto.getValue())
        .dc(dto.getDc())
        .groupName(dto.getGroupName())
        .updateDt(new Date())
        .build();

    repo.save(entity);

  }

  @Override
  @Transactional
  public void deleteByKey(String key) {
    repo.deleteById(key);
  }

  @Override
  public List<GlobalVariablesDto> search(GlobalVariablesSearchDto searchDto, Pageable pageable) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Cacheable(cacheNames = "GlobalVariablesGetByKeyCache")
  public GlobalVariablesDto getByKey(String key) {
    Optional<GlobalVariables> opt = repo.findById(key);

    try {
      return CmmnBeanUtils.entityToDto(opt.get(), GlobalVariablesDto.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
