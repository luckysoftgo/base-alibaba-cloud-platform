package com.application.cloud.auth.integration;

import com.application.cloud.auth.integration.api.IntegrationAuthenticator;
import com.application.cloud.auth.integration.dto.IntegrationAuthentication;
import com.application.cloud.system.api.model.UserInfo;

/**
 * 默认不做处理
 * @author : 孤狼
 * @NAME: AbstractPreparableIntegrationAuthenticator
 * @DESC: AbstractPreparableIntegrationAuthenticator类设计
 **/
public abstract class AbstractPreparableIntegrationAuthenticator implements IntegrationAuthenticator {
	
	@Override
	public abstract UserInfo authenticate(IntegrationAuthentication integrationAuthentication);
	
	@Override
	public abstract void prepare(IntegrationAuthentication integrationAuthentication);
	
	@Override
	public abstract boolean support(IntegrationAuthentication integrationAuthentication);
	
	@Override
	public void complete(IntegrationAuthentication integrationAuthentication) {
	}
}
