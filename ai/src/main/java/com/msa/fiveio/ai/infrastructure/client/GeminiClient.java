package com.msa.fiveio.ai.infrastructure.client;

import com.msa.fiveio.ai.infrastructure.configuration.GeminiConfig;
import com.msa.fiveio.ai.presentation.dto.GeminiRequestDto;
import com.msa.fiveio.ai.presentation.dto.GeminiResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "geminiClient", url = "${app.api.ai.url}${app.api.ai.key}", configuration = GeminiConfig.class)
public interface GeminiClient {

	@PostMapping
	GeminiResponseDto createAis(@RequestBody GeminiRequestDto geminiRequestDto);
}
