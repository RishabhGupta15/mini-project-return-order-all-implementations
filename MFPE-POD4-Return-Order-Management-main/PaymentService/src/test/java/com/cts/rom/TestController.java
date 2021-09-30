package com.cts.rom;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.rom.controller.CreditCardController;
import com.cts.rom.service.CardService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = CreditCardController.class)
public class TestController {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	CardService cardService;
	
	@Test
	public void testGetCallPositive() throws Exception {
		when(cardService.processPayment(Mockito.anyLong(), Mockito.anyDouble())).thenReturn(2000.0);
		Long cardnumber=1234567L;
		Double amt=5000.0;
		
		mockMvc.perform(get("/card/{cardNumber}/{charge}",cardnumber,amt)).andExpect(status().isOk());
		
		
		
	}
	
	@Test
	public void testGetCallNegative() throws Exception {
		when(cardService.processPayment(Mockito.anyLong(), Mockito.anyDouble())).thenThrow(new IllegalArgumentException("in test call"));
		Long cardnumber=1234567L;
		Double amt=5000.0;
		mockMvc.perform(get("/card/{cardNumber}/{charge}",cardnumber,amt)).andExpect(status().isInternalServerError());
	}
}
