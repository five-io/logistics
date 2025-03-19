package com.msa.fiveio.hub.infrastructure.configuration;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KakaoConfig {

    @Value("${kakao.rest.api.key}")
    private String kakaoRestApiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        // 여기서 헤더에 값 추가
        return requestTemplate -> {
            requestTemplate.header("Authorization", "KakaoAK " + kakaoRestApiKey);
            requestTemplate.header("Content-Type", "application/json");
        };
    }

}
