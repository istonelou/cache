/**
 * 
 */
package com.lou.userservice.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lou.userservice.error.ErrorCode;
import com.lou.userservice.error.ErrorMessage;

/**
 * @author louxiaobo
 *
 */
@ControllerAdvice(basePackages = { "com.lou.userservice" })
public class UserExceptionHandler {

	/**
	 * UserServiceException handler (Internal Error)
	 * 
	 * @param ex
	 *            UserServiceException
	 * @return
	 */
	@ExceptionHandler(UserServiceException.class)
	ResponseEntity<ErrorMessage> handleNegativeServiceException(UserServiceException ex,
			HttpServletRequest request) {
		
		ErrorCode errorCode = ex.getErrorCode();
		HttpStatus httpStatus = errorCode.getStatusCode();
		
		ErrorMessage errorMessage = new ErrorMessage(errorCode.getCode(), ex.getMessage());
		
		return new ResponseEntity<ErrorMessage>(errorMessage, httpStatus);

	}
	
	/**
	 * Default exception handler
	 * 
	 * @param ex
	 *            Exception
	 * @return
	 */
	@ExceptionHandler(value = { Exception.class, RuntimeException.class })
	ResponseEntity<ErrorMessage> defaultErrorHandler(Exception ex, HttpServletRequest request) {

		ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
		HttpStatus httpStatus = errorCode.getStatusCode();
		ErrorMessage errorMessage = new ErrorMessage(errorCode.getCode(), ex.getMessage());
		
		return new ResponseEntity<ErrorMessage>(errorMessage, httpStatus);
	}
	
}
