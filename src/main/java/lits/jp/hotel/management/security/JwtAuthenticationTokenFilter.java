package lits.jp.hotel.management.security;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lits.jp.hotel.management.services.TokenService;
import lits.jp.hotel.management.services.impl.StaffMemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  private static final String BEARER_TYPE = "Bearer";

  @Autowired private StaffMemberServiceImpl staffMemberService;

  @Autowired private TokenService tokenService;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    Long accountId =
        Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
            .filter(this::containsBearerToken)
            .map(token -> token.substring(BEARER_TYPE.length() + 1))
            .map(token -> tokenService.parseToken(token))
            .orElse(null);
    log.debug("Exception in class JwtAuthenticationTokenFilter (doFilterInternal)");
    log.info("Checking authentication for user " + accountId);
    log.info("Long account ID(by JwtAuthenticationTokenFilter  = " + accountId);

    if (accountId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      JwtUser jwtUser =
          JwtUserFactory.create(
              accountId, staffMemberService.getAuthority(staffMemberService.findOne(accountId)));

      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(jwtUser, null, jwtUser.getAuthorities());
      authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
      log.info("authenticated user " + accountId + ", setting security context");
      SecurityContextHolder.getContext().setAuthentication(authentication);
    } else {
      log.info("Problem from JwtAuthenticationTokenFilter. account-ID from token is " + accountId);
    }
    chain.doFilter(request, response);
  }

  private boolean containsBearerToken(String authHeader) {
    return Optional.ofNullable(authHeader)
        .filter(e -> e.startsWith(BEARER_TYPE))
        .filter(e -> e.length() > BEARER_TYPE.length() + 1)
        .isPresent();
  }
}
