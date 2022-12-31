package kr.co.dhecoenergy.sicsapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.dhecoenergy.sicsapi.entity.Atchmnfl;

@Repository
public interface AtchmnflRepository extends JpaRepository<Atchmnfl, Long> {

  List<Atchmnfl> findAllByAtchmnflGroupId(Long atchmnflGroupId);

  int countByAtchmnflGroupId(Long atchmnflGroupId);

}
