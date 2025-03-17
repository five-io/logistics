package com.msa.fiveio.slack;

import com.msa.fiveio.common.config.JpaAuditingConfig;
import com.msa.fiveio.common.config.QueryDslConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@Import({JpaAuditingConfig.class, QueryDslConfig.class})
@EnableFeignClients
@SpringBootApplication
public class SlackApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlackApplication.class, args);
	}

}
