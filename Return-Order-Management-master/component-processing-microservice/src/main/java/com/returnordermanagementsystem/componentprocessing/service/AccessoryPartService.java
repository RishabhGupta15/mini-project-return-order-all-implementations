package com.returnordermanagementsystem.componentprocessing.service;

import java.time.LocalDate;
import java.util.Optional;

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

@Service
@Slf4j
public class AccessoryPartService implements ProcessServiceDao {

	@Autowired
	private ProcessResponseRepository processResponseRepository;

	@Autowired
	private ProcessRequestRepository processRequestRepository;

	@Autowired
	private PackagingAndDeliveryFeignClient packagingAndDeliveryFeignClient;

	@Value("${exception.message}")
	private String exceptionMessage;

	@Override
	public ProcessResponse processDetails(int userId) {
		log.info("START processDetails() function from AccessoryPartService class");
		ProcessResponse processResponse = new ProcessResponse();
		int processingDuration = 5;
		double processingCharge = 300;
		double packagingAndDeliveryCharge;

		LocalDate deliveryDate = LocalDate.now().plusDays(processingDuration);

		processResponse.setUserId(userId);
		processResponse.setProcessingCharge(processingCharge);
		Optional<ProcessRequest> processRequest1 = processRequestRepository.findById(userId);
		ProcessRequest processRequest = processRequest1.get();

		log.info("Calling Packaging and Delivery Microservice");

		packagingAndDeliveryCharge = packagingAndDeliveryFeignClient.getPackagingAndDeliveryCharge(
				processRequest.getComponentType(), processRequest.getNumberOfDefective());
		processResponse.setPackagingAndDeliveryCharge(packagingAndDeliveryCharge);

		processResponse.setDateOfDelivery(deliveryDate);

		processResponseRepository.save(processResponse);
		log.debug("ProcessResponse Data Saved Successfully {}", processResponse);
		return processResponse;
	}
}
