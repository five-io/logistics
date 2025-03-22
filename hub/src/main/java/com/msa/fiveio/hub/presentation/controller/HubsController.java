package com.msa.fiveio.hub.presentation.controller;


import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_COMPANY_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_DELIVERY_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_HUB_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_MASTER;

import com.msa.fiveio.common.annotation.ApiPermission;
import com.msa.fiveio.hub.application.facade.HubsFacade;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.SearchResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hubs")
@RequiredArgsConstructor
@Tag(name = "Hub Service", description = "허브 서비스 API")
public class HubsController {

    private final HubsFacade hubsFacade;
    private final InitSetting initSetting;

    @ApiPermission(roles = {ROLE_MASTER})
    @Operation(summary = "Hub 등록", description = "Hub 등록 api 입니다.")
    @PostMapping("/create")
    public ResponseEntity<HubsResponseDto> createHubs(
        @RequestBody HubsRequestDto hubsDto) {
        return ResponseEntity.ok(hubsFacade.createHubs(hubsDto));
    }

    @ApiPermission(roles = {ROLE_MASTER, ROLE_HUB_MANAGER, ROLE_DELIVERY_MANAGER,
        ROLE_COMPANY_MANAGER})
    @Operation(summary = "Hub 단 건 조회", description = "Hub 단 건 조회 api 입니다.")
    @GetMapping("/read")
    public ResponseEntity<HubsResponseDto> readHubs(@RequestParam UUID id) {
        return ResponseEntity.ok(hubsFacade.readHubs(id));
    }

    @ApiPermission(roles = {ROLE_MASTER})
    @Operation(summary = "Hub 수정", description = "Hub 수정 api 입니다.")
    @PatchMapping("/update/{id}")
    public ResponseEntity<HubsResponseDto> updateHubs(@PathVariable UUID id,
        @RequestBody HubsRequestDto hubsDto) {
        return ResponseEntity.ok(hubsFacade.updateHubs(id, hubsDto));
    }

    @ApiPermission(roles = {ROLE_MASTER, ROLE_HUB_MANAGER, ROLE_DELIVERY_MANAGER,
        ROLE_COMPANY_MANAGER})
    @Operation(summary = "Hub Search", description = "Hub Search api 입니다.")
    @GetMapping("/search")
    public ResponseEntity<Page<SearchResponseDto>> searchAddress(
        Pageable pageable, @ModelAttribute HubsRequestDto hubsDto) {

        return ResponseEntity.ok(hubsFacade.searchHubs(hubsDto, pageable));
    }

    @ApiPermission(roles = {ROLE_MASTER})
    @Operation(summary = "Hub 삭제", description = "Hub 삭제 api 입니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHubs(@PathVariable UUID id) {
        //  hubsFacade.deleteHubs(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/setting")
    public String settingHubs() {
        String all = """
            경기 남부 센터 : 경기도 이천시 덕평로 257-21
            대전광역시 센터 : 대전 서구 둔산로 100
            대구광역시 센터 : 대구 북구 태평로 161
            경상북도 센터 : 경북 안동시 풍천면 도청대로 455
            서울특별시 센터 : 서울특별시 송파구 송파대로 55
            경기 북부 센터 : 경기도 고양시 덕양구 권율대로 570
            인천광역시 센터 : 인천 남동구 정각로 29
            강원특별자치도 센터 : 강원특별자치도 춘천시 중앙로 1
            세종특별자치시 센터 : 세종특별자치시 한누리대로 2130
            충청북도 센터 : 충북 청주시 상당구 상당로 82
            충청남도 센터 : 충남 홍성군 홍북읍 충남대로 21
            전북특별자치도 센터 : 전북특별자치도 전주시 완산구 효자로 225
            전라남도 센터 : 전남 무안군 삼향읍 오룡길 1
            광주광역시 센터 : 광주 서구 내방로 111
            울산광역시 센터 : 울산 남구 중앙로 201
            부산광역시 센터 : 부산 동구 중앙대로 206
            경상남도 센터 : 경남 창원시 의창구 중앙대로 300
            """;

        initSetting.initSet(all);

        return "Setting Success";
    }

}
