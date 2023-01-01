package kr.co.dhecoenergy.sicsapi.service;

import javax.servlet.http.HttpServletRequest;

import kr.co.dhecoenergy.sicsapi.domain.UserDto;

public interface JwtService {
  long getUserId(HttpServletRequest request);

  long getUserId(String token);

  String createToken(UserDto userDto);

  boolean validateToken(String token);
}
