package kr.co.dhecoenergy.sicsapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.dhecoenergy.sicsapi.domain.FarmDto;
import kr.co.dhecoenergy.sicsapi.entity.Farm;
import kr.co.dhecoenergy.sicsapi.misc.SicsUtils;
import kr.co.dhecoenergy.sicsapi.repository.FarmRepository;
import kr.co.dhecoenergy.sicsapi.service.FarmService;
import kr.vaiv.sdt.cmmn.misc.CmmnBeanUtils;

@Service
public class FarmServiceImpl implements FarmService {

  private FarmRepository repo;

  public FarmServiceImpl(FarmRepository repo) {
    super();
    this.repo = repo;
  }

  @Override
  @Transactional
  public long regist(FarmDto dto) {
    return repo.save(SicsUtils.dtoToEntity(dto, Farm.class))
        .getFarmId();
  }

  @Override
  @Transactional
  public void update(FarmDto dto) {
    // TODO Auto-generated method stub

  }

  @Override
  @Transactional
  public void deleteByFarmIdAndFarmerId(long farmId, long farmerId) {
    repo.deleteByFarmIdAndFarmerId(farmId, farmerId);
  }

  @Override
  @Transactional
  public void deletesByFarmerId(long farmerId) {
    repo.deleteAllByFarmerId(farmerId);
  }

  @Override
  public FarmDto getByFarmIdAndFarmerId(long farmId, long farmerId) {
    Optional<Farm> opt = repo.findByFarmIdAndFarmerId(farmId, farmerId);
    if (opt.isPresent()) {
      return SicsUtils.entityToDto(opt.get(), FarmDto.class);
    }

    return null;
  }

  @Override
  public List<FarmDto> getsByFarmerId(long farmerId) {
    List<Farm> entities = repo.findAllByFarmerId(farmerId);

    return entities.stream()
        .map(entity -> {
          return SicsUtils.entityToDto(entity, FarmDto.class);
        })
        .collect(Collectors.toList());
  }

  @Override
  public FarmDto getById(long farmId) {
    Optional<Farm> opt = repo.findById(farmId);
    if (opt.isPresent()) {
      return SicsUtils.entityToDto(opt.get(), FarmDto.class);
    }

    return null;
  }

}
