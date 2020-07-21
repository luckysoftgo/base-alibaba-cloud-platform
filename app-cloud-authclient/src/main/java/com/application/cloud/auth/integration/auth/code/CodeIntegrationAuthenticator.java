package com.application.cloud.auth.integration.auth.code;

import com.application.cloud.auth.integration.auth.UsernamePasswordAuthenticator;
import com.application.cloud.auth.integration.consts.AuthClientConst;
import com.application.cloud.auth.integration.dto.IntegrationAuthentication;
import com.application.cloud.auth.integration.type.ClientAuthType;
import com.application.cloud.common.core.constant.CacheConstants;
import com.application.cloud.common.core.utils.StringUtils;
import com.application.cloud.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

/**
 * @author : 孤狼
 * @NAME: CodeIntegrationAuthenticator
 * @DESC: VerificationCodeIntegrationAuthenticator类设计
 **/
@Component
public class CodeIntegrationAuthenticator extends UsernamePasswordAuthenticator {
	
	@Autowired
	private RedisService redisService;
	
	/**
	 * 认证类型
	 */
	private final static String VERIFICATION_CODE_AUTH_TYPE = ClientAuthType.VERCODE_AUTH_TYPE.getAuthType();
	
	/**
	 * redis 或者数据库中做验证码的校验.
	 * @param integrationAuthentication
	 */
	@Override
	public void prepare(IntegrationAuthentication integrationAuthentication) {
		//可以是登录的用户名、手机号、邮箱等
		String vcInstance = integrationAuthentication.getAuthInstance();
		if (StringUtils.isEmpty(vcInstance)){
			vcInstance = integrationAuthentication.getAuthParameter(AuthClientConst.AUTH_USERNAME);
		}
		if (StringUtils.isEmpty(vcInstance)){
			throw new OAuth2Exception("验证码存储对象信息为空");
		}
		String vcCode = integrationAuthentication.getAuthParameter(AuthClientConst.AUTH_PASSWORD);
		if (StringUtils.isEmpty(vcCode)){
			throw new OAuth2Exception("输入的验证码信息为空");
		}
		//验证验证码
		//测试用的，测试放开
		//redisService.setCacheObject(CacheConstants.CLIENT_AUTH_INFO+vcInstance,"123456789");
		String cacheCode = redisService.getCacheObject(CacheConstants.CLIENT_AUTH_INFO+vcInstance);
		if (!cacheCode.trim().equalsIgnoreCase(vcCode.trim())){
			throw new OAuth2Exception("验证码错误,登录失败");
		}
	}
	
	@Override
	public boolean support(IntegrationAuthentication integrationAuthentication) {
		return VERIFICATION_CODE_AUTH_TYPE.equalsIgnoreCase(integrationAuthentication.getAuthType());
	}
	
}
