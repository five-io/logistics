package com.msa.fiveio.order.presentation.controller;

import com.msa.fiveio.order.application.facade.OrdersFacade;
import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import com.msa.fiveio.order.presentation.dto.request.OrderSearchRequestDto;
import com.msa.fiveio.order.presentation.dto.response.OrderCreateResponseDto;
import com.msa.fiveio.order.presentation.dto.response.OrderResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order Service", description = "주문 서비스 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersFacade ordersFacade;

    @PostMapping
    public ResponseEntity<OrderCreateResponseDto> createOrder(
        @RequestBody OrderCreateRequestDto orderRequestDto) {
        return ResponseEntity.ok(ordersFacade.createOrder(orderRequestDto));
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> readOrders(
        @RequestBody OrderSearchRequestDto requestDto, Pageable pageable
    ) {
        return ResponseEntity.ok(ordersFacade.readOrders(requestDto, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> readOrder(
        @PathVariable("id") UUID orderId
    ) {
        return ResponseEntity.ok(ordersFacade.readOrder(orderId));
    }

}
