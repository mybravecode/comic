package com.comic.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.comic.mapper.ComicInfoMapper;
import com.comic.mapper.UserCollectMapper;
import com.comic.pojo.ComicInfo;
import com.comic.pojo.User;
import com.comic.pojo.UserCollect;
import com.comic.service.CollectionService;
import com.comic.utils.CookieUtil;
import com.comic.utils.JsonUtil;
import com.comic.utils.SessionUtil;

@Service
public class CollectionServiceImpl implements CollectionService{
	
	@Autowired private SessionUtil session;
	@Autowired private UserCollectMapper userCollectMapper;
	@Autowired private ComicInfoMapper comicInfoMapper;
	
	
	@Override
	public void addUserCollection(int comicId, String comicName, String comicPic, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		
        UserCollect collect = new UserCollect();
		
		collect.setComicId(comicId);
		collect.setComicName(comicName);
		collect.setComicPicture(comicPic);
		
		//从session中获取用户对象
		//1.从cookie中取出令牌token
		//2.使用令牌从session(redis)中取出用户对象即可
		//这里cookie有非常多，我们需要找到验证用户身份的cookie
		String token = CookieUtil.getCookie("user-token", request);
		
		if(token == "") {
			System.out.println("collectComic token is empty");
			return ;
		}
		
		User user = session.getUser(token);
		
		if(user == null) {
			System.out.println("collectComic user is null");
			return ;
		}
		
		collect.setUserId(user.getId());
		
		System.out.println(JsonUtil.objectToJson(collect));
		System.out.println(JsonUtil.objectToJson(user));
		
		userCollectMapper.insert(collect);
	}
	
	
	@Override
	public String showUserCollection(ModelMap map, HttpServletRequest request) {
		
		//1.从request获得用户的cookie，获得用户的token
		String token = CookieUtil.getCookie("user-token", request);
		
		if(token == "") {
			System.out.println("collection token is empty");
			return "redirect:/index";
		}
		//2.从session获得对应的用户
		User user = session.getUser(token);
		if(user == null) {
			System.out.println("collectComic user is null");
			return "redirect: /index";
		}
		
//		List<Integer> comicId = userCollectMapper.
//		userCollectMapper.selectAll()
		//根据userId 查询 UserCollect表，该用户所收藏的动漫id
		UserCollect userCollectSelect = new UserCollect();
		userCollectSelect.setUserId(user.getId());
		
		List<UserCollect> userCollectList = userCollectMapper.select(userCollectSelect);
		
		//将comicId都取出来
		List <Integer> comicIds = new ArrayList<>();
		
		for(UserCollect u : userCollectList) 
			comicIds.add(u.getComicId());
		//根据动漫id 查询 comic_info表中的 ，该用户所收藏的动漫
		List<ComicInfo> userCollectComics = new ArrayList<>();
		for(Integer comicId : comicIds) {
//			ComicInfo comicInfoSelect = new ComicInfo();
//			comicInfoSelect.setId(id);
			ComicInfo add = comicInfoMapper.selectByPrimaryKey(comicId);
			userCollectComics.add(add);
		}
	
			map.addAttribute("userCollect", userCollectComics);
		
		return "freemarker/collect";
	}

    public String deleteUserCollection(int comicId, HttpServletRequest request) {
    	
		//1.从request获得用户的cookie，获得用户的token
		String token = CookieUtil.getCookie("user-token", request);
		
		if(token == "") {
			System.out.println("collection token is empty");
			return "redirect:/index";
		}
		//2.从session获得对应的用户
		User user = session.getUser(token);
		if(user == null) {
			System.out.println("collectComic user is null");
			return "redirect: /index";
		}
		
		UserCollect deleteRecord = new UserCollect();
		deleteRecord.setUserId(user.getId());
		deleteRecord.setComicId(comicId);
		userCollectMapper.delete(deleteRecord);
		
    	return "redirect:/user/showcollect";
    	
    }

}
