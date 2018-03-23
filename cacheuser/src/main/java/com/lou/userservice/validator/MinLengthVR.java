package com.lou.userservice.validator;

import com.lou.userservice.error.ErrorCode;
import com.lou.userservice.exception.UserServiceException;


public class MinLengthVR extends VRC {

	private int minLength;

	public MinLengthVR(int minLength) {
		this.minLength = minLength;
	}

	@Override
	public void validate(String fieldName, Object value) {
		if (value == null) {
			return;
		}
		if (value instanceof String) {
			if (((String) value).length() == 0) {
				return;
			}
			if (((String) value).length() < minLength) {
				throw new UserServiceException(ErrorCode.USER_PARAM_ERROR, 
						fieldName + " length is too short. Min length is " + minLength);
			}
		} else {
			throw new UserServiceException(ErrorCode.USER_PARAM_ERROR, 
					fieldName + " type must be String.");
		}
	}


}
