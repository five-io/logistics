package com.msa.fiveio.product.infrastructure.client;

import com.msa.fiveio.common.config.FeignConfig;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("localhost:19094", configuration = FeignConfig.class)
public interface CompanyClient {

    @GetMapping("/api/companys/{companyId}/products")
    public ResponseEntity<ProductCompanyGetResponseDto> getProductCompany(
        @PathVariable UUID companyId);
}
