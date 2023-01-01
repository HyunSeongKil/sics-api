package kr.co.dhecoenergy.sicsapi.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.co.dhecoenergy.sicsapi.domain.UserDto;
import kr.co.dhecoenergy.sicsapi.service.JwtService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

  @Value("${app.jwt.secret.key}")
  private String secretKey;

  @Override
  public long getUserId(HttpServletRequest request) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public long getUserId(String token) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String createToken(UserDto userDto) {

    //
    Map<String, Object> headerMap = new HashMap<>();
    headerMap.put("typ", "JWT");
    headerMap.put("alg", "HS256");

    //
    Map<String, Object> bodyMap = new HashMap<>();
    bodyMap.put("userId", userDto.getUserId());
    bodyMap.put("loginId", userDto.getLoginId());
    bodyMap.put("userName", userDto.getUserName());

    //
    Date expireTime = new Date();
    expireTime.setTime(expireTime.getTime() + 1000 * 60 * 1); // 1min

    //
    SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
    Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    //
    String token = Jwts.builder()
        .setHeader(headerMap)
        .setClaims(bodyMap)
        .setExpiration(expireTime)
        .signWith(signatureAlgorithm, signingKey)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .compact();

    log.info("body: {} token: {}", bodyMap, token);
    return token;

  }

  @Override
  public boolean validateToken(String token) {
    return null != getClaims(token);
  }

  private boolean isExpired(String token) {
    try {
      getClaims(token);

      return false;
    } catch (ExpiredJwtException exception) {
      return true;
    } catch (JwtException exception) {
      return false;
    }

  }

  private Claims getClaims(String token) throws ExpiredJwtException, JwtException {
    try {
      return Jwts.parser()
          .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
          .parseClaimsJws(token)
          .getBody();

    } catch (ExpiredJwtException exception) {
      throw exception;
    } catch (JwtException exception) {
      throw exception;
    }

  }

}
