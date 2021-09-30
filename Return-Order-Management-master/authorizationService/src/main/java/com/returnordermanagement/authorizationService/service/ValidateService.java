package com.returnordermanagement.authorizationService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.returnordermanagement.authorizationService.model.AuthenticationResponse;
import com.returnordermanagement.authorizationService.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ValidateService {
	@Autowired
	private JwtUtil jwtutil;

	public AuthenticationResponse validate(String token) {
		AuthenticationResponse authenticationResponse = new AuthenticationResponse();

		String jwt = token.substring(7);
		log.info("jwt token in the checking part:{}",jwt);
		authenticationResponse.setJwtToken(jwt);
		boolean isTrue = jwtutil.validateToken(jwt);
		if (!isTrue) {
			authenticationResponse.setValid(false);
		}
		else
			authenticationResponse.setValid(true);
		return authenticationResponse;
	}
}
