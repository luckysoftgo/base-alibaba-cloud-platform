package com.application.cloud.basic;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ：admin
 * @date ：2021-6-11
 * @description: 日志打印对象:
 * 可以通过该对象来打印日志，也可以将其存入数据库，做控制台的日志管理以及链上的半自动补偿措施
 * @modified By：
 * @version: 1.0.0
 */

public final class LogObject {
	/**
	 * 系统的ID
	 */
	@JsonProperty(index = 1)
	private String sysId;
	/**
	 * 系统的名称
	 */
	@JsonProperty(index = 2)
	private String sysName;
	/**
	 * 事件名称,一般就是业务方法名称
	 */
	@JsonProperty(index = 3)
	private String eventName;
	/**
	 * 调用链ID
	 */
	@JsonProperty(index = 4)
	private String traceId;
	/**
	 * 结果消息
	 */
	@JsonProperty(index = 5)
	private String message;
	/**
	 * 接口响应时间
	 */
	@JsonProperty(index = 6)
	private long costTime;
	/**
	 * C端用户id
	 */
	@JsonProperty(index = 7)
	private Integer userId;
	/**
	 * 其他业务参数
	 */
	@JsonProperty(index = 8)
	private Object others;
	/**
	 * 请求参数
	 */
	@JsonProperty(index = 9)
	private Object request;
	/**
	 * 相应结果
	 */
	@JsonProperty(index = 10)
	private Object response;
	
	public LogObject() {
	}
	
	public LogObject(String sysId, String sysName, String eventName, String traceId, String message, long costTime, Integer userId, Object others, Object request, Object response) {
		this.sysId = sysId;
		this.sysName = sysName;
		this.eventName = eventName;
		this.traceId = traceId;
		this.message = message;
		this.costTime = costTime;
		this.userId = userId;
		this.others = others;
		this.request = request;
		this.response = response;
	}
	
	public String getSysId() {
		return sysId;
	}
	
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	
	public String getSysName() {
		return sysName;
	}
	
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	
	public String getEventName() {
		return eventName;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getTraceId() {
		return traceId;
	}
	
	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public long getCostTime() {
		return costTime;
	}
	
	public void setCostTime(long costTime) {
		this.costTime = costTime;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public Object getOthers() {
		return others;
	}
	
	public void setOthers(Object others) {
		this.others = others;
	}
	
	public Object getRequest() {
		return request;
	}
	
	public void setRequest(Object request) {
		this.request = request;
	}
	
	public Object getResponse() {
		return response;
	}
	
	public void setResponse(Object response) {
		this.response = response;
	}
}
