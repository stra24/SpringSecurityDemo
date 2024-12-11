package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("ユーザーが見つかりません。");
        }
        return new UserPrincipal(user);
    }

    public User findByUsername(String username) {
        return userMapper.findByName(username);
    }

    @Transactional
    public void save(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // パスワードをハッシュ化してから保存
        user.setAuthority(userDto.getAuthority());

        userMapper.save(user);
    }
}