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

import kr.co.dhecoenergy.sicsapi.domain.UserDto;
import kr.co.dhecoenergy.sicsapi.domain.UserSearchDto;
import kr.co.dhecoenergy.sicsapi.entity.Farm;
import kr.co.dhecoenergy.sicsapi.entity.User;
import kr.co.dhecoenergy.sicsapi.repository.UserRepository;
import kr.co.dhecoenergy.sicsapi.service.UserService;
import kr.vaiv.sdt.cmmn.misc.CmmnBeanUtils;
import kr.vaiv.sdt.cmmn.misc.CmmnResultMap;

@Service
public class UserServiceImpl implements UserService {
  @PersistenceContext
  private EntityManager em;

  private UserRepository repo;

  public UserServiceImpl(UserRepository repo) {
    super();

    this.repo = repo;
  }

  @Override
  public CmmnResultMap search(UserSearchDto searchDto, Pageable pageable) {
    //
    Specification<User> specification = new Specification<User>() {

      List<Predicate> predicates = new ArrayList<>();

      @Override
      @Nullable
      public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        predicates.add(criteriaBuilder.like(root.get("null"), "null"));
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
  @Transactional
  public Long regist(UserDto dto) throws Exception {
    dto.setRegistDt(new Date());
    User entity = CmmnBeanUtils.dtoToEntity(dto, User.class);

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

}
