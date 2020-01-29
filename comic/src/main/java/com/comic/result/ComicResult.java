package com.comic.result;


/**
 *自定义响应数据结构
 *这个类是提供给门户、ios、安卓、微信商城用的
 *门户接收此类数据后 需要使用本类的方法转换成对应的数据类型格式（类 或者list）
 *其他自行处理
 *200：表示成功
 *500：表示错误，错误信息在msg字段中
 *501：bean验证错误，不管多少个错误都以map形式返回
 *502：拦截器拦截到用户token出错
 *555：异常抛出信息
 * */

public class ComicResult {
    private int status;
    
    private String msg;
    
    private Object data;
    
    
    public ComicResult(int status, String msg, Object data) {
    	this.status = status;
    	this.msg = msg;
    	this.data = data;
    }
    
    public ComicResult(String msg, Object data) {
        this(200, msg, data);	
    }
    
    public static ComicResult ok(String msg, Object data) {
    	return new ComicResult(msg, data);
    }
    
    public static ComicResult ok() {
    	return new ComicResult("OK", null);
    }
    
    public static ComicResult error(String msg, Object data) {
    	return new ComicResult(400, msg, data);
    }
    
    public static ComicResult exception(String msg, Object data) {
    	return new ComicResult(555, msg, data);
    }
    
    
	public int getStatus() {
		return status;
	}
    
	public void setStatus(int status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
    
    
}
