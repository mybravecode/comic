package com.comic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.stat.TableStat.Mode;
import com.comic.interceptor.ValidatorInterceptor;
import com.comic.pojo.User;

@Controller
public class PageController {
	
	@RequestMapping("/{page}")
	public String comicPage(@PathVariable String page, ModelMap map) {
        User user = ValidatorInterceptor.getUser();	
		map.addAttribute("username", user.getName());
		System.out.println("visisisisissiisissisisi!!!!!!!!!!!");
		return "freemarker/" + page;
	}
	
	@RequestMapping("/")
    public String comicIndex(ModelMap map) {
		User user = ValidatorInterceptor.getUser();
		map.addAttribute("username", user.getName());
		
    	return "freemarker/index";
    }
	
//	
	@RequestMapping("/registerPage")
    public String comicRegister(ModelMap map) {
		map.addAttribute("errorCode", "");
		map.addAttribute("errorMsg", "");
    	return "freemarker/registerPage";
    }


	@RequestMapping("/loginPage")
    public String comicLogin(ModelMap map) {
		map.addAttribute("errorCode", "");
		map.addAttribute("errorMsg", "");
    	return "freemarker/loginPage";
    }
	
}
