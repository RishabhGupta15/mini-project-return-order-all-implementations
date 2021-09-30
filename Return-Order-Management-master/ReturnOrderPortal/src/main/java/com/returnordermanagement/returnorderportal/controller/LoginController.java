package com.returnordermanagement.returnorderportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.returnordermanagement.returnorderportal.client.AuthenticationFeignClient;
import com.returnordermanagement.returnorderportal.model.AuthenticationRequest;
import com.returnordermanagement.returnorderportal.model.AuthenticationResponse;
import com.returnordermanagement.returnorderportal.model.PasswordChangeRequest;
import com.returnordermanagement.returnorderportal.model.User;
import com.returnordermanagement.returnorderportal.service.LoginService;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class LoginController {


	@Autowired
	AuthenticationFeignClient authenticationFeignClient;



	@Autowired
	AuthenticationRequest authenticationRequest;
	@Autowired
	PasswordChangeRequest passwordChangeRequest;
	@Autowired
	private AuthenticationResponse authenticationResponse;

	@Autowired
	private LoginService loginservice;
	@Autowired
	User user;
	@Value("${authentication-error}")
	private String authError;


	@RequestMapping("/")
	public String login() {
		log.info("In landing Page");
		return "index.jsp";
	}


	@RequestMapping("/index")
	public String logedin(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {
		authenticationRequest = new AuthenticationRequest(username, password);
		try {
			user.setUsername(username);
			log.info("Login Controller :: Authentication MicroService Called For validation");
			authenticationResponse = authenticationFeignClient.createAuthenticationToken(authenticationRequest);
			String jwtToken = authenticationResponse.getJwtToken();
			boolean isValid = authenticationResponse.getValid();
			loginservice.createUser(101, username, password, jwtToken, isValid);
			if (isValid) {
				model.addAttribute("username", user.getUsername());
				log.info("Authentication MicroService Called For validation :: Login Success");
			}
			return "home.jsp";
		} catch (Exception e) {
			model.addAttribute("invalidlogin", authError);
			log.info("Login Controller :: Authentication MicroService Called For validation :: Login failed");
		}
		return "index.jsp";
	}
	
	@RequestMapping("/passwordPage")
	public String passwordPage(Model model) {
		log.info("Handling /passwordPage");
		model.addAttribute("username", user.getUsername());
		return "password.jsp";
	}
	@RequestMapping("/medianPage")
	public String medianPage(Model model) {
		log.info("Handling /medianPage");
		model.addAttribute("username", user.getUsername());
		return "median.jsp";
	}
	@RequestMapping("/password")
	public String changePassword(@RequestParam("username1") String username, @RequestParam("old_password") String password, @RequestParam("new_password") String passwordnew,
			Model model) {
		log.info("Handling /password");
		passwordChangeRequest = new PasswordChangeRequest(username, password,passwordnew);
		try {
			log.info("Login Controller :: Authentication MicroService Called For validation");
			authenticationResponse = authenticationFeignClient.changePassword(passwordChangeRequest);
			String jwtToken = authenticationResponse.getJwtToken();
			boolean isValid = authenticationResponse.getValid();
			loginservice.createUser(101, username, password, jwtToken, isValid);
			if (isValid) {
				model.addAttribute("username", user.getUsername());
				log.info("Authentication MicroService Called For validation :: Login Success");
			}
			//authenticationFeignClient.changePassword(username, password, passwordnew);
			return "pass_success.jsp";
		} catch (Exception e) {
			model.addAttribute("invalidlogin", authError);
			log.info(e.getMessage());
		}
		return "pass-failed.jsp";
	}
}
