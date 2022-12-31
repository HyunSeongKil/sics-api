package kr.co.dhecoenergy.sicsapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import kr.co.dhecoenergy.sicsapi.entity.GlobalVariables;

public interface GlobalVariablesRepository
    extends JpaRepository<GlobalVariables, String>, JpaSpecificationExecutor<GlobalVariables> {

}
