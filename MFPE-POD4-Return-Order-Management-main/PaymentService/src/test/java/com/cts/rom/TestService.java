package com.cts.rom;

import static org.mockito.Mockito.when;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;

import com.cts.rom.dao.CreditCardRepository;
import com.cts.rom.model.CreditCard;
import com.cts.rom.service.CardService;

@ExtendWith(MockitoExtension.class)
public class TestService {

	
	@Mock
	CreditCardRepository repo;
	
	@Mock
	CreditCard card;
	
	@InjectMocks
	CardService cardService;
	
	@Test
	public void testProcessPayment() {
		
		card=new CreditCard(1234567L,4000);
		when(repo.findByCardNumber(1234567L)).thenReturn(card);
		when(repo.save(any(CreditCard.class))).thenReturn(card);
		
		
		assertEquals(2000.0,cardService.processPayment(1234567L, 2000),0.00);
		assertEquals(-1,cardService.processPayment(1234567L, 5000),0.0);
		
		
		
	}
}
