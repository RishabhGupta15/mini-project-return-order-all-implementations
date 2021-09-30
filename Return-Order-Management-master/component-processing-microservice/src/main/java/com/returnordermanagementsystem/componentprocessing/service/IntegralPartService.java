package com.returnordermanagementsystem.componentprocessing.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.returnordermanagementsystem.componentprocessing.dao.ProcessServiceDao;
import com.returnordermanagementsystem.componentprocessing.exception.UnknownException;
import com.returnordermanagementsystem.componentprocessing.feignclient.PackagingAndDeliveryFeignClient;
import com.returnordermanagementsystem.componentprocessing.model.ProcessRequest;
import com.returnordermanagementsystem.componentprocessing.model.ProcessResponse;
import com.returnordermanagementsystem.componentprocessing.repository.ProcessRequestRepository;
import com.returnordermanagementsystem.componentprocessing.repository.ProcessResponseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IntegralPartService implements ProcessServiceDao {

	@Autowired
	private ProcessRequestRepository processRequestRepository;

	@Autowired
	private ProcessResponseRepository processResponseRepository;

	@Autowired
	private PackagingAndDeliveryFeignClient packagingAndDeliveryFeignClient;

	@Value("${exception.message}")
	private String exceptionMessage;

	@Override
	public ProcessResponse processDetails(int userId) {
		log.info("START processDetails() function from IntegralPartService class");
		ProcessResponse processResponse = new ProcessResponse();
		int processingDuration = 5;
		double processingCharge = 500;
		double packagingAndDeliveryCharge;

		ProcessRequest processRequest = processRequestRepository.findById(userId).get();
		boolean isPriorityHigh = processRequest.isPriorityRequest();

		if (isPriorityHigh) {
			processingDuration = 2;
			processingCharge += 200;
		}

		LocalDate deliveryDate = LocalDate.now().plusDays(processingDuration);

		processResponse.setUserId(userId);
		processResponse.setProcessingCharge(processingCharge);

		String componentType = processRequest.getComponentType();
		int quantity = processRequest.getNumberOfDefective();

		log.info("Calling Packaging and Delivery Microservice to get Packaging and Delivery Charge");

		packagingAndDeliveryCharge = packagingAndDeliveryFeignClient.getPackagingAndDeliveryCharge(componentType,
				quantity);
		processResponse.setPackagingAndDeliveryCharge(packagingAndDeliveryCharge);

		log.info("Something went wrong in Packaging and Delivery Microservice");

		processResponse.setDateOfDelivery(deliveryDate);

		processResponseRepository.save(processResponse);

		log.debug("ProcessResponse Data Saved Successfully {}", processResponse);
		return processResponse;
	}

}
