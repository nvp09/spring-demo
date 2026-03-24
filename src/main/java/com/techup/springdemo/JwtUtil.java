package com.techup.springdemo;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "mysecretkeymysecretkeymysecretkey"; // ต้องยาว

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // 🔹 generate token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 วัน
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // 🔹 extract username
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // 🔹 validate token
    public boolean isValid(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}