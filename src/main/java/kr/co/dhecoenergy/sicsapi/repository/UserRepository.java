package kr.co.dhecoenergy.sicsapi.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.co.dhecoenergy.sicsapi.domain.UserSttus;
import kr.co.dhecoenergy.sicsapi.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

  Optional<User> getByLoginId(String loginId);

  @Modifying
  @Query(value = "UPDATE user SET login_fail_cnt = login_fail_cnt + 1, update_dt = now() WHERE login_id = :loginId", nativeQuery = true)
  void increaseLoginFailCntByLoginId(@Param("loginId") String loginId);

  @Modifying
  @Query(value = "UPDATE user SET latest_login_dt = :latestLoginDt, update_dt = now() WHERE login_id = :loginId ", nativeQuery = true)
  void updateLatestLoginDt(@Param("loginId") String loginId, @Param("latestLoginDt") Date latestLoginDt);

  Optional<User> findByLoginId(String loginId);

  @Modifying
  @Query(value = "UPDATE user SET user_sttus = :userSttus, update_dt = now() WHERE login_id = :loginId", nativeQuery = true)
  void updateUserSttusByLoginId(@Param("loginId") String loginId, @Param("userSttus") String userSttus);

  @Modifying
  @Query(value = "UPDATE user SET login_fail_cnt = :loginFailCnt, update_dt = now() WHERE login_id = :loginId", nativeQuery = true)
  void updateLoginFailCntByLoginId(@Param("loginId") String loginId, @Param("loginFailCnt") int loginFailCnt);

  @Modifying
  @Query(value = "UPDATE user SET latest_login_failt_dt = :latestLoginFailDt, update_dt = now() WHERE login_id = :loginId", nativeQuery = true)
  void updateLatestLoginFailDtByLoginDt(@Param("loginId") String loginId, @Param("latestLoginFailDt") Date date);

  @Modifying
  @Query(value = "UPDATE user SET password = :password, latest_password_change_dt = now(), update_dt = now() WHERE user_id = :userId", nativeQuery = true)
  void updatePassword(@Param("userId") long userId, @Param("password") String password);

}
