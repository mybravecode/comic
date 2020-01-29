package com.comic.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * 将字符串进行MD5哈希，且结果长度为16位
 * */
public class HashUtil {
	public static String hash(String str){
		 
        MessageDigest mDigest = null;
		try {
			mDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        mDigest.update(str.getBytes());
        BigInteger bigInt = new BigInteger(1, mDigest.digest());
        String resultStr = bigInt.toString(16);
        return resultStr;
    }

}
