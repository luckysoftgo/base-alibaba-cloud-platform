package com.application.cloud.system.api.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.application.cloud.common.core.domain.R;
import com.application.cloud.system.api.RemoteUserService;
import com.application.cloud.system.api.model.UserInfo;
import feign.hystrix.FallbackFactory;

/**
 * 用户服务降级处理
 * 
 * @author cloud
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public R<UserInfo> getUserInfo(String username)
            {
                return null;
            }
        };
    }
}
