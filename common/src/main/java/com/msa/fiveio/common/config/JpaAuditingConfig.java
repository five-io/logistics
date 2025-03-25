package com.msa.fiveio.common.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RequiredArgsConstructor
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {

    private static final String ACCESS_TOKEN_HEADER_NAME = "Authorization";
    private static final String SECRET_KEY= "64+F7IiY66as7Jik7ZiV7KCcIO2ZlOydtO2MheyeheuLiOuLpC4=";


    @Bean
    public AuditorAware<Long> auditorAware() {
        return () -> {
                ServletRequestAttributes attributes =
                    (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes == null) return Optional.empty();
                HttpServletRequest request = attributes.getRequest();
                final String bearerToken = request.getHeader(ACCESS_TOKEN_HEADER_NAME);
                if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
                    return Optional.empty();
                }
                final String accessToken = bearerToken.substring(7);
                return Optional.of(Long.parseLong(extractUserId(accessToken)));
        };
    }



    private String extractUserId(String accessToken) {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY)))
            .build()
            .parseClaimsJws(accessToken)
            .getBody()
            .get("userId", String.class);
    }


}




