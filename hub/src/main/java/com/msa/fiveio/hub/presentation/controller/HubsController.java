package com.msa.fiveio.hub.presentation.controller;


import com.msa.fiveio.hub.application.facade.HubsFacade;
import com.msa.fiveio.hub.presentation.dto.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.HubsResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hubs")
@RequiredArgsConstructor
public class HubsController {

    private final HubsFacade hubsFacade;

    @PostMapping("/create")
    public ResponseEntity<HubsResponseDto> createHubs(@RequestBody HubsRequestDto hubsDto) {
        HubsResponseDto hubs = hubsFacade.createHubs(hubsDto);
        return ResponseEntity.ok(hubs);
    }

}
