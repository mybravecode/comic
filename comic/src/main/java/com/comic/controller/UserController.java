package com.comic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comic.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	//包含登录和注册接口
	//登录和注册后都 需要分别设置cookie和session
	
	@Autowired private UserService userService;
	
	@RequestMapping("/register")
	public String registerUser(
			@RequestParam String logname, @RequestParam String password, @RequestParam String comfirmPassword, ModelMap map
		    ,HttpServletRequest request, HttpServletResponse response) {
		//注册接口，处理注册逻辑
		
		//System.out.println(logname + password + comfirmPassword);
		
		//1. 用户名不能为空
		if(logname == "") {
			map.addAttribute("errorCode", 333);
			map.addAttribute("errorMsg", "用户名不能为空");
			return "freemarker/registerPage";
		}
		
		//2. 密码和确认密码必须一样
		if(password == "" || ! password.equals(comfirmPassword)) {
			map.addAttribute("errorCode", 333);
			map.addAttribute("errorMsg", "密码不能为空,且必须和确认密码相同");
			return "freemarker/registerPage";
		}
		
//		map.addAttribute("errorCode", 000);
//		map.addAttribute("errorMsg", "000");
//		return "freemarker/index";
	    return userService.registerUser(logname, comfirmPassword, map, request, response);

	}
	
	@RequestMapping("/login")
	public String registerUser(
			@RequestParam String logname, @RequestParam String password, ModelMap map
			,HttpServletRequest request, HttpServletResponse response) {
		//注册接口，处理注册逻辑
		
		//System.out.println(logname + password + comfirmPassword);
		
		//1. 用户名和密码不能为空
		if(logname == "" || password == "") {
			map.addAttribute("errorCode", 333);
			map.addAttribute("errorMsg", "用户名和密码不能为空");
			return "freemarker/loginPage";
		}
		
		//密码要正确

	    return userService.loginUser(logname, password, map, request, response);

	}
}

