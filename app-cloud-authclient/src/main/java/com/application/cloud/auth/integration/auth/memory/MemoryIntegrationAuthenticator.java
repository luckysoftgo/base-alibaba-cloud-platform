package com.application.cloud.auth.integration.auth.memory;

import com.application.cloud.auth.integration.api.IntegrationAuthenticator;
import com.application.cloud.auth.integration.consts.AuthClientConst;
import com.application.cloud.auth.integration.dto.IntegrationAuthentication;
import com.application.cloud.auth.integration.type.ClientAuthType;
import com.application.cloud.common.core.utils.StringUtils;
import com.application.cloud.system.api.domain.SysUser;
import com.application.cloud.system.api.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

/**
 * @author : 孤狼
 * @NAME: MemoryIntegrationAuthenticator
 * @DESC: MemoryIntegrationAuthticator类设计
 **/
@Component
public class MemoryIntegrationAuthenticator implements IntegrationAuthenticator {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final static String MEMORY_AUTH_TYPE = ClientAuthType.MEMORY_AUTH_TYPE.getAuthType();
	
	@Override
	public UserInfo authenticate(IntegrationAuthentication integrationAuthentication) {
		UserInfo userInfo = new UserInfo();
		String instance = integrationAuthentication.getAuthInstance();
		if (StringUtils.isEmpty(instance)){
			instance = integrationAuthentication.getAuthParameter(AuthClientConst.AUTH_USERNAME);
		}
		if (StringUtils.isEmpty(instance)){
			throw new OAuth2Exception("内存认证信息为空");
		}
		String password = integrationAuthentication.getAuthParameter(AuthClientConst.AUTH_PASSWORD);
		if (StringUtils.isEmpty(password)){
			throw new OAuth2Exception("内存认证信息为空");
		}
		SysUser sysUser = new SysUser();
		sysUser.setUserName(instance);
		//加入密码校验
		sysUser.setPassword(passwordEncoder.encode(password));
		userInfo.setSysUser(sysUser);
		return userInfo;
	}
	
	@Override
	public void prepare(IntegrationAuthentication integrationAuthentication) {
	
	}
	
	@Override
	public void complete(IntegrationAuthentication integrationAuthentication) {
	
	}
	
	@Override
	public boolean support(IntegrationAuthentication integrationAuthentication) {
		return MEMORY_AUTH_TYPE.equalsIgnoreCase(integrationAuthentication.getAuthType());
	}
}
