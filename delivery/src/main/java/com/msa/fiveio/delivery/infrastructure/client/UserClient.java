package com.msa.fiveio.delivery.infrastructure.client;

import com.msa.fiveio.delivery.infrastructure.client.dto.response.UserResponseDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "userClient", url = "http://localhost:19097")
public interface UserClient {

    @GetMapping("/api/users/{id}")
    UserResponseDto getDeliveryManagerId(
        @PathVariable("id") UUID hubId,
        @RequestParam String type
    );
}
