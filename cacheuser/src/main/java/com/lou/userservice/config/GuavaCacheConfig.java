package com.lou.userservice.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.cache.CacheBuilder;

/**
 * @author louxiaobo
 *
 */
@Configuration
public class GuavaCacheConfig {

	public static final String ID = "id";
    public static final String USERNAME = "username";

    @Bean
    public GuavaCacheManager cacheManager() {
    	
    	CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.DAYS);
        GuavaCacheManager cacheManager = new GuavaCacheManager(ID,USERNAME);
        cacheBuilder.concurrencyLevel(4);
        cacheManager.setCacheBuilder(cacheBuilder);
        
        return cacheManager;
    }
    
    @Bean
	public IndexCache indexCache()
	{
		return new IndexCache();
	}
}
