package com.msa.fiveio.order.infrastructure.client;

import com.msa.fiveio.common.config.FeignConfig;
import com.msa.fiveio.order.infrastructure.client.dto.response.ProductResponseDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "localhost:19098", configuration = FeignConfig.class)
public interface ProductClient {

    @PatchMapping("/api/products/{id}/rollback")
    void rollbackStock(@PathVariable("id") UUID productId, Long quantity);

    @GetMapping("/api/products/order")
    ProductResponseDto processOrderRequest(@RequestParam UUID productId,
        @RequestParam UUID receiverCompanyId, @RequestParam Long quantity);
}
