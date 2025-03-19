package com.msa.fiveio.hub.presentation.controller;


import com.msa.fiveio.hub.application.facade.HubRouteFacade;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
@Tag(name = "Hub Route Service", description = "허브 서비스 API")
public class HubRouteController {

    private final HubRouteFacade hubRouteFacade;

    @Operation(summary = "Hub 이동경로 등록", description = "Hub 이동경로 등록 api 입니다.")
    @PostMapping
    public ResponseEntity<HubRouteResponseDto> createHubs(@RequestBody HubRouteRequestDto hubsDto) {
        return ResponseEntity.ok(hubRouteFacade.createHubRoute(hubsDto));
    }


}
