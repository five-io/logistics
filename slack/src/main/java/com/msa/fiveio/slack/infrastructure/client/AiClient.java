package com.msa.fiveio.slack.infrastructure.client;

import com.msa.fiveio.common.config.FeignConfig;
import com.msa.fiveio.slack.presentation.dto.SlacksCreateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "localhost:19096", configuration = FeignConfig.class)
public interface AiClient {

    @PostMapping("/api/ais")
    String createAis(SlacksCreateRequestDto slacksCreateRequestDto);
}
