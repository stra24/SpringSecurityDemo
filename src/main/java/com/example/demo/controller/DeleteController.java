package com.example.demo.controller;

import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class DeleteController {

  private UserService userService;

  @PostMapping("/delete")
  public String register() {
    userService.delete();
    return "redirect:/login"; // 削除が成功した場合、リダイレクトでログイン画面を表示する
  }
}