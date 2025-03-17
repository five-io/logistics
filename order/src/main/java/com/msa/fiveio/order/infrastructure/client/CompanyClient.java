package com.msa.fiveio.order.infrastructure.client;

import com.msa.fiveio.order.infrastructure.client.dto.CompanyResponseDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "comp-service")
public interface CompanyClient {

    @GetMapping("/companies/order")
    CompanyResponseDto getCompanyInfo(@RequestParam UUID productId,
        @RequestParam UUID receiverCompanyId, @RequestParam Long quantity);

}
