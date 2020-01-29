package com.comic.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

public interface CollectionService {
    public String showUserCollection(ModelMap map, HttpServletRequest request);
    
    public String deleteUserCollection(int comicId, HttpServletRequest request);
    
    public void addUserCollection(int comicId, String comicName, String comicPic,  HttpServletRequest request);
}
