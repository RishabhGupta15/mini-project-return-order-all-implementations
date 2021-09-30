package com.returnordermanagement.authorizationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.returnordermanagement.authorizationService.Repository.UserRepository;
import com.returnordermanagement.authorizationService.exception.BadCredentialException;
import com.returnordermanagement.authorizationService.model.AuthenticationRequest;
import com.returnordermanagement.authorizationService.model.AuthenticationResponse;
import com.returnordermanagement.authorizationService.model.MyUser;
import com.returnordermanagement.authorizationService.model.PasswordChangeRequest;
import com.returnordermanagement.authorizationService.service.MyUserDetailsService;
import com.returnordermanagement.authorizationService.service.ValidateService;
import com.returnordermanagement.authorizationService.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private ValidateService validateService;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws BadCredentialException, NullPointerException {
		boolean isvalid=true;
		log.info("Login Authenticating");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException NullPointerException) {
			log.info("Bad Credential");
			isvalid=false;
			throw new BadCredentialException();
			
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		log.info("generated token:{}",jwt);

		return ResponseEntity.ok(new AuthenticationResponse(jwt, isvalid));
	}

	@GetMapping("/validate")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") final String token) {

		log.info("Validate token");
		return validateService.validate(token);
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChangeRequest passwordChangeRequest)
			throws BadCredentialException, NullPointerException {
		boolean isvalid=true;
		log.info("Login Authenticating");
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					passwordChangeRequest.getUsername(), passwordChangeRequest.getOldPassword()));
			
			String hPassword = BCrypt.hashpw(passwordChangeRequest.getNewPassword(),BCrypt.gensalt(10));
			MyUser user1 = userRepo.findByUsername(passwordChangeRequest.getUsername());
			user1.setPassword(hPassword);
			userRepo.save(user1);
		} catch (BadCredentialsException NullPointerException) {
			log.info("Bad Credential");
			isvalid=false;
			throw new BadCredentialException();
			
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(passwordChangeRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);
		log.info("generated token:{}",jwt);

		return ResponseEntity.ok(new AuthenticationResponse(jwt, isvalid));
	}
}
