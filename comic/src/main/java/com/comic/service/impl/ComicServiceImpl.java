package com.comic.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.comic.mapper.ComicClassifyMapper;
import com.comic.mapper.ComicInfoMapper;
import com.comic.mapper.UserCollectMapper;
import com.comic.pojo.ComicClassify;
import com.comic.pojo.ComicInfo;
import com.comic.pojo.User;
import com.comic.pojo.UserCollect;
import com.comic.service.ComicService;
import com.comic.utils.CookieUtil;
import com.comic.utils.JsonUtil;
import com.comic.utils.SessionUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ComicServiceImpl implements ComicService{

	@Autowired private ComicInfoMapper comicMapper;
	
	@Autowired private ComicClassifyMapper comicClassifyMapper;
	
	@Autowired private UserCollectMapper userCollectMapper;
	
	@Autowired private SessionUtil session;
	
	
	@Override
	public String getComicByClassId(int classId, ModelMap map) {
		// TODO Auto-generated method stub
		
		//这种方式不一定是最高效的
		
		
		//根据类别id到comicClassify表中查找对应的comicId并返回列表
		ComicClassify comicClassifySelect = new ComicClassify();
		comicClassifySelect.setClassifyId(classId);
		
		List<ComicClassify> comicClassifyList = comicClassifyMapper.select(comicClassifySelect);
		
		//将comicId都取出来
		List <Integer> comicIds = new ArrayList<>();
		
		for(ComicClassify c: comicClassifyList)
			comicIds.add(c.getComicId());
		
		// 根据查询到的所有属于classId的comicId来找到对应的comic信息
		//此处使用in查询语句
		
		// select * from ComicInfo where id in ${comicIds}
		Example example = new Example(ComicInfo.class);
		Criteria criterion = example.createCriteria();
		
		criterion.andIn("id", comicIds);
		
		List<ComicInfo> comicList=  comicMapper.selectByExample(example);
		
		map.addAttribute("comicList", comicList);
		
		return "freemarker/comic_classify";
		
	}


	@Override
	public String getComicBrief(int comicId, ModelMap map) {
		// TODO Auto-generated method stub
		System.out.println("/brief Service comicId :" + comicId);
		ComicInfo comicInfoS = new ComicInfo();
		comicInfoS.setId(comicId);
        ComicInfo comicInfo = comicMapper.selectOne(comicInfoS);
		
		//考虑comicInfo为空
		
		map.addAttribute("comic", comicInfo);
		System.out.println("/brief Service comicId :" + comicId);
		System.out.println("/brief Service comicInfoJson :" + JsonUtil.objectToJson(comicInfo));


		
		return "freemarker/comic_brief";
	}
}
