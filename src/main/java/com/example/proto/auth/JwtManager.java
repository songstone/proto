package com.example.proto.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
public class JwtManager {

    // TODO 주입
    private static final String SECRET_KEY = "HUB_PROTO";
    private static final Long EXP = 3 * (1000L*60*60);

    private static final String AUTH_HEADER = "Authorization";

    public String createToken(Long empNo) {
        return Jwts.builder()
                .setSubject(String.valueOf(empNo))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXP))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);

            return claims.getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getSubject(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public String extractToken(HttpServletRequest request) {
        String authToken = request.getHeader(AUTH_HEADER);
        if(!StringUtils.hasText(authToken)) return "";

        return authToken.replace("Bearer ", "");
    }
}
