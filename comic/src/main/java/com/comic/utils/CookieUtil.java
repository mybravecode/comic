package com.comic.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/*
 * 
 * cookie工具，可以通过response设置cookie给浏览器
 * 也可以通过request取出从客户端携带的cookie
 * */
public class CookieUtil {
	
	//设置cookie到response中
    public static void setCookie(String cookieName, String cookieValue, int expire, HttpServletResponse response, String path) {
    	Cookie cookie = new Cookie(cookieName, cookieValue);
    	cookie.setPath(path);
    
    	cookie.setMaxAge(expire);
    	response.addCookie(cookie);
    }
    
    public static String getCookie(String cookieName, HttpServletRequest request) {
    	Cookie []cookies = request.getCookies();
    			
    	if (cookies == null)
    		return "";
    	
    	for(Cookie c: cookies)
    		if(c.getName().equals(cookieName))
    			return c.getValue();
    	
    	return "";
    }
}
