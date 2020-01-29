package com.comic.utils;


import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


/*
 * 对redis数据库的操作
 * */


@Component
public class RedisOp {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

    
    public String get(String key) {
    	String val =  stringRedisTemplate.opsForValue().get(key);
    	if (val.equals("null"))
    		return null;
    	return val;
    }
    
    public void set(String key, String value) {
    	stringRedisTemplate.opsForValue().set(key, value);
    }
    
    public boolean hasKey(String key) {
		return stringRedisTemplate.hasKey(key);
	}
    
    public void set(String key, String value, int ttl) {
    	//如果ttl小于0则不设置过期时间
    	if(ttl < 0)
    		stringRedisTemplate.opsForValue().set(key, value);
    	else
    		stringRedisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
    }
    
    
    public long ttl(String key) { //ttl == time to live
    	return stringRedisTemplate.getExpire(key);
    }
    
}
