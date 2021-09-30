package com.returnordermanag.webPortal.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class UserDetailsTest {

	UserDetails userDetails = new UserDetails();
	

	@Test
	void testGetUserID() {
		userDetails.setUserID(1);
		assertEquals(1, userDetails.getUserID());
	}
	
	@Test
	void testSetUserID() {
		userDetails.setUserID(1);
		assertEquals(1, userDetails.getUserID());
	}
	
	@Test
	void testGetUserName() {
		userDetails.setUserName("Lakshit");
		assertEquals("Lakshit", userDetails.getUserName());
	}
	
	@Test
	void testSetUserName() {
		userDetails.setUserName("Lakshit");
		assertEquals("Lakshit", userDetails.getUserName());
	}
	@Test
	void testGetPassword() {
		userDetails.setPassword("Lakshit");
		assertEquals("Lakshit", userDetails.getPassword());
	}
	
	@Test
	void testSetPassword() {
		userDetails.setPassword("Lakshit");
		assertEquals("Lakshit", userDetails.getPassword());
	}
	
	@Test
	void testGetJwtToken() {
		userDetails.setJwtToken(
				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTYxNTIyNTc5NywiaWF0IjoxNjE1MjIzOTk3fQ.IRD8qCvu6neU1Atkic1qcNnJd_YZEnzFa1F3oaBusiU");
		assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTYxNTIyNTc5NywiaWF0IjoxNjE1MjIzOTk3fQ.IRD8qCvu6neU1Atkic1qcNnJd_YZEnzFa1F3oaBusiU", userDetails.getJwtToken());
	}
	
	@Test
	void testSetJwtToken() {
		userDetails.setJwtToken(
				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTYxNTIyNTc5NywiaWF0IjoxNjE1MjIzOTk3fQ.IRD8qCvu6neU1Atkic1qcNnJd_YZEnzFa1F3oaBusiU");
		assertEquals("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTYxNTIyNTc5NywiaWF0IjoxNjE1MjIzOTk3fQ.IRD8qCvu6neU1Atkic1qcNnJd_YZEnzFa1F3oaBusiU", userDetails.getJwtToken());
	}
	
	
	}
	