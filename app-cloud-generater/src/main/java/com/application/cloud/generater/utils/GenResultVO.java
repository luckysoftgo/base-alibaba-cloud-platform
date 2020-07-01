package com.application.cloud.generater.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 孤狼
 * @NAME: GenResultVO
 * @DESC: GenResultVO 类设计
 **/
public class GenResultVO extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	
	public GenResultVO() {
		put("code", 0);
	}
	
	public static GenResultVO error() {
		return error(500, "服务器内部异常，请联系管理员");
	}
	
	public static GenResultVO error(String msg) {
		return error(500, msg);
	}
	
	public static GenResultVO error(int code, String msg) {
		GenResultVO r = new GenResultVO();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static GenResultVO success(String msg) {
		GenResultVO r = new GenResultVO();
		r.put("msg", msg);
		return r;
	}
	
	public static GenResultVO success(Map<String, Object> map) {
		GenResultVO r = new GenResultVO();
		r.putAll(map);
		return r;
	}
	
	public static GenResultVO success() {
		return new GenResultVO();
	}

	public GenResultVO put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
