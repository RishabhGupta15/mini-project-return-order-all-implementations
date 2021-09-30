package com.returnordermanagement.packaginganddelivery.dao;

import org.springframework.stereotype.Service;

import com.returnordermanagement.packaginganddelivery.exception.ComponentTypeNotFound;

@Service
public interface PackagingAndDeliveryService {
	
	public double getPackingAndDeliveryCharge(String componentType, int count) throws ComponentTypeNotFound;

}
