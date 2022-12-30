package kr.co.dhecoenergy.sicsapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldRestController {

  @GetMapping("/greeting")
  public ResponseEntity<String> greeting() {
    return ResponseEntity.ok("hello world");
  }
}
