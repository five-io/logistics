package com.msa.fiveio.delivery.presentation.controller;

import com.msa.fiveio.delivery.application.facade.DeliveryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryFacade deliveryFacade;

}
