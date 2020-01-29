package com.comic.service.impl;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.comic.mapper.ComicInfoMapper;
import com.comic.mapper.UserCollectMapper;
import com.comic.mapper.UserMapper;
import com.comic.pojo.User;
import com.comic.service.UserService;
import com.comic.utils.CookieUtil;
import com.comic.utils.HashUtil;
import com.comic.utils.JsonUtil;
import com.comic.utils.RandomUtil;
import com.comic.utils.SessionUtil;


@Service
public class UserServiceImpl implements UserService{
	@Autowired UserMapper userMapper;
	@Autowired UserCollectMapper userCollectMapper;
	@Autowired ComicInfoMapper comicInfoMapper;
    
	@Autowired SessionUtil session;
	
	private void setCookieAndSession(User user, HttpServletResponse response, int ttl) {
		//1.设置cookie到浏览器中(只需要添加到response中即可)
		String tokenValue = RandomUtil.randomStr();
		
		
		//进行身份验证的cookie叫做user-token
		CookieUtil.setCookie("user-token", tokenValue, ttl, response, "/");//
		
		// CookieUtil.setCookie("test-cookie", "test-by-fzz", ttl, response, "/fzz");//
		
		//2.设置session到redis服务器中
		session.setUserSession(tokenValue, user, ttl); //
				
		/*此后用户再次访问该网站，则可以取出cookie中的token，再从redis中根据token取出该用户信息，便可进行用户身份验证
		String tokenValue = CookieUtil.getCookie("user-token", request);
		User user = session.getUser(tokenValue)
		      */
		
		System.out.println("token: " + tokenValue);
		System.out.println("user: " + JsonUtil.objectToJson(user));
	}
	
	@Override
	public String registerUser(String logname, String password, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		if(password.length() > 10 || password.length() < 5) {
			map.addAttribute("errorCode", 333);
			map.addAttribute("errorMsg", "密码需要在5-10位之间");
			return "freemarker/registerPage";
		}
		User user = new User();
		user.setName(logname);
		user.setPassword(HashUtil.hash(password));
		
		try {
			//这里要回显id
			userMapper.insert(user);
			
			
		}catch (Exception e) {
		
			map.addAttribute("errorCode", 333);
			map.addAttribute("errorMsg", e.getMessage());
			return "freemarker/registerPage";

		}
		
		//用户注册成功则可以设置cookie并保存session，以后用户可以凭借cookie和session来进行身份验证
		
		setCookieAndSession(user, response, 5 * 60); //5 min
		
		//
		return "redirect:/index";
	}

	@Override
	public String loginUser(String logname, String password, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
        //验证用户和密码的正确性
		
		//查询用户，如果用户不存在提示用户不存在
		// 如果存在则对比密码
		
	    User user = new User();
	    user.setName(logname);
	    
	    User selectUser = userMapper.selectOne(user);
	    
	    if(selectUser == null) {
		    map.addAttribute("errorCode", 422);
			map.addAttribute("errorMsg", "用户不存在");
	    	return "freemarker/loginPage";
	    }
	   
	    if(! selectUser.getPassword().equals(HashUtil.hash(password))) {
	    	 map.addAttribute("errorCode", 422);
			map.addAttribute("errorMsg", "用户密码不正确");
		    return "freemarker/loginPage";
	    }
	    
	    //
	    //用户存在且密码正确
	    
	   //用户登录成功则可以设置cookie并保存session，以后用户可以凭借cookie和session来进行身份验证
		
	  	setCookieAndSession(selectUser, response, 2 * 60 * 60); //5 min
	  		
	    return "redirect:/index";
	}

}
