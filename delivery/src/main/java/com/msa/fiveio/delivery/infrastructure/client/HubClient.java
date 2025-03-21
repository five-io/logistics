package com.msa.fiveio.delivery.infrastructure.client;

import com.msa.fiveio.delivery.infrastructure.client.dto.RouteRequestDto;
import com.msa.fiveio.delivery.infrastructure.client.dto.RouteResponseDto;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "hubClient", url = "http://localhost:19093")
public interface HubClient {

    @GetMapping("/api/routes")
    List<RouteResponseDto> getHubRouteList(@RequestBody RouteRequestDto hubsDto);

}
