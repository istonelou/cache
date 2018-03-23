/**
 * Any software product designated as "Samsung Proprietary Software,"
 * including computer software and may include associated media, printed
 * materials, and
 * "online" or electronic documentation ("SOFTWARE PRODUCT") is a c opyrighted
 * and
 * proprietary property of SAMSUNG ELECTRONICS CO., LTD ("Samsung").
 **
 The SOFTWARE PRODUCT must
 * (i) be used for Samsung’s approved business purposes only,
 * (ii) not be contaminated by open source codes,
 * (iii) must not be used in any ways that will require it to be disclosed or
 * licensed freely to third parties or public,
 * (vi) must not be subject to reverse engineering, decompling or diassembling.
 **
 Samsung does not grant the recipient any intellectual property rights,
 * indemnities or warranties and
 * takes on no obligations regarding the SOFTWARE PRODUCT
 * except as otherwise agreed to under a separate written agreement with the
 * recipient,
 **
 Revision History
 * Author Date Description
 * ------------------- ---------------- --------------------------
 * Mzhaiyh 2015-5-22 First Draft
 */

package com.lou.userservice.error;

import org.springframework.http.HttpStatus;


public enum ErrorCode{

	SUCCESS("0000", HttpStatus.OK),
	USER_EXISTED("1001", HttpStatus.OK),
	USER_NOT_FOUND("1002", HttpStatus.OK),
	USER_CREATE_FAILED("1003",HttpStatus.OK),
	USER_UPDATE_FAILED("1004",HttpStatus.OK),
	USER_DELETE_FAILED("1005",HttpStatus.OK),
	
	USER_PARAM_ERROR("2001",HttpStatus.OK),
	INTERNAL_SERVER_ERROR("5001",HttpStatus.OK),
	;
	
	
	private String code;
	private HttpStatus statusCode;

	private ErrorCode(String code, HttpStatus statusCode){
		this.code = code;
		this.statusCode = statusCode;
	}

	public String getCode(){
		return code;
	}

	public HttpStatus getStatusCode(){
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode){
		this.statusCode = statusCode;
	}
}
