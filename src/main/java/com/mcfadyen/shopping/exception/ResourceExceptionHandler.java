package com.mcfadyen.shopping.exception;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class Handler for the Objects not found exception.
 * 
 * @author fabio
 *
 */
@ControllerAdvice
public class ResourceExceptionHandler {
	
	/**
	 *The object not found implmentation. 
	 * 
	 * @param e The Exception
	 * @param request The request call.
	 * 
	 * @return A Standard Error. @see StandardError.class
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), Calendar.getInstance().getTime());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
		
}
