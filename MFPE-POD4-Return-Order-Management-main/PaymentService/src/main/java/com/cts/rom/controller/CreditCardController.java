package com.cts.rom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cts.rom.exception.CardNotFoundException;
import com.cts.rom.service.CardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CreditCardController {
	@Autowired
	private CardService cardService;

	// deducting charge from creditcard.
	@GetMapping("/card/{cardNumber}/{charge}")
	@ResponseStatus(code = HttpStatus.OK)
	public double getBalance(@PathVariable long cardNumber, @PathVariable double charge) throws CardNotFoundException {
		try {
			log.info("in Payment Service");
			log.info("Start GetBalance");
			return cardService.processPayment(cardNumber, charge);
		} catch (CardNotFoundException ex) {
			throw new CardNotFoundException();
		}

	}
}
