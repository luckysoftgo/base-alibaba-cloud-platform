package com.application.cloud.auth.integration.api;

import com.application.cloud.auth.integration.dto.IntegrationAuthentication;
import com.application.cloud.system.api.model.UserInfo;

/**
 * 集成认证的就接口定义.
 *
 * @author : 孤狼
 * @NAME: IntegrationAuthenticator
 * @DESC: IntegrationAuthenticator类设计
 **/
public interface IntegrationAuthenticator {
	
	/**
	 * 处理集成认证,返回主体信息
	 * @param integrationAuthentication
	 * @return
	 */
	UserInfo authenticate(IntegrationAuthentication integrationAuthentication);
	
	/**
	 * 进行预处理
	 * @param integrationAuthentication
	 */
	void prepare(IntegrationAuthentication integrationAuthentication);
	
	/**
	 * 判断是否支持集成认证类型
	 * @param integrationAuthentication
	 * @return
	 */
	boolean support(IntegrationAuthentication integrationAuthentication);
	
	/** 认证结束后执行
	 * @param integrationAuthentication
	 */
	void complete(IntegrationAuthentication integrationAuthentication);
	
}
