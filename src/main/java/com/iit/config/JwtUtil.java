package com.iit.config;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60;

    private static final Key SECRET_KEY =
            Keys.hmacShaKeyFor("THIS_IS_A_VERY_SECRET_KEY_1234567890".getBytes());

    public static Key getSecretKey() {
        return SECRET_KEY;
    }

    public static String generateToken(Long userId, String email) {

        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }
}

