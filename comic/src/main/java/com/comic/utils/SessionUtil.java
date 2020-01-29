package com.comic.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties.Settings;
import org.springframework.stereotype.Component;

import com.comic.pojo.User;

/*
 * session工具，主要用来在redis中存放session数据，或者取session数据
 * 一个session就是sessionId: sessionValue的形式，供后端进行用户的身份验证 
 * */


@Component
public class SessionUtil {
    @Autowired private RedisOp redis;
    
    //根据userId和User将用户信息保存到redis中作为一个信息session
    public void setUserSession(int userId, User user, int ttl) {
    	String userJson = JsonUtil.objectToJson(user);
    	
    	String sessionId = "userId-" + userId + "-info";
    	redis.set(sessionId, userJson, ttl);
    }
    
  //根据userId从redis中取出用户
    public User getUser(int userId) {
    	String sessionId = "userId-" + userId + "-info";
    	String userJson = redis.get(sessionId);
    	//redis里存了3个值么？redis.set(sessionId, userJson, ttl); 通过redis.get(sessionId)得到的是什么？userJson？
    	
    	if(userJson == null)
    		return null;
    	
    	return JsonUtil.jsonToPojo(userJson, User.class);
    }
    
    
    //
    
  //根据token和User将用户信息保存到redis中作为一个信息session
    public void setUserSession(String token, User user, int ttl) {
    	String userJson = JsonUtil.objectToJson(user);
    	
    	String sessionId = "userToken-" + token + "-info";
    	redis.set(sessionId, userJson, ttl);
    }
    
  //根据token从redis中取出用户
    public User getUser(String token) {
    	String sessionId = "userToken-" + token + "-info";
    	String userJson = redis.get(sessionId);
    	
    	if(userJson == null)
    		return null;
    	
    	return JsonUtil.jsonToPojo(userJson, User.class);
    }
    
    
    //验证码功能
  //根据用户电话号码和验证码作为用户验证码session
    public void setUserVCode(int phone, String vCode, int ttl) {
    	String sessionId = "user-" + phone + "-vcode";
    	redis.set(sessionId, vCode, ttl);
    }
    
  //根据用户电话号码取出对应的验证码
    public String getUserVCode(int phone) {
    	String sessionId = "user-" + phone + "-vcode";
    	String vCode = redis.get(sessionId);
    	return vCode;
    }
}
