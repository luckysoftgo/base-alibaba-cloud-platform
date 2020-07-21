package com.application.cloud.auth.integration.type;

/**
 * oauth2 定义了下面四种授权方式
 * @author : 孤狼
 * @NAME: ClientGrantType
 * @DESC: ClientGrantType类设计
 **/
public enum  ClientGrantType {
	
	/**
	 * 授权码模式、
	 */
	AUTHORIZATION_CODE_GRANT_TYPE("authorization_code","授权码模式"),
	
	/**
	 * 客户端模式
	 */
	CLIENT_CREDENTIALS_GRANT_TYPE("client_credentials","客户端模式"),
	
	/**
	 * 密码模式
	 */
	PASSWORD_GRANT_TYPE("password","密码模式"),
	
	/**
	 * 简化模式
	 */
	IMPLICIT_GRANT_TYPE("implicit","简化模式"),
	
	;
	
	private String grantType;
	private String grantDesc;
	
	ClientGrantType(String grantType,String grantDesc){
		this.grantType = grantType;
		this.grantDesc = grantDesc;
	}
	
	public String getGrantType() {
		return grantType;
	}
	
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	
	public String getGrantDesc() {
		return grantDesc;
	}
	
	public void setGrantDesc(String grantDesc) {
		this.grantDesc = grantDesc;
	}
}
