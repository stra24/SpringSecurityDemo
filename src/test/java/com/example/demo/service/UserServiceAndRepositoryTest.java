package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.dto.UserDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service～Repositoryを通したユニットテストサンプル
 */
@SpringBootTest(webEnvironment = WebEnvironment.NONE) // WebまわりのConfigurationはBean生成を無効にする。
@Transactional // これにより、各テストメソッド終了時にテストデータがロールバックされる。
public class UserServiceAndRepositoryTest {

  @Autowired
  UserService userService;

  @Test
  @Sql("../mapper/insertUserList.sql")
  void testFindAll() {
    List<UserDto> userDtoList = userService.findAll();
    UserDto userDto = userDtoList.stream().findFirst().orElseThrow();

    assertEquals("test-taro", userDto.getName());
    assertEquals("pass", userDto.getPassword());
    assertEquals("ADMIN", userDto.getAuthority());
  }
}
