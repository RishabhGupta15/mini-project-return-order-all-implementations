package com.returnordermanag.packageDeliveryModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.returnordermanag.packageDeliveryModule.service.PackagingAndDeliveryService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PackagingAndDeliveryController{
	
	@Autowired
	private PackagingAndDeliveryService packagingAndDeliveryService;
	
	@GetMapping("/PackagingAndDeliveryCharge/{componentType}/{count}")
	public double PackagingAndDeliveryCost(@PathVariable String componentType, @PathVariable int count) 
	{
		log.info("Inside Package and Delivery Controller");
		return packagingAndDeliveryService.packagingAndDeliveryCharge(count, componentType);
		
	}
	
}