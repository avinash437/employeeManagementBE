package com.amdocs.Avinash.exceptions.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amdocs.Avinash.exceptions.model.ApplicationException;
import com.amdocs.Avinash.resources.rest.models.response.ClientErrorInformation;


@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(Throwable.class)
	@Order(Ordered.LOWEST_PRECEDENCE)
	public ResponseEntity<ClientErrorInformation> handler(HttpServletRequest req, Throwable ex) {
		Throwable e = ex.getCause();
		ClientErrorInformation clientErrorInformation = new ClientErrorInformation();
		
		clientErrorInformation.setCode("INTERNAL_SERVER_ERROR");
		clientErrorInformation.setUserMessage("INTERNAL_SERVER_ERROR");
		e.printStackTrace();
		return new ResponseEntity<ClientErrorInformation>(clientErrorInformation, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(ApplicationException.class)
	@Order(Ordered.LOWEST_PRECEDENCE)
	public ResponseEntity<ClientErrorInformation> handleApplicationException(HttpServletRequest req, ApplicationException ex) {
		Throwable e = ex.getCause();
		ClientErrorInformation clientErrorInformation = new ClientErrorInformation();
		clientErrorInformation.setCode(ex.getCustomErrorCode());
		clientErrorInformation.setUserMessage(ex.getUserMessage());
		
		return new ResponseEntity<ClientErrorInformation>(clientErrorInformation, ex.getHttpStatus());
	}
}
