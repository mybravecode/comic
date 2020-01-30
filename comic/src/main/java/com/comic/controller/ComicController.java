package com.comic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.comic.service.ComicService;

@Controller
@RequestMapping("/comic")
public class ComicController {
	@Autowired private ComicService comicService;
		
	@RequestMapping("/classify")
    public String comicClassify(@RequestParam int j, ModelMap map) {
		System.out.println("classId: " + j);
    	return comicService.getComicByClassId(j, map);
    }
	
	
	@RequestMapping("/brief")
	public String getComicBrief(@RequestParam int comicId, ModelMap map) {
		System.out.println("/brief Controller comicId :" + comicId);
		return comicService.getComicBrief(comicId, map);
	}
	
	
	@RequestMapping("/search")
	public String getSearchComic(@RequestParam String keyword, ModelMap map) {
		// System.out.println("/search Controller keyword :" + keyword);
		return comicService.getSearchComic(keyword, map);
	}
}
