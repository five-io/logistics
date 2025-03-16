package com.msa.fiveio.order.presentation.controller;

import com.msa.fiveio.order.application.facade.OrdersFacade;
import com.msa.fiveio.order.presentation.dto.request.OrderCreateRequestDto;
import com.msa.fiveio.order.presentation.dto.response.OrderCreateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrdersController {

    private final OrdersFacade ordersFacade;

    @PostMapping("/create")
    public ResponseEntity<OrderCreateResponseDto> createOrder(
        @RequestBody OrderCreateRequestDto orderRequestDto) {
        return ResponseEntity.ok(ordersFacade.createOrder(orderRequestDto));
    }

}
