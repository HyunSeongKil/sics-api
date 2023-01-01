package kr.co.dhecoenergy.sicsapi.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dhecoenergy.sicsapi.domain.UserDto;
import kr.co.dhecoenergy.sicsapi.misc.SicsRestController;
import kr.co.dhecoenergy.sicsapi.service.UserService;
import kr.vaiv.sdt.cmmn.misc.CmmnResultMap;

@RestController
@RequestMapping("/users")
public class UserRestController extends SicsRestController {

  private UserService service;

  public UserRestController(UserService service) {
    super();

    this.service = service;
  }

  @PutMapping("/login")
  public ResponseEntity<CmmnResultMap> login(@RequestBody UserDto dto) {
    String resultCode = service.processLogin(dto.getLoginId(), dto.getPassword());
    if (0 != resultCode.length()) {
      return ResponseEntity.ok(CmmnResultMap.of(Map.of(), resultCode));
    }

    // jwt 생성
    String token = jwtService.createToken(service.getByLoginId(dto.getLoginId()));
    return ResponseEntity.ok(CmmnResultMap.withData(token));
  }

  @PutMapping()
  public void update(HttpServletRequest request, @RequestBody UserDto dto) throws Exception {
    dto.setUserId(jwtService.getUserId(request));

    service.update(dto);
  }

  @PutMapping("/password")
  public void updatePassword(HttpServletRequest request, @RequestBody UserDto dto) {
    service.updatePassword(jwtService.getUserId(request), dto.getPassword());
  }

  @DeleteMapping()
  public void delete(HttpServletRequest request) {
    service.deleteById(jwtService.getUserId(request));
  }

  @GetMapping()
  public ResponseEntity<CmmnResultMap> get(HttpServletRequest request) throws Exception {
    UserDto dto = service.getById(jwtService.getUserId(request));

    return ResponseEntity.ok(CmmnResultMap.withData(dto));
  }

}
