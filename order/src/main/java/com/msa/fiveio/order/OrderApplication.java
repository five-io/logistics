package com.msa.fiveio.order;

import com.msa.fiveio.common.aop.PermissionCheckAspect;
import com.msa.fiveio.common.config.FeignConfig;
import com.msa.fiveio.common.config.JpaAuditingConfig;
import com.msa.fiveio.common.config.QueryDslConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@Import({JpaAuditingConfig.class, QueryDslConfig.class, PermissionCheckAspect.class,
    FeignConfig.class})
@EnableFeignClients
@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
