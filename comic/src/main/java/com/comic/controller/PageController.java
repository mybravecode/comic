package com.comic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/{page}")
	public String comicPage(@PathVariable String page) {
		System.out.println("visisisisissiisissisisi!!!!!!!!!!!");
		return "freemarker/" + page;
	}
	
	@RequestMapping("/")
    public String comicIndex() {
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
