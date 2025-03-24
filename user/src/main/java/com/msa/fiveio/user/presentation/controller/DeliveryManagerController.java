package com.msa.fiveio.user.presentation.controller;

import com.msa.fiveio.user.application.DeliveryManagerService;
import com.msa.fiveio.user.model.entity.DeliveryManagers;
import com.msa.fiveio.user.presentation.dto.DeliveryManagerGetDto;
import com.msa.fiveio.user.presentation.dto.DeliveryManagerProfileDto;
import com.msa.fiveio.user.presentation.dto.DeliveryManagerRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Delivery Manager Service", description = "배송담당자 서비스 API")
public class DeliveryManagerController {

  private final DeliveryManagerService deliveryManagerService;

  @PostMapping("/delivery-managers")
  @Operation(summary = "배송담당자 등록", description = "배송담당자를 등록합니다.")
  public ResponseEntity<DeliveryManagerProfileDto> createDeliveryManager(
      @RequestHeader("X-User-Id") Long userId,
      @Valid @RequestBody DeliveryManagerRequestDto deliveryManagerRequestDto
  ) {
    DeliveryManagerProfileDto profile = deliveryManagerService.createDeliveryManager(userId,deliveryManagerRequestDto);
    return ResponseEntity.ok(profile);
  }


  @GetMapping("/delivery-managers")
  @Operation(summary = "배송담당자 조회", description = "배송담당자를 조회합니다.")
  public ResponseEntity<DeliveryManagerGetDto> getDeliveryManager(
      @RequestHeader("X-User-Id") Long userId
  ){
    DeliveryManagerGetDto profileDto = deliveryManagerService.getDeliveryManagerGetDto(userId);
    return ResponseEntity.ok(profileDto);
  }
}