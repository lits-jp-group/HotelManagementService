package lits.jp.hotel.management.services.impl;

import lits.jp.hotel.management.models.StaffMember;
import lits.jp.hotel.management.repository.StaffMemberRepository;
import lits.jp.hotel.management.services.StaffMemberAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StaffMemberAuthServiceImpl implements StaffMemberAuthService {

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private TokenService tokenService;

  @Autowired private StaffMemberRepository staffMemberRepository;

  @Override
  public String auth(String lastname, String pass) {
    log.info("Begin authentification by StaffMemberAuthServiceImpl");

    final Authentication authentication =
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(lastname, pass));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    StaffMember user = staffMemberRepository.findOneByLastName(lastname);

    return tokenService.createToken(user.getStaffMemberId());
  }
}
