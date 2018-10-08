package com.mcfadyen.shopping.exception;

import java.io.Serializable;
import java.util.Date;

/**
 * A wrapper class for response errors.
 * 
 * @author fabio
 *
 */
public class StandardError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The error status.
	 */
	private Integer status;
	
	/**
	 * The message.
	 */
	private String message;
	
	/**
	 * The time that error occurred.
	 */
	private Date time;
	
	public StandardError(Integer status, String message, Date time) {
		super();
		this.status = status;
		this.message = message;
		this.time = time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
