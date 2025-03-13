package com.msa.fiveio.hub.infrastructure.client;

import com.msa.fiveio.hub.infrastructure.configuration.KakaoConfig;
import com.msa.fiveio.hub.presentation.dto.KakaoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "kakaoClient", url= "https://dapi.kakao.com", configuration = KakaoConfig.class)
public interface KakaoClient {

    @GetMapping("/v2/local/search/address.json")
    KakaoResponseDto searchAddress( @RequestParam("query") String query);

}
