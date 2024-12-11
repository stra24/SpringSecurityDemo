package com.example.demo.exception;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error2";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFoundException(Exception ex, Model model) {
        model.addAttribute("errorMessage", "NoResourceFoundExceptionでした: " + ex.getMessage());
        return "error2";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(Exception ex, Model model) {
        model.addAttribute("errorMessage", "404: ページが見つかりませんでした。");
        return "error2";
    }
}