package com.comic.service;


import org.springframework.ui.ModelMap;


public interface ComicService {
    public String getComicByClassId(int classId, ModelMap map);
   
    public String getComicBrief(int comicId, ModelMap map);
    
	public String getSearchComic(String keyword, ModelMap map);

}
