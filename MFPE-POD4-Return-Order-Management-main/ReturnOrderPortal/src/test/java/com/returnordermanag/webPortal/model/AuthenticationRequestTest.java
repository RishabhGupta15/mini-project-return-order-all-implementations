package com.returnordermanag.webPortal.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthenticationRequestTest {

	AuthenticationRequest authenticationRequest = new AuthenticationRequest();

	@Test
	void testGetUserName() {
		authenticationRequest.setUsername("Lakshit");
		assertEquals("Lakshit", authenticationRequest.getUsername());
	}

	@Test
	void testSetUserName() {
		authenticationRequest.setUsername("Lakshit");
		assertEquals("Lakshit", authenticationRequest.getUsername());
	}

	@Test
	void testGetPassword() {
		authenticationRequest.setPassword("Lakshit");
		assertEquals("Lakshit", authenticationRequest.getPassword());
	}

	@Test
	void testSetPassword() {
		authenticationRequest.setPassword("Lakshit");
		assertEquals("Lakshit", authenticationRequest.getPassword());
	}

}
