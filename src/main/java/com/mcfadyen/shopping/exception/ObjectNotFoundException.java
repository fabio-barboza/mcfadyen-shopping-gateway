package com.mcfadyen.shopping.exception;

/**
 * A Exception that for Objects not found exception.
 * 
 * @author fabio
 *
 */
public class ObjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor
	 * 
	 * @param message the error message.
	 */
	public ObjectNotFoundException(String message) {
		super(message);
	}
	
	/**
	 * Constructor
	 * 
	 * @param message The error message.
	 * @param cause The Throwable cause.
	 */
	public ObjectNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}	

}
