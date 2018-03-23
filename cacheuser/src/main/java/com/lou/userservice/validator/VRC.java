package com.lou.userservice.validator;


public abstract class VRC{

	public static VRC required = new RequiredVR();


	public static VRC minLength(int length){
		return new MinLengthVR(length);
	}
	

	public static VRC maxLength(int length){
		return new MaxLengthVR(length);
	}


	public abstract void validate(String propertyName, Object value);
}
