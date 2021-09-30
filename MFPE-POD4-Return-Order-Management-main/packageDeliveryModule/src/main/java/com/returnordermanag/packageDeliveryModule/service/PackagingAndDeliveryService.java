package com.returnordermanag.packageDeliveryModule.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PackagingAndDeliveryService {
	public double packagingAndDeliveryCharge(int count, String componentType) {
		log.info("Calculating Packaging And Delivery Charge");
		int protectiveSheath = 50; // Protective Sheath cost
		int integralPackaging = 100;
		int accessoryPackaging = 50;
		int integralDelivery = 200;
		int accessoryDelivery = 100;
		double packagingAndDeliveryCharge = 0.0;
		if (componentType.equals("integral")) {
			packagingAndDeliveryCharge = protectiveSheath + integralPackaging + integralDelivery;
			return (packagingAndDeliveryCharge * count);
		} else if (componentType.equals("accessory")) {
			packagingAndDeliveryCharge = protectiveSheath + accessoryPackaging + accessoryDelivery;
			return (packagingAndDeliveryCharge * count);
		}
		return packagingAndDeliveryCharge;
	}
}

