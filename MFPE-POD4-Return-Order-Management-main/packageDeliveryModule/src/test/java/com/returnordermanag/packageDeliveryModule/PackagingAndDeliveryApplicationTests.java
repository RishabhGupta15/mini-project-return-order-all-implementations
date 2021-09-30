package com.returnordermanag.packageDeliveryModule;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.returnordermanag.packageDeliveryModule.service.PackagingAndDeliveryService;

@SpringBootTest
class PackagingAndDeliveryApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void PackagingAndDeliveryTest1() {
		PackagingAndDeliveryService pad = new PackagingAndDeliveryService();
		double actualResult = pad.packagingAndDeliveryCharge(10, "integral");
		double expectedResult = 3500;
		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void PackagingAndDeliveryTest2() {
		PackagingAndDeliveryService pad = new PackagingAndDeliveryService();
		double actualResult = pad.packagingAndDeliveryCharge(10, "accessory");
		double expectedResult = 2000;
		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void PackagingAndDeliveryTest3() {
		PackagingAndDeliveryService pad = new PackagingAndDeliveryService();
		double actualResult = pad.packagingAndDeliveryCharge(0, "IntegralItem || Accessory");
		double expectedResult = 0;
		assertEquals(expectedResult, actualResult);

	}
}
