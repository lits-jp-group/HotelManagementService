package lits.jp.hotel.management.services.impl;

import java.util.Arrays;
import java.util.Objects;
import lits.jp.hotel.management.exceptions.StaffMemberException;
import lits.jp.hotel.management.models.StaffMember;
import lits.jp.hotel.management.repository.StaffMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "staffMemberService")
public class StaffMemberServiceImpl implements UserDetailsService {

  @Autowired private StaffMemberRepository staffMemberRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    StaffMember user = staffMemberRepository.findOneByLastName(s);
    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException("Invalid username or password.");
    }
    log.info("User from StaffMemberServiceImpl is " + user);
    return new org.springframework.security.core.userdetails.User(
        user.getLastName(),
        user.getPassword(),
        Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
  }

  public StaffMember saveNewStaffMember(StaffMember staffMember) throws StaffMemberException {

    boolean staffMemberIsInDB = false;
    Iterable<StaffMember> staffMembersFromDB = staffMemberRepository.findAll();
    for (StaffMember iteratorNext : staffMembersFromDB) {
      if (iteratorNext.getLastName().equals(staffMember.getLastName())) staffMemberIsInDB = true;
    }

    if (!staffMemberIsInDB) {
      staffMember.setPassword(passwordEncoder.encode(staffMember.getPassword()));
      return staffMemberRepository.save(staffMember);
    } else {
      log.info("The StaffMember is in DB already");
      throw new StaffMemberException("The StaffMember is in DB already");
    }
  }
}
