package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class LoginController {

  private final UserService userService;

  @GetMapping("/login")
  public String login(Authentication authentication, Model model) {
    if (authentication != null && authentication.isAuthenticated()) {
      // 認証済みならマイページにリダイレクト
      return "redirect:/mypage";
    }
    List<UserDto> userDtoList = userService.findAll();
    model.addAttribute(userDtoList);
    // 未認証ならログイン画面を表示
    return "login";
  }

  @GetMapping("/mypage")
  public String myPage() {
    return "mypage";
  }
}