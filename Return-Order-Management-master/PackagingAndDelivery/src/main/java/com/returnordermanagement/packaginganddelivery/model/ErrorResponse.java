package com.returnordermanagement.packaginganddelivery.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ErrorResponse {
	
	private LocalDateTime timestamp;
	private HttpStatus status;
	private String message;
	private String reason;


}
