package com.application.cloud.basic.common.result;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author : 孤狼
 * @NAME: AjaxResult
 * @DESC: AjaxResult类设计
 **/
public class AjaxResult <T> extends HashMap<String, Object> implements Serializable {
	
	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = 1L;
	
	public AjaxResult() {
		putValue("code",BaseCommonMsg.SYSTEM_SUCCESS_MSG.getCode());
	}
	
	public AjaxResult(String message) {
		putValue("code",BaseCommonMsg.SYSTEM_SUCCESS_MSG.getCode());
		putValue("msg",message);
	}
	
	public AjaxResult(T data) {
		putValue("code",BaseCommonMsg.SYSTEM_SUCCESS_MSG.getCode());
		putValue("msg",BaseCommonMsg.SYSTEM_SUCCESS_MSG.getMessage());
		putValue("data",data);
	}
	
	public AjaxResult(Integer code, String message) {
		putValue("code",code);
		putValue("msg",message);
	}
	
	public AjaxResult(Integer code, T data) {
		putValue("code",code);
		putValue("data",data);
	}
	
	public AjaxResult(Integer code, String message,T data) {
		putValue("code",code);
		putValue("msg",message);
		putValue("data",data);
	}
	
	public static AjaxResult error() {
		return error(BaseCommonMsg.SYSTEM_ERROR_MSG.getCode(), BaseCommonMsg.SYSTEM_ERROR_MSG.getMessage());
	}
	
	public static AjaxResult error(String msg) {
		return error(BaseCommonMsg.SYSTEM_ERROR_MSG.getCode(), msg);
	}

	public static AjaxResult error(Integer code, String message) {
		return new AjaxResult(code,message);
	}
	
	public static AjaxResult error(Integer code, String message,Object data) {
		return new AjaxResult(code,message,data);
	}
	
	public static AjaxResult success() {
		return new AjaxResult();
	}
	
	public static AjaxResult success(Object data) {
		return new AjaxResult(data);
	}
	
	public static AjaxResult success(String msg) {
		return new AjaxResult(msg);
	}
	
	public static AjaxResult success(Integer code,String msg) {
		return new AjaxResult(code,msg);
	}
	
	public static AjaxResult success(Integer code,Object data) {
		return new AjaxResult(code,data);
	}
	
	public static AjaxResult success(Integer code,String msg, Object data) {
		return new AjaxResult(code,msg,data);
	}
	
	/**
	 * 设置结果的值.
	 * @param key
	 * @param value
	 * @return
	 */
	public AjaxResult putValue(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
