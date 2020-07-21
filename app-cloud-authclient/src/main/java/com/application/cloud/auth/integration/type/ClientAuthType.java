package com.application.cloud.auth.integration.type;

/**
 * @author : 孤狼
 * @NAME: ClientAuthType
 * @DESC: AuthTypeConst类设计
 **/
public enum ClientAuthType {
	
	/**
	 * 用户名、手机号、邮箱、
	 */
	INSTANCE_AUTH_TYPE("instance","用户名、手机号、邮箱"),

	/**
	 * 短信验证
	 */
	SMS_AUTH_TYPE("sms","短信认证"),

	/**
	 * 验证码验证: 数字验证/图片验证
	 */
	VERCODE_AUTH_TYPE("code","验证码认证"),
	
	/**
	 * 内存认证
	 */
	MEMORY_AUTH_TYPE("memory","内存认证"),
	
	/**
	 * 验证码验证: 数字验证/图片验证
	 */
	SOCIAL_WECHAT_AUTH_TYPE("wechat","微信授权登录"),
	
	;
	
	private String authType;
	private String authDesc;
	
	ClientAuthType(String authType,String authDesc){
		this.authType = authType;
		this.authDesc = authDesc;
	}
	
	public String getAuthType() {
		return authType;
	}
	
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	
	public String getAuthDesc() {
		return authDesc;
	}
	
	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}
}
