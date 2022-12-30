package kr.co.dhecoenergy.sicsapi.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import kr.co.dhecoenergy.sicsapi.domain.UserDto;
import kr.co.dhecoenergy.sicsapi.domain.UserSearchDto;
import kr.vaiv.sdt.cmmn.misc.CmmnResultMap;

public interface UserService {
    // 검색 with 페이징
    CmmnResultMap search(UserSearchDto searchDto, Pageable pageable);

    // 1건 조회
    UserDto getById(Long userId) throws Exception;

    // 등록
    Long regist(UserDto dto) throws Exception;

    // 수정
    void update(UserDto dto) throws Exception;

    // 1건 삭제
    void deleteById(Long userId);

}
