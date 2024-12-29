package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class RegisterController {

  private UserService userService;

  @GetMapping("/register")
  public ModelAndView registerForm() {
    ModelAndView mav = new ModelAndView();
    mav.addObject("user", new UserDto()); // ビューで参照できるモデル
    mav.setViewName("register"); // 表示するビュー（HTMLファイル）の名前
    return mav;
  }

  @PostMapping("/register")
  public String register(UserDto userDto) {
    User user = userService.findByUsername(userDto.getName());
    if (user != null) {
      return "register"; // ユーザーが存在するため、再度登録画面を表示する
    }
    userService.save(userDto);
    return "redirect:/login"; // 登録が成功した場合、リダイレクトでログイン画面を表示する
  }
}