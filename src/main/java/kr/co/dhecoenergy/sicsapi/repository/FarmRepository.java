package kr.co.dhecoenergy.sicsapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.dhecoenergy.sicsapi.entity.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long> {

  List<Farm> findAllByFarmerId(long userId);

  void deleteAllByFarmerId(long userId);

  void deleteByFarmIdAndFarmerId(long farmId, long farmerId);

  Optional<Farm> findByFarmIdAndFarmerId(long farmId, long farmerId);

}
