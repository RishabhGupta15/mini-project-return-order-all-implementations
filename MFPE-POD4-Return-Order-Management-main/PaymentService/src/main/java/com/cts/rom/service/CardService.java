package com.cts.rom.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.rom.dao.CreditCardRepository;
import com.cts.rom.exception.CardNotFoundException;
import com.cts.rom.model.CreditCard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CardService {

	@Autowired
	CreditCardRepository creditCardRepository;

	/*
	 * 
	 * 1.Service take the creditcard number and processing charge as input
	 * 
	 * 2.retrieve card from database
	 * 
	 * 3.deduct the processing charge from card limit
	 * 
	 * 3.1 if cardlimit>0 then update the card limit in database and return the
	 * cardlimit
	 * 
	 * 3.2 else return -1
	 * 
	 * 
	 */

	public double processPayment(long cardNumber, double charge) throws NoSuchElementException, CardNotFoundException {

		CreditCard card = creditCardRepository.findByCardNumber(cardNumber);

		double balance = card.getCardLimit() - charge;
		if (balance > 0) {
			log.info("Deducting processing charge from Card");
			card.setCardLimit(balance);
			creditCardRepository.save(card);
			return balance;

		} else {
			log.info("Cannot deduct Processing charge >> card limit exceed");
			return -1;
		}
	}
}
