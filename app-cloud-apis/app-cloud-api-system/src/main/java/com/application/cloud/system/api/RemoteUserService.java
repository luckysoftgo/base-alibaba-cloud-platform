package com.application.cloud.system.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.application.cloud.common.core.constant.ServiceNameConstants;
import com.application.cloud.common.core.domain.R;
import com.application.cloud.system.api.factory.RemoteUserFallbackFactory;
import com.application.cloud.system.api.model.UserInfo;

/**
 * 用户服务
 * 
 * @author cloud
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService
{
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    public R<UserInfo> getUserInfo(@PathVariable("username") String username);
}