package com.msa.fiveio.delivery.presentation.controller;


import com.msa.fiveio.delivery.application.facade.DeliveryRouteFacade;
import com.msa.fiveio.delivery.presentation.dto.request.CreateDeliveryRouteRequest;
import com.msa.fiveio.delivery.presentation.dto.request.DeliveryRouteRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Delivery Route Service", description = "배송경로 서비스 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/delivery-routes")
public class DeliveryRouteController {

    private final DeliveryRouteFacade deliveryRouteFacade;

    @PostMapping
    public ResponseEntity<String> createDelRoute(@RequestBody CreateDeliveryRouteRequest request) {
        deliveryRouteFacade.createDelRoute(request);
        return ResponseEntity.ok("경로 생성 완료");
    }

    @PutMapping
    public ResponseEntity<String> updateDelRoute(@RequestBody DeliveryRouteRequest request) {
        deliveryRouteFacade.updateDelRoute(request);
        return ResponseEntity.ok("배송상태 변경 완료");
    }

}
