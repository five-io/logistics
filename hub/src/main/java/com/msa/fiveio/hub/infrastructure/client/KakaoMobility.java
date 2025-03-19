package com.msa.fiveio.hub.infrastructure.client;

import com.msa.fiveio.hub.infrastructure.configuration.KakaoConfig;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.DirectionsResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoMobility", url = "https://apis-navi.kakaomobility.com", configuration = KakaoConfig.class)
public interface KakaoMobility {

    @GetMapping("/v1/directions")
    DirectionsResponseDto distanceMatrix(@RequestParam("origin") String query,
        @RequestParam("destination") String destination);

}
