package com.application.cloud.auth.integration.auth.wechat;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.application.cloud.auth.integration.api.IntegrationAuthenticator;
import com.application.cloud.auth.integration.consts.AuthClientConst;
import com.application.cloud.auth.integration.dto.IntegrationAuthentication;
import com.application.cloud.auth.integration.type.ClientAuthType;
import com.application.cloud.common.core.constant.HttpStatus;
import com.application.cloud.common.core.domain.GenericResult;
import com.application.cloud.system.api.RemoteUserService;
import com.application.cloud.system.api.model.UserInfo;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

/**
 * 微信小程序集成认证
 * @author : 孤狼
 * @NAME: WechatIntegrationAuthenticator
 * @DESC: WechatIntegrationAuthenticator类设计
 **/
@Component
public class WechatIntegrationAuthenticator implements IntegrationAuthenticator {
	
	@Autowired
	private RemoteUserService sysUserClient;
	
	@Autowired(required = false)
	private WxMaService wxMaService;
	
	private final static String SOCIAL_WECHAT_AUTH_TYPE = ClientAuthType.SOCIAL_WECHAT_AUTH_TYPE.getAuthType();
	
	@Override
	public UserInfo authenticate(IntegrationAuthentication integrationAuthentication) {
		WxMaJscode2SessionResult session = null;
		String password = integrationAuthentication.getAuthParameter(AuthClientConst.AUTH_PASSWORD);
		try {
			session = this.wxMaService.getUserService().getSessionInfo(password);
			if (session==null){
				throw new  OAuth2Exception("微信认证失败");
			}
			WechatAppToken wechatToken = new WechatAppToken(session.getOpenid(), session.getUnionid(), session.getSessionKey());
			// 加密算法的初始向量
			wechatToken.setIv(integrationAuthentication.getAuthParameter("iv"));
			// 用户的加密数据
			wechatToken.setEncryptedData(integrationAuthentication.getAuthParameter("encryptedData"));
		} catch (WxErrorException e) {
			throw new InternalAuthenticationServiceException("获取微信小程序用户信息失败",e);
		}
		String openId = session.getOpenid();
		GenericResult<UserInfo> genericResult = sysUserClient.getUserInfo(openId);
		if (genericResult!=null && genericResult.getCode()== HttpStatus.SUCCESS){
			UserInfo userInfo = genericResult.getData();
			return userInfo;
		}
		return null;
	}
	
	@Override
	public void prepare(IntegrationAuthentication integrationAuthentication) {
	
	}
	
	@Override
	public boolean support(IntegrationAuthentication integrationAuthentication) {
		return SOCIAL_WECHAT_AUTH_TYPE.equalsIgnoreCase(integrationAuthentication.getAuthType());
	}
	
	@Override
	public void complete(IntegrationAuthentication integrationAuthentication) {
	
	}
}