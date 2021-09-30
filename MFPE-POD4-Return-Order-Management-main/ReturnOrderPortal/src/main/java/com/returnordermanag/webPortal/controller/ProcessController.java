package com.returnordermanag.webPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.returnordermanag.webPortal.client.ComponentProcessingFeignClient;
import com.returnordermanag.webPortal.dao.ProcessReponseRepository;
import com.returnordermanag.webPortal.dao.ProcessRequestRepository;
import com.returnordermanag.webPortal.dao.UserAuthenticationRepository;
import com.returnordermanag.webPortal.model.ProcessRequest;
import com.returnordermanag.webPortal.model.ProcessResponse;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProcessController {
	@Autowired
	ProcessRequest processRequest;

	@Autowired
	ProcessResponse processResponse;

	@Autowired
	UserAuthenticationRepository userRepo;

	@Autowired
	ProcessRequestRepository processRequestRepo;

	@Autowired
	ProcessReponseRepository processResponseRepo;

	@Autowired
	ComponentProcessingFeignClient componentProcessingFeignClient;

	@RequestMapping("/home")
	public String home() {
		return "home.jsp";
	}

	@RequestMapping("/addprocessRequest")      //To be invoked when user enters component details
	public String displayProcessingDetails(@RequestParam("username") String username,
			@RequestParam("contactNumber") long contactNumber, @RequestParam("creditCardNumber") long creditCardNumber,
			@RequestParam("componentType") String componentType, @RequestParam("componentName") String componentName,
			@RequestParam("quantityOfDefective") int quantityOfDefective,
			@RequestParam("isPriorityRequest") boolean isPriorityRequest,  Model model) {
		log.info("Inside displayProcessingDetails method");
		int userId = 101;
		processRequest = new ProcessRequest(userId, username, contactNumber, creditCardNumber,
				componentType, componentName, quantityOfDefective, isPriorityRequest);

		processRequestRepo.save(processRequest);
		log.info("Process Request saved");

		String jwtToken = "Bearer "+ userRepo.findById(processRequest.getUserID()).get().getJwtToken();
		log.info("JWT Generated");

		processResponse = componentProcessingFeignClient.getProcessingDetails(jwtToken, processRequest);
		processResponseRepo.save(processResponse);
		log.info("Process Response saved");
		// Below fields to be displayed in process.jsp

		int requestId = processResponse.getRequestID();
		int userID = processResponse.getUserID();
		double processingCharge = processResponse.getProcessingCharge();
		double packagingAndDeliveryCharge = processResponse.getPackagingAndDeliveryCharge();
		String dateOfDelivery = processResponse.getDateOfDelivery();

		model.addAttribute("requestId", requestId);
		model.addAttribute("userID", userID);
		model.addAttribute("processingCharge", processingCharge);
		model.addAttribute("packagingAndDeliveryCharge", packagingAndDeliveryCharge);
		model.addAttribute("dateOfDelivery", dateOfDelivery);
		
		
		return "process.jsp";
	}

	@RequestMapping("/payment")          //To be invoked when user confirms payment
	public String confirmationMessage(@RequestParam("confirmation") boolean confirmation,  Model Map) {
		log.info("Inside confirmationMessage method");
		//boolean confirmation = true;
		String response = "Unable to process";
		String jwtToken = "Bearer "+ userRepo.findById(processRequest.getUserID()).get().getJwtToken();
		log.info("JWT Generated");
		int requestID = processResponse.getRequestID();
		long creditCardNumber = processRequest.getCreditCardNumber();
		double totalCharge = processResponse.getProcessingCharge() + processResponse.getPackagingAndDeliveryCharge();
		if(confirmation) {
			response = componentProcessingFeignClient.paymentProcessing(jwtToken, requestID, creditCardNumber, 2000.0, totalCharge);
		}
		else
			response = "Thank you for your time";
		Map.addAttribute("response", response);
		return "confirmation.jsp" ;

	}

}
