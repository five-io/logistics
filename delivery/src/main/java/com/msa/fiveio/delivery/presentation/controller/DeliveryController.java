package com.msa.fiveio.delivery.presentation.controller;

import com.msa.fiveio.delivery.application.facade.DeliveryFacade;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryFacade deliveryFacade;

    @PostMapping
    public void createDelivery(
        @RequestBody DeliveryCreateRequestDto deliveryRequestDto) {
        deliveryFacade.createDelivery(deliveryRequestDto);
    }

}
