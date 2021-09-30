package com.cts.rom.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.rom.exception.CardNotFoundException;
import com.cts.rom.model.CreditCard;

public interface CreditCardRepository extends JpaRepository<CreditCard, String> {

	CreditCard findByCardNumber(long cardNumber) throws CardNotFoundException;

}
