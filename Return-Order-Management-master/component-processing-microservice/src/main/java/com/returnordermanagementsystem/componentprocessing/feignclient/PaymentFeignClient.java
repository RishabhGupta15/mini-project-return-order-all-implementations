package com.returnordermanagementsystem.componentprocessing.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${payment-service-name}", url = "${payment-service-url}")
public interface PaymentFeignClient {

	@GetMapping(value = "/{cardNumber}/{charge}")
	double getBalance(@PathVariable long cardNumber, @PathVariable double charge);
	
	@GetMapping(value = "/{requestId}/{cardNumber}/{charge}")
	double getCBalance(@PathVariable int requestId, @PathVariable long cardNumber, @PathVariable double charge);
}
