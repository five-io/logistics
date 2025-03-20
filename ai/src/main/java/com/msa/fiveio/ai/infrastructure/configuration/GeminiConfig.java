package com.msa.fiveio.ai.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GeminiConfig {

	@Bean
	public RestTemplate geminiRestTemplate() {
		return new RestTemplate();
	}
}
