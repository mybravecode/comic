package com.comic.utils;

import java.util.Random;


/*
 * 产生随机字符串验证码等功能
 * */

public class RandomUtil {
	
	private static Random random = new Random();
	
	private static String allCs = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ#$=+-";
	
	//随机生成[min,max]之间的随机数
	private static int randInt(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}
	
	
	//生成长度为l的随机字符串
    public static String randomStr(int l) {
    	if(l <= 0)
    		return "";
    	
    	StringBuffer strBuffer = new StringBuffer();
    	int csL = allCs.length();
    	
    	for(int i=0;i<l;i++)
    		strBuffer.append(allCs.charAt(randInt(0, csL-1)));
    	return strBuffer.toString();
    	
    }
    
    //生成长度为l的随机验证码
    public static String randomCode(int l) {
    	if(l <= 0)
    		return "";
    	
    	StringBuffer strBuffer = new StringBuffer();
    	for(int i=0;i<l;i++)
    		strBuffer.append(randInt(0, 9));
    	
    	return strBuffer.toString();
    }
    
    
  //生成长度为24的随机字符串
    public static String randomStr() {
    	return randomStr(24);
    	
    }
    
    //生成长度为6的随机验证码
    public static String randomCode() {
    	return randomCode(6);
    }
    
    
    public static void main(String []args) {
    	for(int i=0;i< 20;i++)
    		System.out.println(randInt(0, 3));
        
    	System.out.println(randomStr(16));
    	
    	System.out.println(randomCode(6));
    	
        System.out.println(randomStr());
    	
    	System.out.println(randomCode());
    	
    	
    }
}
