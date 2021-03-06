package com.application.cloud.auth.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.application.cloud.common.core.constant.Constants;
import com.application.cloud.common.core.utils.StringUtils;
import com.application.cloud.common.security.domain.LoginUser;
import com.application.cloud.system.api.RemoteLogService;

/**
 * 认证成功日志处理.
 *
 * @author cloud
 */
@Component
public class AuthenticationSuccessEventHandler implements ApplicationListener<AuthenticationSuccessEvent> {
	
    @Autowired
    private RemoteLogService remoteLogService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event)
    {
        Authentication authentication = (Authentication) event.getSource();
        if (StringUtils.isNotEmpty(authentication.getAuthorities()) && authentication.getPrincipal() instanceof LoginUser){
            LoginUser user = (LoginUser) authentication.getPrincipal();
            String username = user.getUsername();
            // 记录用户登录日志
            remoteLogService.saveLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");
        }
    }
}
