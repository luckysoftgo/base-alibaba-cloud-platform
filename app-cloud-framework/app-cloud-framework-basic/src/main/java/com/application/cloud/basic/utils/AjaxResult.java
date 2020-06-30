package com.application.cloud.basic.utils;

import com.application.cloud.basic.common.result.BaseCommonMsg;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : 孤狼
 * @NAME: AjaxResult
 * @DESC: AjaxResult 类设计
 **/
public class AjaxResult extends HashMap<String, Object> {

	private static final long serialVersionUID = 1L;
	
	public AjaxResult() {
		put("code", BaseCommonMsg.SYSTEM_SUCCESS_MSG.getCode());
	}
	
	public static AjaxResult error() {
		return error(BaseCommonMsg.HTTPSTATUS_INTERNAL_SERVER_ERROR.getCode(), BaseCommonMsg.HTTPSTATUS_INTERNAL_SERVER_ERROR.getMessage());
	}
	
	public static AjaxResult error(String msg) {
		return error(BaseCommonMsg.HTTPSTATUS_INTERNAL_SERVER_ERROR.getCode(), msg);
	}
	
	public static AjaxResult error(Integer code, String msg) {
		AjaxResult result = new AjaxResult();
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}
	
	public static AjaxResult error(Integer code, String msg,Object data) {
		AjaxResult result = new AjaxResult();
		result.put("code", code);
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}

	public static AjaxResult success(String msg) {
		AjaxResult result = new AjaxResult();
		result.put("msg", msg);
		return result;
	}
	
	public static AjaxResult success(Integer code,String msg) {
		AjaxResult result = new AjaxResult();
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}
	
	public static AjaxResult success(Integer code,String msg,Object data) {
		AjaxResult result = new AjaxResult();
		result.put("code", code);
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}
	
	public static AjaxResult success(Map<String, Object> map) {
		AjaxResult result = new AjaxResult();
		result.putAll(map);
		return result;
	}
	
	public static AjaxResult success() {
		return new AjaxResult();
	}
	
	/**
	 * 添加值
	 * @param key
	 * @param value
	 * @return
	 */
	public AjaxResult putParam(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
