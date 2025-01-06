package com.example.demo.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.config.SecurityConfig;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Controllerのユニットテストサンプル
 */
@WebMvcTest(RegisterController.class) // DIコンテナを生成してくれる。引数のクラスのBeanが用意される。MockMvcのBeanも用意される。
@Import(SecurityConfig.class) // @SpringBootTestを使っていないため、SecurityConfigのBeanを明示的に用意する。
public class RegisterControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  UserService userService;

  @Test
  void test_POST_register() throws Exception {
    mockMvc.perform(
            post("/register")
                .param("name", "test-taro")
                .param("password", "pass")
                .param("authority", "ADMIN")
                .with(csrf())
        )
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrl("/login"));
  }
}
