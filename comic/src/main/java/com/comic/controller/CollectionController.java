package com.comic.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comic.service.CollectionService;

@Controller
@RequestMapping("/collection")
public class CollectionController {
    
	@Autowired private CollectionService collectionService;
	
	@RequestMapping("/show")
	public String showUserCollection(ModelMap map, HttpServletRequest request) {
	    return collectionService.showUserCollection(map, request);

	}
	
	@RequestMapping("/add")
	public String collectComic(@RequestParam int comicId, @RequestParam String comicName, @RequestParam String comicPic, HttpServletRequest request) {
		
		collectionService.addUserCollection(comicId, comicName, comicPic, request);
		
//		return "redirect:/index";
		
		//return userService.showUserCollection(map, request);
		return "redirect:/collection/show";

	}
	
	@RequestMapping("/delete")
	public String deleteUserCollection(@RequestParam int comicId, ModelMap map, HttpServletRequest request) {
	    collectionService.deleteUserCollection(comicId, request);
	    return "redirect:/collection/show";
	}
	
	
}
