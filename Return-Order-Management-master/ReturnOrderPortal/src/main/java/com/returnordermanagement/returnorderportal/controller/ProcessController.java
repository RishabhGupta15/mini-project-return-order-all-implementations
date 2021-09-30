package com.returnordermanagement.returnorderportal.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.returnordermanagement.returnorderportal.client.ComponentProcessingFeignClient;
import com.returnordermanagement.returnorderportal.model.ProcessRequest;
import com.returnordermanagement.returnorderportal.model.ProcessResponse;
import com.returnordermanagement.returnorderportal.model.User;
import com.returnordermanagement.returnorderportal.repository.ProcessReponseRepository;
import com.returnordermanagement.returnorderportal.repository.ProcessRequestRepository;
import com.returnordermanagement.returnorderportal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class ProcessController {
	@Autowired
	User user;

	@Autowired
	ProcessRequest processRequest;

	@Autowired
	ProcessResponse processResponse;

	@Autowired
	UserRepository userRepo;
	@Autowired
	ProcessRequestRepository processRequestRepository;

	@Autowired
	ProcessReponseRepository processResponseRepository;

	@Autowired
	ComponentProcessingFeignClient componentProcessingFeignClient;
	@Value("${payment-message}")
	private String paymentMessage;

	@RequestMapping("/addprocessRequest") // To be invoked when user enters component details
	public String displayProcessingDetails(@RequestParam("username") String username,
			@RequestParam("contactNumber") long contactNumber, @RequestParam("componentType") String componentType,
			@RequestParam("componentName") String componentName,
			@RequestParam("quantityOfDefective") int quantityOfDefective,
			@RequestParam(value = "isPriorityRequest", required = false) boolean isPriorityRequest, Model model) {
		log.info("Handling /addProcessRequest");

		long creditCardNumber = 1;
		processRequest = new ProcessRequest(1, username, contactNumber, creditCardNumber, componentType, componentName,
				quantityOfDefective, isPriorityRequest);
		processRequestRepository.save(processRequest);
		String jwtToken = "Bearer " + userRepo.findById(processRequest.getUserId()).get().getJwtToken();
		log.info("jwtTOkn:{}",jwtToken);
		log.info("Component processing MicroService :: Package and Delivery");
		processResponse = componentProcessingFeignClient.getProcessingDetails(jwtToken, processRequest);
		processResponseRepository.save(processResponse);


		int requestId = processResponse.getRequestId();
		int userID = processResponse.getUserId();
		double processingCharge = processResponse.getProcessingCharge();
		double packagingAndDeliveryCharge = processResponse.getPackagingAndDeliveryCharge();
		String dateOfDelivery = processResponse.getDateOfDelivery().toString();
		model.addAttribute("username", user.getUsername());
		model.addAttribute("requestId", requestId);
		model.addAttribute("userID", userID);
		model.addAttribute("processingCharge", processingCharge);
		model.addAttribute("packagingAndDeliveryCharge", packagingAndDeliveryCharge);
		model.addAttribute("dateOfDelivery", dateOfDelivery);
		model.addAttribute("componentName", componentName);

		return "process.jsp";
	}

	@RequestMapping("/track")
	public String track(@RequestParam(value = "id", required=true) int id,Model model) {
		LocalDate d = componentProcessingFeignClient.trackRequest(id);
		log.info("date:{}",d);
		model.addAttribute("date",d);
		return "track.jsp";
	}
	
	@RequestMapping("/trackPage")
	public String trackPage(Model model) {
		
		return "trackrequest.jsp";
	}
	
	@RequestMapping("/cardDetails")
	public String paymentgateway(Model model) {
		log.info("Handling /cardDetails");
		log.info("Component processing MicroService :: Payment Microservice");
		model.addAttribute("username", user.getUsername());
		log.info("in card");
		return "payment.jsp";
	}
	
	@RequestMapping("/cancelPage")
	public String cancelRequest(Model model) {
		return "cancelrequest.jsp";
	}


	@RequestMapping("/payment")
	public String confirmationMessage(@RequestParam("creditCardNumber") long userCardNumber, Model model) {

		log.info("Handling /payment request");
		processRequest.setCreditCardNumber(userCardNumber);
		String response = "";
		String jwtToken = "Bearer " + userRepo.findById(processRequest.getUserId()).get().getJwtToken();
		int requestID = processResponse.getRequestId();
		long creditCardNumber = processRequest.getCreditCardNumber();
		double totalCharge = processResponse.getProcessingCharge() + processResponse.getPackagingAndDeliveryCharge();
		response = componentProcessingFeignClient.paymentProcessing(jwtToken, requestID, creditCardNumber, 2000.0,
				totalCharge);
		log.info("Component processing MicroService :: Payment Microservice");
		try {
			log.debug("Exception in Payment Microservice", creditCardNumber);
			String componentName = processRequest.getComponentName();
			String username = processRequest.getUserName();
			long creditCarNumber = processRequest.getCreditCardNumber();
			String displayCard = Long.toString(creditCarNumber).substring(0, 4) + "xxxxxxxxxxxx";
			double charge = processResponse.getPackagingAndDeliveryCharge() + processResponse.getProcessingCharge();
			model.addAttribute("requestId", processResponse.getRequestId());
			model.addAttribute("response", response);
			model.addAttribute("componentName", componentName);
			model.addAttribute("username", username);
			model.addAttribute("creditCardNumber", displayCard);
			model.addAttribute("charge", charge);
			if (response.equals(paymentMessage))
				return "success.jsp";
		} catch (Exception e) {
			log.info("InValid Card No :: CardNo not present in database ");
			return "failed.jsp";
		}
		log.info("Payment failed :: Insufficent Fund");
		return "failed.jsp";
	}
	
	@RequestMapping("/reversePayment")
	public String reverseConfirmationMessage(@RequestParam("requestId") int id, @RequestParam("creditCardNumber") long userCardNumber, Model model) {

		log.info("Handling /reversePayment request");
		processRequest.setCreditCardNumber(userCardNumber);
		String jwtToken = "Bearer " + userRepo.findById(processRequest.getUserId()).get().getJwtToken();
		int requestID = id;
		long creditCardNumber = processRequest.getCreditCardNumber();
		
		try {
			String response = componentProcessingFeignClient.reversePaymentProcessing(jwtToken, requestID, creditCardNumber);
			log.info("Component processing MicroService :: Payment Microservice");
		
			if (response.equals("Successful")) {
				String resp = componentProcessingFeignClient.deleteRequest(id);
				return "success.jsp";
			}
			
		} 
		catch (Exception e) {
			log.info("InValid Card No :: CardNo not present in database ");
			return "failed.jsp";
		}
		log.info("Payment failed :: Insufficent Fund");
		return "failed.jsp";
	}
}
