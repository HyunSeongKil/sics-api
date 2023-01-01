package kr.co.dhecoenergy.sicsapi.service.impl;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.dhecoenergy.sicsapi.domain.UserDto;
import kr.co.dhecoenergy.sicsapi.service.UserService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

  private UserService userService;

  public JwtUserDetailsServiceImpl(UserService userService) {
    super();
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // username == loginId
    UserDto userDto = userService.getByLoginId(username);
    if (null == userDto) {
      throw new UsernameNotFoundException("username not found");
    }

    return new User(userDto.getLoginId(), userDto.getPassword(), List.of());
  }

}
