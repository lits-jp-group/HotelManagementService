package lits.jp.hotel.management.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lits.jp.hotel.management.exceptions.StaffMemberException;
import lits.jp.hotel.management.models.StaffMember;
import lits.jp.hotel.management.repository.StaffMemberRepository;
import lits.jp.hotel.management.services.StaffMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "staffMemberService")
public class StaffMemberServiceImpl implements StaffMemberService, UserDetailsService {

  @Autowired private StaffMemberRepository staffMemberRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public List<StaffMember> findAll() {
    List<StaffMember> staffMemberList = new ArrayList<>();
    Iterable<StaffMember> result = staffMemberRepository.findAll();
    for (StaffMember iterator : result) {
      staffMemberList.add(iterator);
    }
    return staffMemberList;
  }

  @Override
  public StaffMember findOne(Long id) {
    return staffMemberRepository
        .findById(id)
        .orElseThrow(() -> new StaffMemberException("StaffMember with id: " + id + " not found"));
  }

  @Override
  public StaffMember save(StaffMember staffMember) throws StaffMemberException {

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

  @Override
  public void delete(Long id) {
    staffMemberRepository.deleteById(id);
  }

  public List<GrantedAuthority> getAuthority(StaffMember user) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    user.getRoles()
        .forEach(
            role -> {
              authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            });
    return authorities;
  }

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
}
