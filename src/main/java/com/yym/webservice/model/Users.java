package com.yym.webservice.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
public class Users extends BaseTimeEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;	//

  @Column(nullable = false, unique = true)
  private String username;  // 아이디

  @Column(nullable = false)
  private String password;  // 비밀번호

  @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name="userId")
  private List<History> history;

  @Builder
  public Users(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public void addHistory(History history){
    if(this.history == null){
      this.history = new ArrayList<>();
    }
    this.history.add(history);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
    list.add(new SimpleGrantedAuthority("ADMIN"));
    return list;
  }

  @Override
  public String getUsername() {
    return this.username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}