package com.msa.fiveio.delivery.infrastructure.client;

import com.msa.fiveio.delivery.infrastructure.client.dto.request.SlacksCreateRequestDto;
import com.msa.fiveio.delivery.infrastructure.client.dto.response.SlacksCreateResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "localhost:19095")
public interface SlackClient {

    @PostMapping("/api/slacks")
    ResponseEntity<SlacksCreateResponseDto> createSlack(
        @RequestBody SlacksCreateRequestDto slacksCreateRequestDto);
}
