package com.application.cloud.auth.integration.auth.sms;

import com.application.cloud.auth.integration.AbstractPreparableIntegrationAuthenticator;
import com.application.cloud.auth.integration.auth.sms.event.SmsAuthenticateBeforeEvent;
import com.application.cloud.auth.integration.auth.sms.event.SmsAuthenticateSuccessEvent;
import com.application.cloud.auth.integration.consts.AuthClientConst;
import com.application.cloud.auth.integration.dto.IntegrationAuthentication;
import com.application.cloud.auth.integration.type.ClientAuthType;
import com.application.cloud.common.core.constant.CacheConstants;
import com.application.cloud.common.core.constant.HttpStatus;
import com.application.cloud.common.core.domain.GenericResult;
import com.application.cloud.common.core.utils.StringUtils;
import com.application.cloud.common.redis.service.RedisService;
import com.application.cloud.system.api.RemoteUserService;
import com.application.cloud.system.api.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

/**
 * 短信验证码集成认证
 * @author : 孤狼
 * @NAME: SmsAuthenticateSuccessEvent
 * @DESC: SmsAuthenticateSuccessEvent 类设计
 **/
@Component
public class SmsIntegrationAuthenticator extends AbstractPreparableIntegrationAuthenticator implements ApplicationEventPublisherAware {
	
	@Autowired
	private RedisService redisService;
    @Autowired
    private RemoteUserService sysUserClient;
	
    private ApplicationEventPublisher applicationEventPublisher;

    private final static String SMS_AUTH_TYPE = ClientAuthType.SMS_AUTH_TYPE.getAuthType();

    @Override
    public UserInfo authenticate(IntegrationAuthentication integrationAuthentication) {
	    //获取密码，实际值是验证码
	    // TODO 到redis中去验证是否是短信验证码.
	    String password = integrationAuthentication.getAuthParameter(AuthClientConst.AUTH_PASSWORD);
	    //获取用户名的手机号
	    String mobilePhone = integrationAuthentication.getAuthInstance();
	    if (StringUtils.isEmpty(mobilePhone)) {
		    mobilePhone = integrationAuthentication.getAuthParameter(AuthClientConst.AUTH_USERNAME);
	    }
	    //发布事件，可以监听事件进行自动注册用户,RemoteUserService 有方法，但是现在不用尝试.
	    this.applicationEventPublisher.publishEvent(new SmsAuthenticateBeforeEvent(integrationAuthentication));
	    //通过手机号码查询用户
	    GenericResult<UserInfo> genericResult = this.sysUserClient.getUserInfo(mobilePhone);
        if (genericResult!=null && genericResult.getCode()== HttpStatus.SUCCESS){
	        UserInfo userInfo = genericResult.getData();
	        //发布事件，可以监听事件进行消息通知
	        this.applicationEventPublisher.publishEvent(new SmsAuthenticateSuccessEvent(integrationAuthentication));
	        return userInfo;
        }
		return null;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {
	    String mobilePhone = integrationAuthentication.getAuthInstance();
	    if (StringUtils.isEmpty(mobilePhone)){
	    	mobilePhone = integrationAuthentication.getAuthParameter(AuthClientConst.AUTH_USERNAME);
	    }
	    if (StringUtils.isEmpty(mobilePhone)){
		    throw new OAuth2Exception("输入的手机号信息为空");
	    }
	    String smsCode = integrationAuthentication.getAuthParameter(AuthClientConst.AUTH_PASSWORD);
	    if (StringUtils.isEmpty(smsCode)){
		    throw new OAuth2Exception("输入的验证码信息为空");
	    }
	    //验证验证码
	    //测试用的，测试放开
	    //redisService.setCacheObject(CacheConstants.CLIENT_AUTH_INFO+mobilePhone,"123456789");
	    String cacheCode = redisService.getCacheObject(CacheConstants.CLIENT_AUTH_INFO+mobilePhone);
	    if (!cacheCode.trim().equalsIgnoreCase(smsCode.trim())){
		    throw new OAuth2Exception("短信验证码错误,登录失败");
	    }
    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return SMS_AUTH_TYPE.equalsIgnoreCase(integrationAuthentication.getAuthType());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
    
}
