package kr.co.dhecoenergy.sicsapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dhecoenergy.sicsapi.service.UserService;
import kr.vaiv.sdt.cmmn.misc.CmmnResultMap;

@RestController
@RequestMapping("/users")
public class UserRestController {

  private UserService service;

  public UserRestController(UserService service) {
    super();

    this.service = service;
  }

}
