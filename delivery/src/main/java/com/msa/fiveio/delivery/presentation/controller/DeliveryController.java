package com.msa.fiveio.delivery.presentation.controller;

import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_COMPANY_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_DELIVERY_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_HUB_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_MASTER;

import com.msa.fiveio.common.annotation.ApiPermission;
import com.msa.fiveio.delivery.application.facade.DeliveryFacade;
import com.msa.fiveio.delivery.model.entity.enums.DeliveryStatus;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import com.msa.fiveio.delivery.presentation.dto.request.DeliverySearchRequestDto;
import com.msa.fiveio.delivery.presentation.dto.response.DeliveryResponseDto;
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

@Tag(name = "Delivery Service", description = "배송 서비스 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryFacade deliveryFacade;

    @ApiPermission(roles = {ROLE_MASTER})
    @Operation(summary = "Delivery 생성", description = "Delivery 생성 api 입니다.")
    @PostMapping
    public void createDelivery(@RequestBody DeliveryCreateRequestDto deliveryRequestDto) {
        deliveryFacade.createDelivery(deliveryRequestDto);
    }

    @ApiPermission(roles = {ROLE_MASTER, ROLE_HUB_MANAGER, ROLE_DELIVERY_MANAGER})
    @Operation(summary = "Delivery 상태 수정", description = "Delivery 상태 수정 api 입니다.")
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
        @PathVariable("id") UUID deliveryId,
        @RequestParam("deliveryStatus") String status
    ) {
        try {
            return ResponseEntity.ok(deliveryFacade.updateStatus(deliveryId, status));
        } catch (IllegalArgumentException e) {
            String errorMessage = "잘못된 상태 값입니다. 허용된 값: "
                + String.join(", ", DeliveryStatus.getAllowedStatuses()) + " 입니다.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    @ApiPermission(roles = {ROLE_MASTER, ROLE_HUB_MANAGER, ROLE_DELIVERY_MANAGER, ROLE_COMPANY_MANAGER})
    @Operation(summary = "Delivery 검색", description = "Delivery 검색 api 입니다.")
    @GetMapping
    public ResponseEntity<Page<DeliveryResponseDto>> readDeliveries(
        @ModelAttribute DeliverySearchRequestDto requestDto, Pageable pageable
    ) {
        return ResponseEntity.ok(deliveryFacade.readDeliveries(requestDto, pageable));
    }

    @ApiPermission(roles = {ROLE_MASTER, ROLE_HUB_MANAGER, ROLE_DELIVERY_MANAGER, ROLE_COMPANY_MANAGER})
    @Operation(summary = "Delivery 단건 조회", description = "Delivery 단건 조회 api 입니다.")
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryResponseDto> readDelivery(
        @PathVariable("id") UUID deliveryId
    ) {
        return ResponseEntity.ok(deliveryFacade.readDelivery(deliveryId));
    }

    @ApiPermission(roles = {ROLE_MASTER, ROLE_HUB_MANAGER, ROLE_DELIVERY_MANAGER, ROLE_COMPANY_MANAGER})
    @Operation(summary = "Delivery 상태 조회", description = "Delivery 상태 조회 api 입니다.")
    @GetMapping("/{id}/status")
    public String getDeliveryStatus(@PathVariable("id") UUID orderId) {
        return deliveryFacade.getDeliveryStatus(orderId);
    }

    @ApiPermission(roles = {ROLE_MASTER, ROLE_HUB_MANAGER})
    @Operation(summary = "Delivery 삭제", description = "Delivery 삭제 api 입니다.")
    @DeleteMapping("/{id}")
    public void deleteDelivery(
        @PathVariable("id") UUID deliveryId
//        @RequestHeader("X-User-Id") Long userId
    ) {
        Long userId = 1L;
        deliveryFacade.deleteDelivery(deliveryId, userId);
    }

    @Operation(summary = "Delivery 취소", description = "Delivery 취소 api 입니다.")
    @DeleteMapping("/{id}/cancel")
    public void cancelDelivery(@PathVariable("id") UUID orderId, Long userId) {
        deliveryFacade.cancelDelivery(orderId, userId);
    }
}
