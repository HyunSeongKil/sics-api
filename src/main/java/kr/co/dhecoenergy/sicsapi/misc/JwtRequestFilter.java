package kr.co.dhecoenergy.sicsapi.misc;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import kr.co.dhecoenergy.sicsapi.service.JwtService;
import kr.co.dhecoenergy.sicsapi.service.impl.JwtUserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {

  private JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl;
  private JwtService jwtService;

  public JwtRequestFilter(JwtUserDetailsServiceImpl jwtUserDetailsServiceImpl, JwtService jwtService) {
    super();

    this.jwtUserDetailsServiceImpl = jwtUserDetailsServiceImpl;
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String requestTokenHeader = request.getHeader("Authorization");

    String username = null;
    String jwtToken = null;

    // JWT 토큰은 "Beare token"에 있다. Bearer단어를 제거하고 토큰만 받는다.
    if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
      jwtToken = requestTokenHeader.substring(7);
      try {
        username = jwtTokenUtil.getUsernameFromToken(jwtToken);
      } catch (IllegalArgumentException ex) {
        log.error("Unable to get JWT token", ex);
      } catch (ExpiredJwtException ex) {
        log.error("JWT Token has expired", ex);
        throw new ExpiredJwtException("JWT Token has expired");
      } catch (UsernameFromTokenException ex) {
        log.error("token valid error:" + ex.getMessage(), ex);
        throw new UsernameFromTokenException("Username from token error");
      } catch (Exception ex) {
        log.error("token valid error:" + ex.getMessage(), ex);
        throw new RuntimeException("11 Username from token error");
      }
    } else {
      log.warn("JWT token does not begin with Bearer String");
    }

    // 토큰을 가져오면 검증을 한다.
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.jwtUserDetailsServiceImpl.loadUserByUsername(username);

      // 토큰이 유효한 경우 수동으로 인증을 설정하도록 스프링 시큐리티를 구성한다.
      if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));

        // 컨텍스트에 인증을 설정 한 후 현재 사용자가 인증되도록 지정한다.
        // 그래서 Spring Security 설정이 성공적으로 넘어간다.
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }

    filterChain.doFilter(request, response);

  }

}
