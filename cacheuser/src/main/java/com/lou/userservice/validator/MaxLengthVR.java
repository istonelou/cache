package com.lou.userservice.validator;

import com.lou.userservice.error.ErrorCode;
import com.lou.userservice.exception.UserServiceException;
import com.lou.userservice.util.ParamUtil;

public class MaxLengthVR extends VRC {

	protected int maxLength;

	public MaxLengthVR(int maxLength) {
		this.maxLength = maxLength;
	}

	@Override
	public void validate(String fieldName, Object value) {
		if (ParamUtil.isEmpty(value)) {
			return;
		}
		if (value instanceof String) {
			if (((String) value).length() > maxLength) {
				throw new UserServiceException(ErrorCode.USER_PARAM_ERROR, 
						fieldName + " length is too long. Maximum length is " + maxLength);
			}
		} else {
			throw new UserServiceException(ErrorCode.USER_PARAM_ERROR, 
					fieldName + " type must be String.");
		}
	}

}
