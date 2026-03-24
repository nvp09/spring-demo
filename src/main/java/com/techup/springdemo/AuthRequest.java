package com.techup.springdemo;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}