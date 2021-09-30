package com.returnordermanag.webPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EntityScan("com.returnordermanag.webPortal.model")
@EnableJpaRepositories("com.returnordermanag.webPortal.dao")
@SpringBootApplication
@EnableFeignClients
public class ReturnOrderPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReturnOrderPortalApplication.class, args);
	}

}
