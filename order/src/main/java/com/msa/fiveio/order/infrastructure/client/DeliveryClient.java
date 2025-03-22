package com.msa.fiveio.order.infrastructure.client;

import com.msa.fiveio.order.infrastructure.client.dto.request.DeliveryCreateRequestDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "localhost:19092")
public interface DeliveryClient {

    @PostMapping("/api/deliveries")
    void createDelivery(@RequestBody DeliveryCreateRequestDto deliveryCreateRequestDto);

    @GetMapping("/api/deliveries/{id}/status")
    String getDeliveryStatus(@PathVariable("id") UUID orderId);
}
