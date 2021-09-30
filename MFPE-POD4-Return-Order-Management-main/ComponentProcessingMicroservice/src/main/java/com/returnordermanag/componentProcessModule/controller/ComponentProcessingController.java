package com.returnordermanag.componentProcessModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.returnordermanag.componentProcessModule.client.AuthenticationFeignClient;
import com.returnordermanag.componentProcessModule.model.AuthenticationResponse;
import com.returnordermanag.componentProcessModule.model.ProcessRequest;
import com.returnordermanag.componentProcessModule.model.ProcessResponse;
import com.returnordermanag.componentProcessModule.repository.ProcessRequestRepository;
import com.returnordermanag.componentProcessModule.service.AccessoryPartService;
import com.returnordermanag.componentProcessModule.service.IntegralPartService;
import com.returnordermanag.componentProcessModule.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ComponentProcessingController {
	@Autowired
	IntegralPartService integralPartService;
	@Autowired
	AccessoryPartService accessoryPartService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	ProcessRequestRepository processRequestRepository;
	@Autowired
	AuthenticationFeignClient authenticationFeignClient;

	@PostMapping("/service")
	public ProcessResponse getProcessingDetails(@RequestHeader("Authorization") String token,
			@RequestBody ProcessRequest processRequest) throws Exception {
		log.info("Getting Processing Details");
		AuthenticationResponse authenticationResponse = null;
		try {
			log.info("Authenticating Request Token");
			authenticationResponse = authenticationFeignClient.getValidity(token);
			if (authenticationResponse.getValid() == false)
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Authorization Failed. Please try again");
		}

		processRequestRepository.save(processRequest);
		log.info("Saved Process Request");

		ProcessResponse response = null;

		int userID = processRequest.getUserID();
		String componentType = processRequest.getComponentType();

		if (componentType.equals("integral"))
			response = (integralPartService.processDetail(userID));
		else if (componentType.equals("accessory"))
			response = (accessoryPartService.processDetail(userID));

		return response;
	}

	@PostMapping("/payment/{requestID}/{creditCardNumber}/{creditLimit}/{processingCharge}")
	public String paymentProcessing(@RequestHeader("Authorization") String token,
			@PathVariable("requestID") int requestID, @PathVariable("creditCardNumber") long creditCardNumber,
			@PathVariable("creditLimit") double creditLimit, @PathVariable("processingCharge") double processingcharge)
			throws Exception {

		AuthenticationResponse authenticationResponse = null;
		try {
			log.info("Authenticating Request Token");
			authenticationResponse = authenticationFeignClient.getValidity(token);
			if (authenticationResponse.getValid() == false)
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Authorization Failed. Please try again");
		}

		log.info("Getting Payment Details");

		return paymentService.completeProcessing(requestID, creditCardNumber, creditLimit, processingcharge);
	}
}
