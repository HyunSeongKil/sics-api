package kr.co.dhecoenergy.sicsapi.service;

import javax.servlet.http.HttpServletRequest;

public interface JwtService {
  long getUserId(HttpServletRequest request);

  long getUserId(String token);
}
