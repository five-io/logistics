package com.msa.fiveio.delivery.infrastructure.client;


import com.msa.fiveio.delivery.infrastructure.client.dto.response.HubsResponseDto;
import com.msa.fiveio.delivery.infrastructure.client.dto.response.RouteResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "hubClient", url = "http://localhost:19093")
public interface HubClient {

    @GetMapping("/api/hubs/read")
    ResponseEntity<HubsResponseDto> readHubs(@RequestParam UUID id);

    @GetMapping("/api/routes")
    List<RouteResponseDto> getHubRouteList(@RequestParam UUID arriveHubId,
        @RequestParam UUID departHubId);
}
