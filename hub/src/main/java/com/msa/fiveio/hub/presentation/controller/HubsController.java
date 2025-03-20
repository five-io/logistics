package com.msa.fiveio.hub.presentation.controller;


import com.msa.fiveio.hub.application.facade.HubsFacade;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.hubs.HubsResponseDto;
import com.msa.fiveio.hub.presentation.dto.hubs.SearchResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    @Operation(summary = "Hub 등록", description = "Hub 등록 api 입니다.")
    @PostMapping("/create")
    public ResponseEntity<CompletableFuture<HubsResponseDto>> createHubs(
        @RequestBody HubsRequestDto hubsDto) {
        return ResponseEntity.ok(hubsFacade.createHubs(hubsDto));
    }

    @Operation(summary = "Hub 단 건 조회", description = "Hub 단 건 조회 api 입니다.")
    @GetMapping("/read")
    public ResponseEntity<HubsResponseDto> readHubs(@RequestParam UUID id) {
        return ResponseEntity.ok(hubsFacade.readHubs(id));
    }

    @Operation(summary = "Hub 수정", description = "Hub 수정 api 입니다.")
    @PatchMapping("/update/{id}")
    public ResponseEntity<HubsResponseDto> updateHubs(@PathVariable UUID id,
        @RequestBody HubsRequestDto hubsDto) {
        return ResponseEntity.ok(hubsFacade.updateHubs(id, hubsDto));
    }

    @Operation(summary = "Hub Search", description = "Hub Search api 입니다.")
    @GetMapping("/search")
    public ResponseEntity<Page<SearchResponseDto>> searchAddress(
        HubsRequestDto hubsDto, Pageable pageable) {

        return ResponseEntity.ok(hubsFacade.searchHubs(hubsDto, pageable));
    }

    @Operation(summary = "Hub 삭제", description = "Hub 삭제 api 입니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHubs(@PathVariable UUID id) {
        //  hubsFacade.deleteHubs(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/setting")
    public String settingHubs() {
        initSetting.initSet();
        return "Setting Success";
    }

}
