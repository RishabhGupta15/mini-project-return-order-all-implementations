package com.returnordermanag.componentProcessModule.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnordermanag.componentProcessModule.client.PackagingAndDeliveryFeignClient;
import com.returnordermanag.componentProcessModule.model.ProcessRequest;
import com.returnordermanag.componentProcessModule.model.ProcessResponse;
import com.returnordermanag.componentProcessModule.repository.ProcessRequestRepository;
import com.returnordermanag.componentProcessModule.repository.ProcessResponseRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IntegralPartService implements ProcessService {
	@Autowired
	private ProcessRequestRepository processRequestRepository;

	@Autowired
	private ProcessResponseRepository processResponseRepository;

	@Autowired
	private PackagingAndDeliveryFeignClient packagingAndDeliveryFeignClient;

	@Override
	public ProcessResponse processDetail(int userID) {
		log.info("Inside Integral Part Service");
		int processingDays = 5;
		double processingCharge = 500;
		double packagingAndDeliveryCharge = 0.0;

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();

		ProcessResponse processResponse = new ProcessResponse();

		Optional<ProcessRequest> result = processRequestRepository.findById(userID);
		ProcessRequest processRequest = result.get();

		boolean isPriorityHigh = processRequest.isPriorityRequest();

		if (isPriorityHigh) {
			processingDays = 2;
			processingCharge += 200;
		}
		// Calculating the expected date of delivery
		c.add(Calendar.DATE, processingDays);

		processResponse.setUserID(userID);
		processResponse.setProcessingCharge(processingCharge);

		log.info("Invoking PackagingAndDelivery Microservice");

		String componentType = processRequest.getComponentType();
		int quantity = processRequest.getQuantityOfDefective();

		packagingAndDeliveryCharge = packagingAndDeliveryFeignClient.getPackagingAndDeliveryCharge(componentType,
				quantity);

		processResponse.setPackagingAndDeliveryCharge(packagingAndDeliveryCharge);
		processResponse.setDateOfDelivery(dateFormat.format(c.getTime()));
		processResponseRepository.save(processResponse);

		log.info("Process Response saved");

		return processResponse;
	}

}
