
package com.lou.userservice.error;

import com.fasterxml.jackson.annotation.JsonProperty;



public class ErrorMessage{

	
	/** The code. */
	private String resultCode;
	
	/** The message. */
	@JsonProperty("resultMessage")
	private String resultMessage;

	public String getResultCode(){
		return resultCode;
	}

	public void setResultCode(String resultCode){
		this.resultCode = resultCode;
	}

	@JsonProperty("resultMessage")
	public String getResultMessage(){
		return resultMessage;
	}

	public void setResultMessage(String resultMessage){
		this.resultMessage = resultMessage;
	}


	/** The suss. */
	private static ErrorMessage SUSS = new ErrorMessage("0000", "request success");
	
	/**
	 * Add dummy constructor to fix the No suitable constructor found Exception
	 * for type From Jackson.
	 */
	public ErrorMessage() {		
	}
	
	/**
	 * Instantiates a new error message.
	 *
	 * @param code
	 *            the code
	 * @param message
	 *            the message
	 */
	public ErrorMessage(String resultCode, String message) {
		this.resultCode = resultCode;
		this.resultMessage = message;
	}

	
	/**
	 * No error message.
	 *
	 * @return the error message
	 */
	public static ErrorMessage noErrorMessage(){
		return SUSS;
	}
}
