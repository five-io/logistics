package com.msa.fiveio.user.infrastructure.configuration.jwt;


import com.msa.fiveio.user.model.entity.enums.UsersRoleEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtUtil {


    public static final String AUTHORIZATION_KEY = "role";
    public static final String BEARER_PREFIX = "Bearer ";


    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    private final JwtProperties jwtProperties;

    public JwtUtil(JwtProperties jwtProperties) {

        this.jwtProperties = jwtProperties;

    }


    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecretKey());
        return Keys.hmacShaKeyFor(keyBytes);
    }


    //accessToken 생성
    public String createToken(String username, UsersRoleEnum role) {
        Date date = new Date();

        return BEARER_PREFIX +
            Jwts.builder()
                .claim("user_id", username)
                .claim("role", role)
                .setExpiration(new Date(date.getTime() + jwtProperties.getTokenTime()))
                .setIssuedAt(date)
                .signWith(getSecretKey(), signatureAlgorithm)
                .compact();
    }


    //로그아웃 시 jwt만료 시간확인
    public long getExpiration(String token) {
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(jwtProperties.getSecretKey())
            .build()
            .parseClaimsJws(token)
            .getBody();

        Date expiration = claims.getExpiration();
        return expiration.getTime() - System.currentTimeMillis(); // 남은 유효 시간
    }

}