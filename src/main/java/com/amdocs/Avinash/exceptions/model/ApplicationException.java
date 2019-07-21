package com.amdocs.Avinash.exceptions.model;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 2854128011698077180L;

	private String customErrorCode;
	private String systemMessage;
	private HttpStatus httpStatus;
	private String userMessage;

	public ApplicationException() {
	}

	public ApplicationException(String sysErrorMessage, String userMessage, String customErrorCode,
			HttpStatus httpStatus) {
		this.systemMessage = sysErrorMessage;
		this.userMessage = userMessage;
		this.customErrorCode = customErrorCode;
		this.httpStatus = httpStatus;
	}
	public ApplicationException(String userMessage, String customErrorCode,
			HttpStatus httpStatus) {
		this.userMessage = userMessage;
		this.customErrorCode = customErrorCode;
		this.httpStatus = httpStatus;
	}
}
