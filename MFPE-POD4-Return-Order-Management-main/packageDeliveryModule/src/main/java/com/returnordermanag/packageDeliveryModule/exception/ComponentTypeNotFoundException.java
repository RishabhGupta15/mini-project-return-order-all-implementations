package com.returnordermanag.packageDeliveryModule.exception;

public class ComponentTypeNotFoundException  extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ComponentTypeNotFoundException(String ComponentType) {
		super("there is no no deliveryCharge with-"+ComponentType);
	}
}
