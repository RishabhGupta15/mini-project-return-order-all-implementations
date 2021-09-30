package com.returnordermanagementsystem.componentprocessing.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.returnordermanagementsystem.componentprocessing.exception.InvalidTokenException;
import com.returnordermanagementsystem.componentprocessing.exception.UnknownException;
import com.returnordermanagementsystem.componentprocessing.feignclient.AuthenticationFeignClient;
import com.returnordermanagementsystem.componentprocessing.model.AuthenticationResponse;
import com.returnordermanagementsystem.componentprocessing.model.ProcessRequest;
import com.returnordermanagementsystem.componentprocessing.model.ProcessResponse;
import com.returnordermanagementsystem.componentprocessing.repository.ProcessRequestRepository;
import com.returnordermanagementsystem.componentprocessing.repository.ProcessResponseRepository;
import com.returnordermanagementsystem.componentprocessing.service.AccessoryPartService;
import com.returnordermanagementsystem.componentprocessing.service.IntegralPartService;
import com.returnordermanagementsystem.componentprocessing.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ComponentProcessingController {

	@Autowired
	private IntegralPartService integralPartService;
	@Autowired
	private AccessoryPartService accessoryPartService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private ProcessRequestRepository processRequestRepository;
	
	@Autowired
	private ProcessResponseRepository processResponseRepository;
	
	@Autowired
	private AuthenticationFeignClient authenticationFeignClient;

	@Value("${componentType.integral}")
	private String componentTypeIntegral;
	@Value("${componentType.accessory}")
	private String componentTypeAccessory;
	@Value("${exception.message}")
	private String exceptionMessage;
	@Value("${invalidTokenException.message}")
	private String invalidTokenExceptionMessage;

	@PostMapping("/service")
	public ProcessResponse getProcessingDetails(@RequestHeader("Authorization") String token,
			@RequestBody ProcessRequest processRequest) throws InvalidTokenException {
		log.info("Started getProcessingDetails() method in Component processing Controller");
		try {
			AuthenticationResponse authenticationResponse = null;
			log.info("Authenticating Request Token");
			authenticationResponse = authenticationFeignClient.getValidity(token);
			if (authenticationResponse.isValid()) {
				log.info("JWT Token verified");
			} else {
				log.info("JWT Token Expired");
				throw new InvalidTokenException(invalidTokenExceptionMessage);
			}
		} catch (Exception exception) {
			log.info("Something went wrong in Authentication Microservice");
			log.debug("Exception {} ", exception.getMessage());
			throw new UnknownException(exceptionMessage);
		}
		processRequestRepository.save(processRequest);
		log.info("Saved Process Request");

		ProcessResponse response = null;

		int userId = processRequest.getUserId();
		String componentType = processRequest.getComponentType();

		if (componentType.equals(componentTypeIntegral))
			response = integralPartService.processDetails(userId);
		else if (componentType.equals(componentTypeAccessory))
			response = accessoryPartService.processDetails(userId);

		return response;
	}

	@GetMapping("/payment/{requestId}/{creditCardNumber}/{creditLimit}/{processingCharge}")
	public String paymentProcessing(@RequestHeader("Authorization") String token,
			@PathVariable("requestId") int requestId, @PathVariable("creditCardNumber") long creditCardNumber,
			@PathVariable("creditLimit") double creditLimit, @PathVariable("processingCharge") double processingcharge)
			throws InvalidTokenException {
		log.info("Started paymentProcessing() method in Component processing Controller");
		try {
			AuthenticationResponse authenticationResponse = null;

			log.info("Authenticating Request Token");
			authenticationResponse = authenticationFeignClient.getValidity(token);
			if (authenticationResponse.isValid()) {
				log.info("JWT Token verified");
			} else {
				log.info("JWT Token Expired");
				throw new InvalidTokenException(invalidTokenExceptionMessage);
			}
		} catch (Exception exception) {
			log.info("Something went wrong in Authentication Microservice");
			log.debug("Exception {} ", exception.getMessage());
			throw new UnknownException(exceptionMessage);
		}

		log.info("Processing Payment Details");
		return paymentService.completeProcessing(requestId, creditCardNumber, creditLimit, processingcharge);
	}
	
	@GetMapping("/reversePayment/{requestId}/{creditCardNumber}")
	public String reversePaymentProcessing(@RequestHeader("Authorization") String token,
			@PathVariable("requestId") int requestId, @PathVariable("creditCardNumber") long creditCardNumber)
			throws InvalidTokenException {
		log.info("Started reversePaymentProcessing() method in Component processing Controller");
		try {
			AuthenticationResponse authenticationResponse = null;

			log.info("Authenticating Request Token");
			authenticationResponse = authenticationFeignClient.getValidity(token);
			if (authenticationResponse.isValid()) {
				log.info("JWT Token verified");
			} else {
				log.info("JWT Token Expired");
				throw new InvalidTokenException(invalidTokenExceptionMessage);
			}
		} catch (Exception exception) {
			log.info("Something went wrong in Authentication Microservice");
			log.debug("Exception {} ", exception.getMessage());
			throw new UnknownException(exceptionMessage);
		}

		log.info("Processing Payment Details");
		ProcessResponse response = processResponseRepository.findById(requestId).get();
		double charges = response.getProcessingCharge() + response.getPackagingAndDeliveryCharge();
		return paymentService.reverseProcessing(requestId, creditCardNumber, charges);
	}
	
	@GetMapping("/track/{id}")
	public LocalDate trackRequest(@PathVariable("id") int id) {
		ProcessResponse response = processResponseRepository.findById(id).get();
		log.info("response:{}",response);
		return response.getDateOfDelivery();
	}
	
	@GetMapping("/deleteRequest/{id}")
	public String deleteRequest(@PathVariable("id") int id) {
		processResponseRepository.deleteById(id);
		return "successful";
	}
}
