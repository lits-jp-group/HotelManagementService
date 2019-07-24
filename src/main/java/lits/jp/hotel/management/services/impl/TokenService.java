package lits.jp.hotel.management.services.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TokenService implements lits.jp.hotel.management.services.TokenService {

  private Logger logger = LoggerFactory.getLogger(getClass());

  private final byte[] secret;
  private final String userIdClaim = "userIdClaim";
  private final Long allowedClockSkewSeconds = 30L;
  private final JwtParser jwtParser;

  public TokenService(@Value("${security.token.secret}") String secret) {
    this.secret = secret.getBytes(StandardCharsets.UTF_8);
    jwtParser =
        Jwts.parser()
            .setSigningKey(this.secret)
            .setAllowedClockSkewSeconds(this.allowedClockSkewSeconds);
  }

  @Override
  public Long parseToken(String token) {
    try {
      Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
      final Claims body = claimsJws.getBody();

      return body.get(userIdClaim, Long.class);
    } catch (ExpiredJwtException e) {
      log.debug("JWT token has expired: {}", e.getMessage());
      throw new CredentialsExpiredException("JWT is not valid", e);
    } catch (JwtException e) {
      log.debug("JWT is not valid: {}", e.getMessage());
      throw new BadCredentialsException("JWT is not valid", e);
    }
  }

  public String createToken(Long id) {
    return Jwts.builder()
        .claim(userIdClaim, id)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
  }
}
