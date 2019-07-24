package lits.jp.hotel.management.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtUserFactory {
  private JwtUserFactory() {}

  public static JwtUser create(Long accountId, List<GrantedAuthority> roles) {
    return new JwtUser(accountId, roles);
  }

  private static List<GrantedAuthority> mapToGrantedAuthorities(String role) {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(role));
    return authorities;
  }
}
