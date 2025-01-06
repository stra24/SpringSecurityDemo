package com.example.demo.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Controller～Service～Repositoryのユニットテストサンプル
 */
@SpringBootTest
@AutoConfigureMockMvc // MockMvcのBeanを用意するためのもの。
@Transactional
public class LoginControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void test_GET_login_authorized() throws Exception {
    mockMvc.perform(
            get("/login")
                .with(csrf())
                .with(user("test-taro").password("pass"))
        )
        .andExpect(status().is3xxRedirection());
  }

  @Test
  @Sql("../mapper/insertUserList.sql")
  void test_GET_login_notAuthorized() throws Exception {
    mockMvc.perform(
            get("/login")
                .with(csrf())
        )
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("ログイン")));
  }
}
