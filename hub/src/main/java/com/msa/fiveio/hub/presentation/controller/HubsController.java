package com.msa.fiveio.hub.presentation.controller;


import brave.Response;
import com.msa.fiveio.hub.application.facade.HubsFacade;
import com.msa.fiveio.hub.presentation.dto.HubsRequestDto;
import com.msa.fiveio.hub.presentation.dto.HubsResponseDto;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
public class HubsController {

    private final HubsFacade hubsFacade;

    @PostMapping("/create")
    public ResponseEntity<HubsResponseDto> createHubs(@RequestBody HubsRequestDto hubsDto) {
        return ResponseEntity.ok(hubsFacade.createHubs(hubsDto));
    }

    @GetMapping("/read")
    public ResponseEntity<HubsResponseDto> readHubs(@RequestParam UUID id) {
        return ResponseEntity.ok(hubsFacade.reaadHubs(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<HubsResponseDto> updateHubs(@PathVariable UUID id, @RequestBody HubsRequestDto hubsDto) {
        return ResponseEntity.ok(hubsFacade.updateHubs(id,hubsDto));
    }

}
