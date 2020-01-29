package com.comic.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;

public interface UserService {
    public String registerUser(String logname, String password, ModelMap view, HttpServletRequest request, HttpServletResponse response);
    public String loginUser(String logname, String password, ModelMap map, HttpServletRequest request, HttpServletResponse response);
    

}
