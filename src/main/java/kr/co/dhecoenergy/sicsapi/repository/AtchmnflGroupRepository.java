package kr.co.dhecoenergy.sicsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.co.dhecoenergy.sicsapi.entity.AtchmnflGroup;

@Repository
public interface AtchmnflGroupRepository extends JpaRepository<AtchmnflGroup, Long> {

}
