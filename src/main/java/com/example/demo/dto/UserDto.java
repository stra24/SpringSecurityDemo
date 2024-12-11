package com.example.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;
    @NotEmpty
    private String authority;
}
