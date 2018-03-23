package com.lou.userservice.exception;

import com.lou.userservice.error.ErrorCode;

public class UserServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -29969820319615379L;
	
	private ErrorCode errorCode;
	private String message;
	
	/**
	 * @param errorCode
	 */
	public UserServiceException(ErrorCode errorCode){
		super(errorCode.name());
		this.setErrorCode(errorCode);
	}
	
	/**
	 * @param errorCode
	 * @param args
	 */
	public UserServiceException(ErrorCode errorCode, String message){
		super(errorCode.name() + ":" + message);
		this.setErrorCode(errorCode);
		this.setMessage(message);
	}

	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	

}
