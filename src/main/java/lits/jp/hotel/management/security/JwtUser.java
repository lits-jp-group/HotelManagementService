package lits.jp.hotel.management.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser extends User implements UserDetails {
  private final Long id;
  private final Collection<? extends GrantedAuthority> authorities;

  public JwtUser(Long id, Collection<? extends GrantedAuthority> authorities) {
    super(String.valueOf(id), "", authorities);
    this.id = id;
    this.authorities = authorities;
  }

  public JwtUser(
      Long id,
      String username,
      String password,
      Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.id = id;
    this.authorities = authorities;
  }

  public JwtUser(
      String username,
      String password,
      Collection<? extends GrantedAuthority> authorities,
      Long id) {
    super(username, password, authorities);
    this.id = id;
    this.authorities = authorities;
  }

  @JsonIgnore
  public Long getId() {
    return id;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return null;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return true;
  }
}
