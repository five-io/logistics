package com.msa.fiveio.order.presentation.controller;

import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_HUB_MANAGER;
import static com.msa.fiveio.common.annotation.ApiPermission.Role.ROLE_MASTER;

import com.msa.fiveio.common.annotation.ApiPermission;
import com.msa.fiveio.order.application.facade.OrdersFacade;
import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import com.msa.fiveio.order.presentation.dto.request.OrderSearchRequestDto;
import com.msa.fiveio.order.presentation.dto.request.OrderUpdateRequestDto;
import com.msa.fiveio.order.presentation.dto.response.OrderCreateResponseDto;
import com.msa.fiveio.order.presentation.dto.response.OrderResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Order Service", description = "주문 서비스 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersFacade ordersFacade;

    /**
     * 주문 생성
     * 사용자 권한: 모든 로그인 사용자
     */
    @PostMapping
    public ResponseEntity<OrderCreateResponseDto> createOrder(
        @RequestBody OrderCreateRequestDto orderRequestDto) {
        return ResponseEntity.ok(ordersFacade.createOrder(orderRequestDto));
    }

    /**
     * 주문 조회(검색)
     * 사용자 권한: 모든 로그인 사용자. 단, 주문자 본인은 자신의 주문만 조회 가능
     */
    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> readOrders(
        @RequestBody OrderSearchRequestDto requestDto, Pageable pageable
    ) {
        return ResponseEntity.ok(ordersFacade.readOrders(requestDto, pageable));
    }

    /**
     * 주문 단일 조회
     * 사용자 권한: 모든 로그인 사용자. 단, 주문자 본인은 자신의 주문만 조회 가능
     */
    @ApiPermission(roles = {ROLE_MASTER, ROLE_HUB_MANAGER})
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> readOrder(
        @PathVariable("id") UUID orderId,
        @RequestHeader("X-User-Role") String role
    ) {
        log.info("Order Controller Read Role: " + role);
        return ResponseEntity.ok(ordersFacade.readOrder(orderId));
    }

    /**
     * 주문 수정
     * 사용자 권한: 마스터 관리자, 해당 주문 허브 관리자
     */
    @PatchMapping("/{id}")
    public ResponseEntity<OrderResponseDto> updateOrder(
        @PathVariable("id") UUID orderId,
        @RequestBody OrderUpdateRequestDto requestDto
    ) {
        return ResponseEntity.ok(ordersFacade.updateOrder(orderId, requestDto));
    }

    @DeleteMapping("/{id}/cancel")
    public void cancelOrder(
        @PathVariable("id") UUID orderId
//        @RequestHeader("X-User-Id") Long userId
    ) {
        Long userId = 1L;
        ordersFacade.cancelOrder(orderId, userId);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(
        @PathVariable("id") UUID orderId
    ) {
        Long userId = 1L;
        ordersFacade.deleteOrder(orderId, userId);
    }
}
