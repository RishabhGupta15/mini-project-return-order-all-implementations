package com.returnordermanagement.returnorderportal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.returnordermanagement.returnorderportal.model.AuthenticationRequest;
import com.returnordermanagement.returnorderportal.model.AuthenticationResponse;
import com.returnordermanagement.returnorderportal.model.PasswordChangeRequest;

@FeignClient(name = "${authentication-service-name}", url = "${authentication-service-url}")
public interface AuthenticationFeignClient {
	
	@PostMapping("/login")
	public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);
	
	@GetMapping(value = "/validate")
	AuthenticationResponse getValidity(@RequestHeader("Authorization") String token);
	
	@PostMapping("/changePassword")
	public AuthenticationResponse changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest);
}

