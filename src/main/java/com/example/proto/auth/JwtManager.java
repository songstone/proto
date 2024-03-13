package com.example.proto.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

public class JwtManager {

    //TODO properties 분리
    private static final String SECRET_KEY = "HUB_PROTO";
    private static final Long EXP = 1000L*60*60*24*7;

    public static String createToken(Long empNo, String email) {
        return Jwts.builder()
                .setSubject(empNo + "_" + email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXP))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
