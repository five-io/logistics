package com.msa.fiveio.user.presentation.controller;

import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_COMPANY_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_DELIVERY_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_HUB_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_MASTER;

import com.msa.fiveio.common.annotation.ApiPermission;
import com.msa.fiveio.user.application.DeliveryManagerService;
import com.msa.fiveio.user.model.entity.DeliveryManagers;
import com.msa.fiveio.user.model.entity.enums.ManagersTypeEnum;
import com.msa.fiveio.user.presentation.dto.DeliveryManagerProfileDto;
import com.msa.fiveio.user.presentation.dto.DeliveryManagerRequestDto;
import com.msa.fiveio.user.presentation.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.UUID;
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
  @ApiPermission(roles = {ROLE_MASTER, ROLE_HUB_MANAGER, ROLE_DELIVERY_MANAGER,
      ROLE_COMPANY_MANAGER})
  @Operation(summary = "배송담당자 등록", description = "배송담당자를 등록합니다.")
  public ResponseEntity<DeliveryManagerProfileDto> createDeliveryManager(
      @Valid @RequestBody DeliveryManagerRequestDto deliveryManagerRequestDto
  ) {
    DeliveryManagerProfileDto profile = deliveryManagerService.createDeliveryManager(deliveryManagerRequestDto);
    return ResponseEntity.ok(profile);
  }


  @GetMapping("/{id}")
  @ApiPermission(roles = {ROLE_MASTER, ROLE_HUB_MANAGER, ROLE_DELIVERY_MANAGER,
      ROLE_COMPANY_MANAGER})
  public ResponseEntity<UserResponseDto> getDeliveryManagerId( @PathVariable("id") UUID hubId,
      @RequestParam ManagersTypeEnum type){
    UserResponseDto userResponseDto = deliveryManagerService.getDeliveryManagerId(hubId,type);
    return ResponseEntity.ok(userResponseDto);
  }

}