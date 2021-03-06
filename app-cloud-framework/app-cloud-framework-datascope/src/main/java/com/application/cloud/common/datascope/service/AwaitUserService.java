package com.application.cloud.common.datascope.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.cloud.common.core.domain.GenericResult;
import com.application.cloud.common.core.utils.StringUtils;
import com.application.cloud.common.security.utils.SecurityUtils;
import com.application.cloud.system.api.RemoteUserService;
import com.application.cloud.system.api.model.UserInfo;

/**
 * 同步调用用户服务
 * 
 * @author cloud
 */
@Service
public class AwaitUserService
{
    private static final Logger log = LoggerFactory.getLogger(AwaitUserService.class);

    @Autowired
    private RemoteUserService remoteUserService;

    /**
     * 查询当前用户信息
     * 
     * @return 用户基本信息
     */
    public UserInfo getUserInfo()
    {
        String username = SecurityUtils.getUsername();
        GenericResult<UserInfo> userResult = remoteUserService.getUserInfo(username);
        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData()))
        {
            log.info("数据权限范围查询用户：{} 不存在.", username);
            return null;
        }
        return userResult.getData();
    }
}
