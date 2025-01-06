package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

import com.example.demo.dto.UserDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Serviceのユニットテストサンプル
 */
// テスト時にこのクラスの処理（Mockitoのアノテーションの検知）を呼び出してくれて、@InjectMocksや@Mockが使えるようになる。
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  // コンストラクタを呼び出し、@Mockがついているものを引数にインジェクションしてくれる。
  @InjectMocks
  UserService userService;

  @Mock
  UserMapper userMapper;

  @Mock
  PasswordEncoder passwordEncoder;

  @Test
  void testFindAll() {
    User user = new User(
        "id",
        "test-taro",
        "pass",
        "ADMIN"
    );
    doReturn(List.of(user)).when(userMapper).findAll();

    List<UserDto> userDtoList = userService.findAll();
    UserDto userDto = userDtoList.stream().findFirst().orElseThrow();

    assertEquals(user.getName(), userDto.getName());
    assertEquals(user.getPassword(), userDto.getPassword());
    assertEquals(user.getAuthority(), userDto.getAuthority());
  }
}
