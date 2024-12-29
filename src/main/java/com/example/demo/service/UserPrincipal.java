package com.example.demo.service;

import com.example.demo.model.Authority;
import com.example.demo.model.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserPrincipal implements UserDetails {

  private final User user;

  // コンストラクタでUserオブジェクトを受け取り、それをこのクラスのuserにセットします。
  public UserPrincipal(User user) {
    this.user = user;
  }

  // すべてのユーザーに与えられる権限
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<SimpleGrantedAuthority> authorityList = new ArrayList<>(
        Collections.singleton(
            new SimpleGrantedAuthority(Authority.GENERAL.name())
        )
    );
    if (Authority.ADMIN.name().equals(user.getAuthority())) {
      authorityList.add(new SimpleGrantedAuthority(Authority.ADMIN.name()));
    }
    return authorityList;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getName();
  }
}
