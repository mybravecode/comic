package com.comic.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.comic.interceptor.ValidatorInterceptor;
import com.comic.mapper.ComicInfoMapper;
import com.comic.mapper.UserCollectMapper;
import com.comic.pojo.ComicInfo;
import com.comic.pojo.User;
import com.comic.pojo.UserCollect;
import com.comic.service.CollectionService;
import com.comic.utils.CookieUtil;
import com.comic.utils.JsonUtil;
import com.comic.utils.SessionUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

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
//		String token = CookieUtil.getCookie("user-token", request);
//		
//		if(token == "") {
//			System.out.println("collectComic token is empty");
//			return ;
//		}
//		
//		User user = session.getUser(token);
//		
//		if(user == null) {
//			System.out.println("collectComic user is null");
//			return ;
//		}
		
		User user = ValidatorInterceptor.getUser();
		
		collect.setUserId(user.getId());
		
		System.out.println(JsonUtil.objectToJson(collect));
		System.out.println(JsonUtil.objectToJson(user));
		
		userCollectMapper.insert(collect);
	}
	
	
	@Override
	public String showUserCollection(ModelMap map, HttpServletRequest request) {
		
//		//1.从request获得用户的cookie，获得用户的token
//		String token = CookieUtil.getCookie("user-token", request);
//		
//		if(token == "") {
//			System.out.println("collection token is empty");
//			return "redirect:/index";
//		}
//		//2.从session获得对应的用户
//		User user = session.getUser(token);
//		if(user == null) {
//			System.out.println("collectComic user is null");
//			return "redirect: /index";
//		}
//		
//		List<Integer> comicId = userCollectMapper.
//		userCollectMapper.selectAll()
		//根据userId 查询 UserCollect表，该用户所收藏的动漫id
		
		//直接从本地线程变量中获取在拦截其中已经放置的user对象
		User user = ValidatorInterceptor.getUser();
		System.out.println("get user from interceptor userId: " + user.getId());
		UserCollect userCollectSelect = new UserCollect();
		userCollectSelect.setUserId(user.getId());
		
		List<UserCollect> userCollectList = userCollectMapper.select(userCollectSelect);
		
		//将comicId都取出来
		List <Integer> comicIds = new ArrayList<>();
		
		for(UserCollect u : userCollectList) 
			comicIds.add(u.getComicId());
		
		if(comicIds.size() == 0) {
			map.addAttribute("userCollect", new ArrayList<ComicInfo>());
			
			return "freemarker/collect";
		}
		
		
		//根据动漫id 查询 comic_info表中的 ，该用户所收藏的动漫
//		List<ComicInfo> userCollectComics = new ArrayList<>();
//		for(Integer comicId : comicIds) {
////			ComicInfo comicInfoSelect = new ComicInfo();
////			comicInfoSelect.setId(id);
//			ComicInfo add = comicInfoMapper.selectByPrimaryKey(comicId);
		//  select * from ComicInfo where id = comicId
//			userCollectComics.add(add);
//		}
		
	   //这里分别发送多个请求获得单条comicInfo数据，很明显效率不够高，需要进行查询优化，建议使用一个sql语句就可以查到所有目标数据
		//select * from ComicInfo where id in comicIds
		//ORM
		
		//设置example条件
		Example example = new Example(ComicInfo.class);
		
		//创建查询规则
		Criteria criterion = example.createCriteria();
		
		criterion.andIn("id", comicIds);
		
		List<ComicInfo> userCollectComics = comicInfoMapper.selectByExample(example);
		
		map.addAttribute("userCollect", userCollectComics);
		
		return "freemarker/collect";
	}

    public String deleteUserCollection(int comicId, HttpServletRequest request) {
    	
//		//1.从request获得用户的cookie，获得用户的token
//		String token = CookieUtil.getCookie("user-token", request);
//		
//		if(token == "") {
//			System.out.println("collection token is empty");
//			return "redirect:/index";
//		}
//		//2.从session获得对应的用户
//		User user = session.getUser(token);
//		if(user == null) {
//			System.out.println("collectComic user is null");
//			return "redirect: /index";
//		}
//		
    	
    	User user = ValidatorInterceptor.getUser();
    	
		UserCollect deleteRecord = new UserCollect();
		deleteRecord.setUserId(user.getId());
		deleteRecord.setComicId(comicId);
		userCollectMapper.delete(deleteRecord);
		
    	return "redirect:/user/showcollect";
    	
    }

}
