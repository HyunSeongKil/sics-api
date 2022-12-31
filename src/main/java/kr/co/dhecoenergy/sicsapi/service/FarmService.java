package kr.co.dhecoenergy.sicsapi.service;

import java.util.List;

import kr.co.dhecoenergy.sicsapi.domain.FarmDto;

public interface FarmService {
  long regist(FarmDto dto);

  void update(FarmDto dto);

  void deleteByFarmIdAndFarmerId(long farmId, long farmerId);

  void deletesByFarmerId(long farmerId);

  FarmDto getByFarmIdAndFarmerId(long farmId, long farmerId);

  List<FarmDto> getsByFarmerId(long farmerId);

  FarmDto getById(long farmId);

}
