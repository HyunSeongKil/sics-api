package kr.co.dhecoenergy.sicsapi.controller;

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

@RestController
@RequestMapping("/users")
public class UserRestController extends SicsRestController {

  private UserService service;

  public UserRestController(UserService service) {
    super();

    this.service = service;
  }

  @PutMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserDto dto) {
    String resultCode = service.login(dto.getLoginId(), dto.getPassword());
    if (0 != resultCode.length()) {
      return ResponseEntity.ok(resultCode);
    }

    // jwt 생성

    return null;
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
  public ResponseEntity<UserDto> get(HttpServletRequest request) throws Exception {
    return ResponseEntity.ok(service.getById(jwtService.getUserId(request)));
  }

}
