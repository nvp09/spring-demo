package com.techup.springdemo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {

        // 🔥 mock login (ยังไม่เช็ค DB)
        if ("admin".equals(request.getUsername()) &&
            "1234".equals(request.getPassword())) {

            String token = jwtUtil.generateToken(request.getUsername());
            return new AuthResponse(token);
        }

        throw new RuntimeException("Invalid username or password");
    }
}