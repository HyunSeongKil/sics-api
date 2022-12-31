package kr.co.dhecoenergy.sicsapi.service;

import java.util.List;

import kr.co.dhecoenergy.sicsapi.domain.DeviceDto;

public interface DeviceService {

  List<DeviceDto> getsByFarmId(long farmId);

  DeviceDto getById(long deviceId);

  void deleteById(long deviceId);

  void update(DeviceDto dto);

  long regist(DeviceDto dto);

}
