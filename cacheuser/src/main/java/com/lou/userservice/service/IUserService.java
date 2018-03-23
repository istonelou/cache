/**
 * 
 */
package com.lou.userservice.service;

import java.util.List;

import com.lou.userservice.entity.UserInfo;

/**
 * @author louxiaobo
 *
 */
public interface IUserService {

	UserInfo findUserById(Long id);
	UserInfo findUserByUsername(String username);
	List<UserInfo> findAll(String orderBy);
	int insertUser(String username, String password, String name);
	int updateUser(String username, String password, String name);
	int deleteUser(String username);
	
}
