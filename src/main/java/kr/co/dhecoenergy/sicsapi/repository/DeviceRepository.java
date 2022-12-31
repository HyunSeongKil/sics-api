package kr.co.dhecoenergy.sicsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.dhecoenergy.sicsapi.entity.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {

  List<Device> findAllByFarmId(long farmId);

}
