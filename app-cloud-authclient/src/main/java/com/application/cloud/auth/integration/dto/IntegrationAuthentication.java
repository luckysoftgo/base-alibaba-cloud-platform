package com.application.cloud.auth.integration.dto;

import lombok.Data;

import java.util.Map;

/**
 * 认证对象信息.
 *
 * @author : 孤狼
 * @NAME: IntegrationAuthentication
 * @DESC: IntegrationAuthentication类设计
 **/
@Data
public class IntegrationAuthentication {
	
	/**
	 * 认证类型:为枚举 --> ClientAuthType
	 */
	private String authType;
	
	/**
	 * 认证的实例(用户名，手机号，邮箱，身份证号等)
	 */
	private String authInstance;
	
	/**
	 * 认证的信息(密码，短信验证码，图片验证码，微信等).
	 */
	private String authParam;
	
	/**
	 * 认证的参数.
	 */
	private Map<String,String[]> authParameters;
	
	/**
	 * 获取认证的参数.
	 * @param paramter
	 * @return
	 */
	public String getAuthParameter(String paramter){
		String[] values = this.authParameters.get(paramter);
		if(values != null && values.length > 0){
			return values[0].trim();
		}
		return null;
	}
}
