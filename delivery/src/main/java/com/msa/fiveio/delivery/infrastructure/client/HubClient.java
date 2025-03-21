package com.msa.fiveio.delivery.infrastructure.client;

import com.msa.fiveio.delivery.infrastructure.client.dto.response.HubsResponseDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "localhost:19093")
public interface HubClient {

    @GetMapping("/api/hubs/read")
    ResponseEntity<HubsResponseDto> readHubs(@RequestParam UUID id);

}
