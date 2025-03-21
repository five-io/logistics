package com.msa.fiveio.order.infrastructure.client;

import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("19098")
public interface ProductClient {

    @PatchMapping("/api/products/{id}/rollback")
    void rollbackStock(@PathVariable("id") UUID productId, Long quantity);
}
