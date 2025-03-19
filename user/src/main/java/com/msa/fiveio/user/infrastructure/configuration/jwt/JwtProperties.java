package com.msa.fiveio.user.infrastructure.configuration.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "service.jwt")
public class JwtProperties {

    @Value("${service.jwt.secret-key}")
    private String secretKey;
    private long tokenTime = 1000 * 60 * 10;
    private long refreshTokenTime = 1000 * 60 * 60 * 24 * 7;

    // Getter
    public String getSecretKey() {
        return secretKey;
    }

    public long getTokenTime() {
        return tokenTime;
    }

    public long getRefreshTokenTime() {
        return refreshTokenTime;
    }

}
