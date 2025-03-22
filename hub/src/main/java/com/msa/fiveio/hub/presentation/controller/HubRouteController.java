package com.msa.fiveio.hub.presentation.controller;


import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_COMPANY_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_DELIVERY_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_MASTER;

import com.msa.fiveio.common.annotation.ApiPermission;
import com.msa.fiveio.hub.application.facade.HubRouteFacade;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubRoutes.HubRouteResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final InitSetting initSetting;

    @ApiPermission(roles = {ROLE_MASTER})
    @Operation(summary = "Hub 이동경로 등록", description = "Hub 이동경로 등록 api 입니다.")
    @PostMapping
    public ResponseEntity<HubRouteResponseDto> createHubs(@RequestBody HubRouteRequestDto hubsDto) {
        return ResponseEntity.ok(hubRouteFacade.createHubRoute(hubsDto));
    }

    @ApiPermission(roles = {ROLE_MASTER, ROLE_DELIVERY_MANAGER, ROLE_COMPANY_MANAGER})
    @Operation(summary = "Hub 최단 경로 조회", description = "Hub 최단 경로 조회 api 입니다.")
    @GetMapping
    public List<HubRouteResponseDto> getHubRouteList(@RequestBody HubRouteRequestDto hubsDto) {
        return hubRouteFacade.getHubRouteList(hubsDto);
    }

    @ApiPermission(roles = {ROLE_MASTER})
    @PostMapping("/setRoute")
    public String setRoute() {

        String middle = """
            경기 남부 센터
            대전광역시 센터
            대구광역시 센터
            경상북도 센터
            """;

        String input1 = """
            서울특별시 센터
            경기 북부 센터
            인천광역시 센터
            강원특별자치도 센터
            경상북도 센터
            대전광역시 센터
            대구광역시 센터
            """;
        String input2 = """
            세종특별자치시 센터
            충청북도 센터
            충청남도 센터
            전북특별자치도 센터
            전라남도 센터
            광주광역시 센터
            대구광역시 센터
            """;
        String input3 = """
            울산광역시 센터
            부산광역시 센터
            경상남도 센터
            경상북도 센터
            """;

        String[] inputs = new String[]{middle, input1, input2, input3};
        initSetting.initSetRoutes(inputs);

        return "Setting Success";
    }
}
