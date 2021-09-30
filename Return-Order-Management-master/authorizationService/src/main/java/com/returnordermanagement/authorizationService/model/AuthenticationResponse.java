package com.returnordermanagement.authorizationService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

	private String jwtToken;
	private Boolean valid;

}
