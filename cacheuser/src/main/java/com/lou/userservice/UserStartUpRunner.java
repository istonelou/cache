/**
 * 
 */
package com.lou.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.stereotype.Component;

import com.lou.userservice.config.IndexCache;
import com.lou.userservice.entity.UserInfo;

/**
 * @author louxiaobo
 *
 */
@Component
public class UserStartUpRunner implements CommandLineRunner{
	
	@Autowired
	GuavaCacheManager cacheManager;
	
	@Autowired
	IndexCache indexCache;

	@Override
	public void run(String... args) throws Exception {
		//ArrayList<UserInfo> userInfos = new ArrayList<>();
		UserInfo userInfo1 = new UserInfo();
		userInfo1.setId(1L);
		userInfo1.setUsername("andy");
		userInfo1.setPassword("");
		userInfo1.setName("Andy");
		cacheManager.getCache("id").putIfAbsent(userInfo1.getId(), userInfo1);
		cacheManager.getCache("username").putIfAbsent(userInfo1.getUsername(), userInfo1);
		
		indexCache.addIdIndex(1L);
		indexCache.addUsernameIndex("andy");
		
		UserInfo userInfo2 = new UserInfo();
		userInfo2.setId(2L);
		userInfo2.setUsername("carl");
		userInfo2.setPassword("");
		userInfo2.setName("Carl");
		cacheManager.getCache("id").putIfAbsent(userInfo2.getId(), userInfo2);
		cacheManager.getCache("username").putIfAbsent(userInfo2.getUsername(), userInfo2);
		
		indexCache.addIdIndex(2L);
		indexCache.addUsernameIndex("carl");
		
		UserInfo userInfo3 = new UserInfo();
		userInfo3.setId(3L);
		userInfo3.setUsername("bruce");
		userInfo3.setPassword("");
		userInfo3.setName("Bruce");
		cacheManager.getCache("id").putIfAbsent(userInfo3.getId(), userInfo3);
		cacheManager.getCache("username").putIfAbsent(userInfo3.getUsername(), userInfo3);
		
		
		indexCache.addIdIndex(3L);
		indexCache.addUsernameIndex("bruce");
		
		UserInfo userInfo4 = new UserInfo();
		userInfo4.setId(4L);
		userInfo4.setUsername("dolly");
		userInfo4.setPassword("");
		userInfo4.setName("Dolly");
		cacheManager.getCache("id").putIfAbsent(userInfo4.getId(), userInfo4);
		cacheManager.getCache("username").putIfAbsent(userInfo4.getUsername(), userInfo4);
		
		indexCache.addIdIndex(4L);
		indexCache.addUsernameIndex("dolly");
	}

}
