package com.application.cloud.common.security.handler;

import com.alibaba.fastjson.JSON;
import com.application.cloud.common.core.constant.HttpStatus;
import com.application.cloud.common.core.domain.GenericResult;
import com.application.cloud.common.core.utils.ServletUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义访问无权限资源时的异常
 * 
 * @author cloud
 */
@Slf4j
@Component
public class CustomAccessDeniedHandler extends OAuth2AccessDeniedHandler
{

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException authException)
    {
        log.info("权限不足，请联系管理员 {}", request.getRequestURI());
        String msg = authException.getMessage();
        ServletUtils.renderString(response, JSON.toJSONString(GenericResult.fail(HttpStatus.FORBIDDEN, msg)));
    }
}
