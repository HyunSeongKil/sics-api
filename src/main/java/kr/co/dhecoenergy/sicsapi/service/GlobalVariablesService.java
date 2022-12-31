package kr.co.dhecoenergy.sicsapi.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import kr.co.dhecoenergy.sicsapi.domain.GlobalVariablesDto;
import kr.co.dhecoenergy.sicsapi.domain.GlobalVariablesSearchDto;

public interface GlobalVariablesService {
  String regist(GlobalVariablesDto dto);

  void update(GlobalVariablesDto dto);

  void deleteByKey(String key);

  List<GlobalVariablesDto> search(GlobalVariablesSearchDto searchDto, Pageable pageable);

  GlobalVariablesDto getByKey(String key);
}
