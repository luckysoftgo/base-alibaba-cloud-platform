package com.application.cloud.auth.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UnsupportedResponseTypeException;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * 自定义异常返回
 *
 * @author cloud
 **/
@Component
@Slf4j
public class CustomOauthExceptionSerializer extends DefaultWebResponseExceptionTranslator {
	
	/**
	 * 消息提示.
	 */
    public static final String BAD_CREDENTIALS = "Bad credentials",BAD_MSG = "坏的凭证";
	
	/**
	 * @param e spring security内部异常
	 * @return 经过处理的异常信息
	 * @throws Exception 通用异常
	 */
	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
		log.error("Grant Error info:{}" ,e.getMessage());
		OAuth2Exception oAuth2Exception;
		if (e.getMessage() != null) {
			if(e.getMessage().equalsIgnoreCase(BAD_CREDENTIALS)|| e.getMessage().equalsIgnoreCase(BAD_MSG)){
				oAuth2Exception = new InvalidGrantException("用户名或密码错误", e);
			}else{
				oAuth2Exception = new InvalidGrantException("认证服务未知异常信息", e);
			}
			
		}else if (e instanceof InternalAuthenticationServiceException) {
			oAuth2Exception = new InvalidGrantException(e.getMessage(), e);
		} else{
			oAuth2Exception = new UnsupportedResponseTypeException("服务内部错误", e);
		}
		return super.translate(oAuth2Exception);
	}
}