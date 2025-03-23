package com.msa.fiveio.user.presentation.controller;

import com.msa.fiveio.user.application.DeliveryManagerService;
import com.msa.fiveio.user.model.entity.DeliveryManagers;
import com.msa.fiveio.user.presentation.dto.DeliveryManagerProfileDto;
import com.msa.fiveio.user.presentation.dto.DeliveryManagerRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class DeliveryManagerController {

  private final DeliveryManagerService deliveryManagerService;

  @PostMapping("/delivery-managers")
  public ResponseEntity<DeliveryManagerProfileDto> createDeliveryManager(
      @RequestHeader("X-User-Id") Long userId,
      @Valid @RequestBody DeliveryManagerRequestDto deliveryManagerRequestDto
  ) {
    DeliveryManagerProfileDto profile = deliveryManagerService.createDeliveryManager(userId,deliveryManagerRequestDto);
    return ResponseEntity.ok(profile);
  }


//  @DeleteMapping("/")
//  public ResponseEntity<String> removeDeliveryManager(){
//
//  }
}