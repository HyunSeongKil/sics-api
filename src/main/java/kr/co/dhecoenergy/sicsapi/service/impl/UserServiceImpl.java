package kr.co.dhecoenergy.sicsapi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.dhecoenergy.sicsapi.domain.GlobalVariables;
import kr.co.dhecoenergy.sicsapi.domain.GlobalVariablesDto;
import kr.co.dhecoenergy.sicsapi.domain.UserDto;
import kr.co.dhecoenergy.sicsapi.domain.UserSearchDto;
import kr.co.dhecoenergy.sicsapi.domain.UserSttus;
import kr.co.dhecoenergy.sicsapi.entity.Farm;
import kr.co.dhecoenergy.sicsapi.entity.User;
import kr.co.dhecoenergy.sicsapi.repository.UserRepository;
import kr.co.dhecoenergy.sicsapi.service.GlobalVariablesService;
import kr.co.dhecoenergy.sicsapi.service.UserService;
import kr.vaiv.sdt.cmmn.misc.CmmnBeanUtils;
import kr.vaiv.sdt.cmmn.misc.CmmnCrypto;
import kr.vaiv.sdt.cmmn.misc.CmmnResultMap;
import kr.vaiv.sdt.cmmn.misc.CmmnUtils;

@Service
public class UserServiceImpl implements UserService {
  @PersistenceContext
  private EntityManager em;

  private UserRepository repo;
  private GlobalVariablesService globalVariablesService;

  public UserServiceImpl(UserRepository repo, GlobalVariablesService globalVariablesService) {
    super();

    this.repo = repo;
    this.globalVariablesService = globalVariablesService;
  }

  @Override
  public CmmnResultMap search(UserSearchDto searchDto, Pageable pageable) {
    //
    Specification<User> specification = new Specification<User>() {

      List<Predicate> predicates = new ArrayList<>();

      @Override
      @Nullable
      public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (CmmnUtils.isNotEmpty(searchDto.getUserName())) {
          predicates.add(criteriaBuilder.like(root.get("userName"), searchDto.getUserName()));
        }
        // TODO Auto-generated method stub

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
      }
    };

    //
    Page<User> page = repo.findAll(specification, pageable);

    //
    List<User> entities = page.getContent();
    List<UserDto> dtos = entities.stream().map(x -> {
      try {
        return CmmnBeanUtils.entityToDto(x, UserDto.class);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }).collect(Collectors.toList());

    //
    return CmmnResultMap.of(dtos, pageable, page.getTotalElements());
  }

  @Override
  public UserDto getById(Long userId) throws Exception {
    Optional<User> opt = repo.findById(userId);
    if (opt.isEmpty()) {
      return null;
    }

    return CmmnBeanUtils.entityToDto(opt.get(), UserDto.class);
  }

  @Override
  public UserDto getByLoginId(String loginId) {
    Optional<User> opt = repo.findByLoginId(loginId);

    try {
      return CmmnBeanUtils.entityToDto(opt.get(), UserDto.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  @Transactional
  public long regist(UserDto dto) throws Exception {
    User entity = User.builder()
        .loginId(dto.getLoginId())
        .password(CmmnCrypto.sha512(dto.getPassword()))
        .userSttus(dto.getUserSttus())
        .registDt(new Date())
        .build();

    return repo.save(entity).getUserId();
  }

  @Override
  @Transactional
  public void update(UserDto dto) throws Exception {
    repo.save(CmmnBeanUtils.dtoToEntity(dto, User.class));
  }

  @Override
  @Transactional
  public void deleteById(Long userId) {
    repo.deleteById(userId);
  }

  @Override
  @Transactional
  public String processLogin(String loginId, String password) {
    // loginId 확인
    Optional<User> opt = repo.getByLoginId(loginId);
    if (opt.isEmpty()) {
      return "E_01";
    }

    //
    User entity = opt.get();

    // 상태 확인
    if (UserSttus.OK != entity.getUserSttus()) {
      return "E_03";
    }

    // password 확인
    if (!entity.getPassword().equals(CmmnCrypto.sha512(password))) {
      //
      increaseLoginFailCntByLoginId(loginId);
      repo.updateLatestLoginFailDtByLoginDt(loginId, new Date());

      // 실패 최대횟수 초과이면 상태 잠그기
      lockAccountIfExceedLoginFailCnt(loginId);

      return "E_02";
    }

    //// 로그인 성공

    // clear 로그인 실패 횟수
    updateLoginFailCntByLoginId(loginId, 0);
    // update 최근 로그인 일시
    updateLatestLoginDtByLoginId(loginId, new Date());

    return "";
  }

  @Transactional
  private void lockAccountIfExceedLoginFailCnt(String loginId) {
    GlobalVariablesDto globalVariablesDto = globalVariablesService
        .getByKey(GlobalVariables.MAX_LOGIN_FAIL_CNT.toString());
    if (null == globalVariablesDto) {
      return;
    }

    //
    UserDto dto = getByLoginId(loginId);
    if (dto.getLoginFailCnt() < Integer.valueOf(globalVariablesDto.getValue())) {
      return;
    }

    //
    updateUserSttusByLoginId(loginId, UserSttus.BLOCKED);
  }

  @Override
  public void increaseLoginFailCntByLoginId(String loginId) {
    repo.increaseLoginFailCntByLoginId(loginId);
  }

  @Override
  public void updateLatestLoginDtByLoginId(String loginId, Date latestLoginDt) {
    repo.updateLatestLoginDt(loginId, latestLoginDt);
  }

  @Override
  @Transactional
  public void updateUserSttusByLoginId(String loginId, UserSttus userSttus) {
    repo.updateUserSttusByLoginId(loginId, userSttus.toString());
  }

  @Override
  public void updateLoginFailCntByLoginId(String loginId, int loginFailCnt) {
    repo.updateLoginFailCntByLoginId(loginId, loginFailCnt);
  }

  @Override
  public void updatePassword(long userId, String password) {
    repo.updatePassword(userId, CmmnCrypto.sha512(password));

    // TODO Auto-generated method stub

  }

}
