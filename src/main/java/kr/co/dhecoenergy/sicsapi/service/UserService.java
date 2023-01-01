package kr.co.dhecoenergy.sicsapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;

import kr.co.dhecoenergy.sicsapi.domain.UserDto;
import kr.co.dhecoenergy.sicsapi.domain.UserSearchDto;
import kr.co.dhecoenergy.sicsapi.domain.UserSttus;
import kr.vaiv.sdt.cmmn.misc.CmmnResultMap;

public interface UserService {
    String processLogin(String loginId, String password);

    // 검색 with 페이징
    CmmnResultMap search(UserSearchDto searchDto, Pageable pageable);

    // 1건 조회
    UserDto getById(Long userId) throws Exception;

    UserDto getByLoginId(String loginId);

    // 등록
    long regist(UserDto dto) throws Exception;

    // 수정
    void update(UserDto dto) throws Exception;

    void increaseLoginFailCntByLoginId(String loginId);

    void updateLoginFailCntByLoginId(String loginId, int loginFailCnt);

    void updateLatestLoginDtByLoginId(String loginId, Date date);

    void updateUserSttusByLoginId(String loginId, UserSttus userSttus);

    // 1건 삭제
    void deleteById(Long userId);

    void updatePassword(long userId, String password);

}
