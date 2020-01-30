package com.comic.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;

import com.comic.pojo.User;
import com.comic.utils.CookieUtil;
import com.comic.utils.SessionUtil;



public class ValidatorInterceptor implements HandlerInterceptor{
    
	@Autowired private SessionUtil session ;
	
	// 定义一个线程域，存放登录用户
    private static final ThreadLocal<User> tl = new ThreadLocal<>();
    
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("调用用户身份验证拦截器");
		System.out.println("session: " + session);
		
        String token = CookieUtil.getCookie("user-token", request);
		
		if(token == "") {
		    //cookie失效重新登录
			//重定向到登录页面
			response.sendRedirect("/loginPage");
			return false;
		}
		
		//1   2    3    4
		//--------------------
		
		User user = session.getUser(token);
		
		if(user == null) {
			//session失效也要重新登录
			response.sendRedirect("/loginPage");
			return false;
		}
		
		//身份验证通过
		
		System.out.println("业务处理前!!!!!!!!!");
		
		//如何将user对象继续下传，给业务逻辑使用，避免再次根据cookie查询用户信息？？？？？？
		//1 将user放到request中 request.setAttribute("user-session", user); 不是特别推荐修改request
		
		//2 本地线程变量
		//在这里将user放入该线程的本地内存中
		tl.set(user);
		
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		tl.remove();
		
		//清除线程变量的内容，防止内存泄漏
		
		// TODO Auto-generated method stub
		System.out.println("业务处理完毕!!!!!!!!!!!!");
	}
	
	public static User getUser() {
		return tl.get();
	}//对外提供getUser方法获取放置的user对象
    
}
