package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/") // ルートURL
//    public String redirectToIndex() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 現在のユーザーの認証情報を取得します
//        if (authentication != null && authentication.isAuthenticated()) { // ユーザーがログインしている場合
//            return "redirect:/index";  // "/index"にリダイレクトします
//        }
//        return "redirect:/login"; // ユーザーがログインしていない場合、"/login"にリダイレクトします
//    }

    @GetMapping("/mypage")
    public String myPage() {
        return "mypage";
    }
}