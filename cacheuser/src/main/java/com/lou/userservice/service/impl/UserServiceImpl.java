/**
 * 
 */
package com.lou.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.stereotype.Service;

import com.lou.userservice.config.IndexCache;
import com.lou.userservice.entity.UserInfo;
import com.lou.userservice.error.ErrorCode;
import com.lou.userservice.exception.UserServiceException;
import com.lou.userservice.service.IUserService;
import com.lou.userservice.util.AESUtil;


/**
 * @author louxiaobo
 *
 */
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	GuavaCacheManager cacheManager;
	
	@Autowired
	IndexCache indexCache;
	
	@Value("${aes.key}")
	String aesKey;
	
	public static AtomicLong userid = new AtomicLong(4);
	
	
	@Override
	public UserInfo findUserById(Long id) {
		if(cacheManager.getCache("id").get(id,UserInfo.class) == null)
		{
			return null;
		}
		UserInfo userInfo = (UserInfo)cacheManager.getCache("id").get(id,UserInfo.class) ;
		if(userInfo.getPassword()!=null)
		{
			try {
				String decpassword = AESUtil.decrypt(userInfo.getPassword(), aesKey);
				userInfo.setPassword(decpassword);
			} catch (Exception e) {
				throw new UserServiceException(ErrorCode.INTERNAL_SERVER_ERROR,"decrpytion error");
			}
		}
		return userInfo;
	}

	@Override
	public UserInfo findUserByUsername(String username) {
		if(cacheManager.getCache("username").get(username,UserInfo.class) == null)
		{
			return null;
		}
		UserInfo userInfo = cacheManager.getCache("username").get(username,UserInfo.class);
		if(userInfo.getPassword()!=null)
		{
			try {
				String decpassword = AESUtil.decrypt(userInfo.getPassword(), aesKey);
				userInfo.setPassword(decpassword);
			} catch (Exception e) {
				throw new UserServiceException(ErrorCode.INTERNAL_SERVER_ERROR,"decrpytion error");
			}
		}
		return userInfo;
	}
	
	@Override
	public List<UserInfo> findAll(String orderBy) {
		List<UserInfo> userInfos = new ArrayList<>();
		int size = indexCache.getIdIndex().size();
		if(orderBy.equals("username"))
		{
			for(int i=0;i<size;i++)
			{
				userInfos.add(cacheManager.getCache("username").get(indexCache.getUsernameIndex().get(i), UserInfo.class));
			}
		}
		else
		{
			for(int i=0;i<size;i++)
			{
				userInfos.add(cacheManager.getCache("id").get(indexCache.getIdIndex().get(i), UserInfo.class));
			}
		}
		
		return userInfos;
	}

	@Override
	public int insertUser(String username, String password, String name) {
		
		if(cacheManager.getCache("username").get(username,UserInfo.class)!=null)
		{
			throw new UserServiceException(ErrorCode.USER_EXISTED,"user existed");
		}
		UserInfo userInfo = new UserInfo();
		userInfo.setId(userid.incrementAndGet());
		userInfo.setUsername(username);
		if(password!=null)
		{
			try {
				password = AESUtil.encrypt(password, aesKey);
			} catch (Exception e) {
				throw new UserServiceException(ErrorCode.INTERNAL_SERVER_ERROR,"encrpytion error");
			}
		}
		userInfo.setPassword(password);
		userInfo.setName(name);
		
		cacheManager.getCache("id").put(userInfo.getId(), userInfo);
		cacheManager.getCache("username").put(username, userInfo);
		
		indexCache.addIdIndex(userInfo.getId());
		indexCache.addUsernameIndex(username);
		
		return 1;
	}

	@Override
	public int updateUser(String username, String password, String name) {
		if(cacheManager.getCache("username").get(username,UserInfo.class)==null)
		{
			throw new UserServiceException(ErrorCode.USER_NOT_FOUND,"user not found");
		}
		UserInfo userInfo = (UserInfo)cacheManager.getCache("username").get(username,UserInfo.class);
		if(password!=null)
		{
			try {
				password = AESUtil.encrypt(password, aesKey);
			} catch (Exception e) {
				throw new UserServiceException(ErrorCode.INTERNAL_SERVER_ERROR,"encrpytion error");
			}
		}
		userInfo.setPassword(password);
		userInfo.setName(name);
		
		cacheManager.getCache("id").put(userInfo.getId(), userInfo);
		cacheManager.getCache("username").put(username, userInfo);
		return 1;
	}

	@Override
	public int deleteUser(String username) {
		if(cacheManager.getCache("username").get(username,UserInfo.class)==null)
		{
			return 0;
		}
		UserInfo userInfo = (UserInfo)cacheManager.getCache("username").get(username,UserInfo.class);
		
		cacheManager.getCache("id").evict(userInfo.getId());
		cacheManager.getCache("username").evict(username);
		
		indexCache.evalIdIndex(userInfo.getId());
		indexCache.evalUsernameIndex(username);
		return 1;
	}

	

}
