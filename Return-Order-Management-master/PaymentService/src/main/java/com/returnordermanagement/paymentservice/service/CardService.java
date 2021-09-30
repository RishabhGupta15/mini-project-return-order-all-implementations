package com.returnordermanagement.paymentservice.service;

import javax.transaction.Transactional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnordermanagement.paymentservice.dao.CardRepository;
import com.returnordermanagement.paymentservice.exception.CardNotFoundException;
import com.returnordermanagement.paymentservice.model.CreditCard;

@Service
public class CardService {

	@Autowired
	CardRepository cardRepo;

	private static final Logger lOG = Logger.getLogger("com.returnordermanagement.paymentService");


	@Transactional
	public double processPayment(long cardNumber, double charge) throws CardNotFoundException {

		CreditCard card = cardRepo.findByCardNumber(cardNumber);
		lOG.info("payment processing");
		double rembalance = card.getCardLimit() - charge;
		if (rembalance >= 0) {

			card.setCardLimit(rembalance);
			cardRepo.save(card);
			lOG.info("payment successfull");
			return rembalance;

		}

		else {
			lOG.info("payment failed");
			return -1;
		}
	}
	
	@Transactional
	public double processReversePayment(long cardNumber, double charge) throws CardNotFoundException {

		CreditCard card = cardRepo.findByCardNumber(cardNumber);
		if(card==null)
			throw new CardNotFoundException("No card found");
		lOG.info("payment processing");
		double rembalance = card.getCardLimit() + charge;
		cardRepo.save(card);
		return rembalance;
	}
}
