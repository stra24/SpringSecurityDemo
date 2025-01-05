package com.example.demo.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.demo.model.User;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

/**
 * Repositoryのユニットテストサンプル
 */
// MapperだけBean登録する（ControllerやServiceはBean登録しない）
// @MybatisTestの中に@Transactionalも含まれている。
@MybatisTest
// これを指定しないと、デフォルトで組み込みDBを使用する設定が適用されるが、MySQLを使用したいので指定している。
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {

  @Autowired
  UserMapper userMapper;

  @Test
  @Sql("insertUserList.sql")
  void testFindAll() {
    List<User> userList = userMapper.findAll();
    User user = userList.stream().findFirst().orElseThrow();
    assertEquals("test-taro", user.getName());
  }
}
