package kr.co.dhecoenergy.sicsapi.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.type.descriptor.sql.JdbcTypeFamilyInformation.Family;
import org.springframework.stereotype.Service;

import kr.co.dhecoenergy.sicsapi.domain.DeviceDto;
import kr.co.dhecoenergy.sicsapi.domain.FarmDto;
import kr.co.dhecoenergy.sicsapi.entity.Device;
import kr.co.dhecoenergy.sicsapi.misc.SicsUtils;
import kr.co.dhecoenergy.sicsapi.repository.DeviceRepository;
import kr.co.dhecoenergy.sicsapi.service.DeviceService;
import kr.co.dhecoenergy.sicsapi.service.FarmService;

@Service
public class DeviceServiceImpl implements DeviceService {
  private DeviceRepository repo;

  public DeviceServiceImpl(DeviceRepository repo) {
    super();
    this.repo = repo;
  }

  @Override
  public List<DeviceDto> getsByFarmId(long farmId) {

    List<Device> entities = repo.findAllByFarmId(farmId);
    return entities.stream()
        .map(entity -> SicsUtils.entityToDto(entity, DeviceDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public DeviceDto getById(long deviceId) {
    Optional<Device> opt = repo.findById(deviceId);
    if (opt.isPresent()) {
      return SicsUtils.entityToDto(opt.get(), DeviceDto.class);
    }

    return null;
  }

  @Override
  public void deleteById(long deviceId) {
    repo.deleteById(deviceId);
  }

  @Override
  public void update(DeviceDto dto) {
    // TODO Auto-generated method stub

  }

  @Override
  public long regist(DeviceDto dto) {
    Device entity = SicsUtils.dtoToEntity(dto, Device.class);
    return repo.save(entity)
        .getDeviceId();
  }
}
