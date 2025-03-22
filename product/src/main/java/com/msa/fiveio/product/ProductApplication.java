package com.msa.fiveio.product;

import com.msa.fiveio.common.config.FeignConfig;
import com.msa.fiveio.common.config.JpaAuditingConfig;
import com.msa.fiveio.common.config.QueryDslConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@Import({JpaAuditingConfig.class, QueryDslConfig.class, FeignConfig.class})
@EnableFeignClients
@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

}
