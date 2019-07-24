package lits.jp.hotel.management.services.impl;

import lits.jp.hotel.management.models.StaffMember;
import lits.jp.hotel.management.repository.StaffMemberRepository;
import lits.jp.hotel.management.services.StaffMemberAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class StaffMemberAuthServiceImpl implements StaffMemberAuthService {

  @Autowired private AuthenticationManager authenticationManager;

  @Autowired private TokenService tokenService;

  @Autowired private StaffMemberRepository staffMemberRepository;

  @Override
  public String auth(String username, String pass) {
    final Authentication authentication =
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, pass));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    StaffMember user = staffMemberRepository.findOneByLastName(username);

    System.out.println("message from authServiceImpl");
    return tokenService.createToken(user.getStaffMemberId());
  }
}
