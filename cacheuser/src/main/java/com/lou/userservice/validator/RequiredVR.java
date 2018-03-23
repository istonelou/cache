package com.lou.userservice.validator;

import com.lou.userservice.error.ErrorCode;
import com.lou.userservice.exception.UserServiceException;
import com.lou.userservice.util.ParamUtil;

public class RequiredVR extends VRC {

	@Override
	public void validate(String fieldName, Object value) {
		if (ParamUtil.isEmpty(value)) {
			throw new UserServiceException(ErrorCode.USER_PARAM_ERROR, 
					fieldName + " is mandatory field.");
		}
	}
}
