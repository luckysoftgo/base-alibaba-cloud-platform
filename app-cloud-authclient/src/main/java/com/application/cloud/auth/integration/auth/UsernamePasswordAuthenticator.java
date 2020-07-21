package com.application.cloud.auth.integration.auth;

import com.application.cloud.auth.integration.AbstractPreparableIntegrationAuthenticator;
import com.application.cloud.auth.integration.dto.IntegrationAuthentication;
import com.application.cloud.auth.integration.type.ClientAuthType;
import com.application.cloud.common.core.constant.HttpStatus;
import com.application.cloud.common.core.domain.GenericResult;
import com.application.cloud.system.api.RemoteUserService;
import com.application.cloud.system.api.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户密码的认证方式.
 *
 * @author : 孤狼
 * @NAME: UsernamePasswordAuthenticator
 * @DESC: UsernamePasswordAuthenticator类设计
 **/
@Component
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {
	
	@Autowired
	private RemoteUserService sysUserClient;
	
	private final static String INSTANCE_AUTH_TYPE = ClientAuthType.INSTANCE_AUTH_TYPE.getAuthType();
	
	@Override
	public UserInfo authenticate(IntegrationAuthentication integrationAuthentication) {
		GenericResult<UserInfo> genericResult = sysUserClient.getUserInfo(integrationAuthentication.getAuthInstance());
		if (genericResult.getCode()== HttpStatus.SUCCESS){
			return genericResult.getData();
		}
		return null;
	}
	
	@Override
	public void prepare(IntegrationAuthentication integrationAuthentication) {
	
	}
	
	@Override
	public boolean support(IntegrationAuthentication integrationAuthentication) {
		return INSTANCE_AUTH_TYPE.equalsIgnoreCase(integrationAuthentication.getAuthType());
	}
}
