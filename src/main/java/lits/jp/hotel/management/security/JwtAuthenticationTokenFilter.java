package lits.jp.hotel.management.security;

import lits.jp.hotel.management.services.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private final Log logger = LogFactory.getLog(this.getClass());

    private static final String BEARER_TYPE = "Bearer";

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        Long accountId =  Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION))
                .filter(this::containsBearerToken)
                .map(token -> token.substring(BEARER_TYPE.length() + 1))
                .map(token -> tokenService.parseToken(token))
                .orElse(null);
        log.debug("Exception in class JwtAuthenticationTokenFilter (doFilterInternal)");
        logger.info("checking authentication for user " + accountId);

        System.out.println("long account ID(by JwtAuthenticationTokenFilter  = "+accountId); // here is a probles. accountId is null here.

        if (accountId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            JwtUser jwtUser = JwtUserFactory.create(accountId, "ADMIN");

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtUser,
                    null, jwtUser.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            logger.info("authenticated user " + accountId + ", setting security context");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        else {

            System.out.println("Problem from JwtAuthenticationTokenFilter. account-ID from token is "+accountId);
        }

        chain.doFilter(request, response);
    }
    private boolean containsBearerToken(String authHeader) {
        return Optional.ofNullable(authHeader).filter(e -> e.startsWith(BEARER_TYPE))
                .filter(e -> e.length() > BEARER_TYPE.length() + 1).isPresent();
    }
}
