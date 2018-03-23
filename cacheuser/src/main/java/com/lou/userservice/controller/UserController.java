/**
 * 
 */
package com.lou.userservice.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.lou.userservice.entity.UserInfo;
import com.lou.userservice.error.ErrorCode;
import com.lou.userservice.error.ErrorMessage;
import com.lou.userservice.service.IUserService;
import com.lou.userservice.validator.VRC;
import com.lou.userservice.validator.Validator;



/**
 * @author louxiaobo
 *
 */
@RestController
public class UserController {

	@Autowired
    private IUserService iUserService;
    
    @GetMapping ("user/id")
    public UserInfo findUserByLoginId(@RequestParam String id) {
    	
        return iUserService.findUserById(Long.valueOf(id));
    }
    
    @GetMapping ("user/username")
    public UserInfo findUserByUserId(@RequestParam String username) {
    	
        return iUserService.findUserByUsername(username);
    }
    
    @GetMapping ("user/list")
    public List<UserInfo> findAll(@RequestParam String orderBy) {
    	
        return iUserService.findAll(orderBy);
    }


    @PostMapping("user/add")
    public ResponseEntity<ErrorMessage> addUser(@RequestBody Map<String, String> map){
    	
    	String username = map.get("username");
    	String password = map.get("password");
    	String name = map.get("name");
    	
    	Validator.validateValue(username, "username", VRC.required, VRC.minLength(1),VRC.maxLength(32));
    	Validator.validateValue(username, "name", VRC.required, VRC.minLength(1),VRC.maxLength(32));
    	
    	ErrorCode errorCode = ErrorCode.SUCCESS;
		HttpStatus httpStatus = errorCode.getStatusCode();
		ErrorMessage errorMessage;
		
        if(iUserService.insertUser(username,password,name) >0)
        {
        	errorCode = ErrorCode.SUCCESS;
        	errorMessage = new ErrorMessage(errorCode.getCode(), "Added successfully");
        }
        else {
        	errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        	errorMessage = new ErrorMessage(errorCode.getCode(), "Added failed");
        }
        
        return new ResponseEntity<ErrorMessage>(errorMessage, httpStatus);
    }
    
    @PostMapping("user/update")
    public ResponseEntity<ErrorMessage> updateUser(@RequestBody Map<String, String> map){
    	
    	String username = map.get("username");
    	String password = map.get("password");
    	String name = map.get("name");
    	
    	Validator.validateValue(username, "username", VRC.required, VRC.minLength(1),VRC.maxLength(32));
    	Validator.validateValue(username, "name", VRC.required, VRC.minLength(1),VRC.maxLength(32));
    	
    	ErrorCode errorCode = ErrorCode.SUCCESS;
		HttpStatus httpStatus = errorCode.getStatusCode();
		ErrorMessage errorMessage;
		

        if(iUserService.updateUser(username,password,name) >0)
        {
        	errorCode = ErrorCode.SUCCESS;
        	errorMessage = new ErrorMessage(errorCode.getCode(), "Updated successfully");
        }
        else {
        	errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        	errorMessage = new ErrorMessage(errorCode.getCode(), "Updated failed");
        }
        
        return new ResponseEntity<ErrorMessage>(errorMessage, httpStatus);
    }
    
    @PostMapping("user/delete")
    public ResponseEntity<ErrorMessage> deleteUser(@RequestParam String username){
    	
    	//String username = map.get("username");
    	
    	ErrorCode errorCode = ErrorCode.SUCCESS;
		HttpStatus httpStatus = errorCode.getStatusCode();
		ErrorMessage errorMessage;
		

        if(iUserService.deleteUser(username) >0)
        {
        	errorCode = ErrorCode.SUCCESS;
        	errorMessage = new ErrorMessage(errorCode.getCode(), "Deleted successfully");
        }
        else {
        	errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        	errorMessage = new ErrorMessage(errorCode.getCode(), "Deleted failed");
        }
        
        return new ResponseEntity<ErrorMessage>(errorMessage, httpStatus);
    }
}
