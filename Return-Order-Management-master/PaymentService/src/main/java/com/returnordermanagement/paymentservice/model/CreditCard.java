package com.returnordermanagement.paymentservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditCard {

	@Id
	private long cardNumber;
	private double cardLimit;
	
	}
	
	
	
	

	

