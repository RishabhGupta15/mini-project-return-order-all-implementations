package com.returnordermanag.componentProcessModule.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.returnordermanag.componentProcessModule.client.PaymentFeignClient;
import com.returnordermanag.componentProcessModule.repository.PaymentRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class PaymentServiceTest {

	@Autowired
	PaymentService paymentService;
	@MockBean
	private PaymentRepository paymentRepository;
	@MockBean
	private PaymentFeignClient paymentFeignClient;
	
	@Test
	void testCompleteProcessing_BalanceEqualToZero() {
		String expectedOutput = "Your Payment is successful. Thank you for using our service";
		when(paymentFeignClient.getCurrentBalance(Mockito.anyLong(), Mockito.anyDouble())).thenReturn(0.0);
		String actualOutput = paymentService.completeProcessing(1, 1234567890, 1000, 1000);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	void testCompleteProcessing_BalanceLessThanZero() {
		String expectedOutput = "We are sorry. Your payment could not be processed due to insufficient limit.";
		when(paymentFeignClient.getCurrentBalance(Mockito.anyLong(), Mockito.anyDouble())).thenReturn(-10.0);
		String actualOutput = paymentService.completeProcessing(1, 1234567890, 1000, 1000);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	void testCompleteProcessing_BalanceMoreThanZero() {
		String expectedOutput = "Your Payment is successful. Thank you for using our service";
		when(paymentFeignClient.getCurrentBalance(Mockito.anyLong(), Mockito.anyDouble())).thenReturn(1000.0);
		String actualOutput = paymentService.completeProcessing(1, 1234567890, 1000, 1000);
		assertEquals(expectedOutput, actualOutput);
	}
}
