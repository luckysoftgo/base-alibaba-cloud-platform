package com.application.cloud.system.api.factory;

import com.application.cloud.common.core.domain.GenericResult;
import com.application.cloud.system.api.RemoteLogService;
import com.application.cloud.system.api.domain.SysOperLog;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 日志服务降级处理
 * 
 * @author cloud
 */
@Slf4j
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService>
{
    @Override
    public RemoteLogService create(Throwable throwable)
    {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteLogService()
        {
            @Override
            public GenericResult<Boolean> saveLog(SysOperLog sysOperLog)
            {
                return null;
            }
            @Override
            public GenericResult<Boolean> saveLogininfor(String username, String status, String message)
            {
                return null;
            }
        };

    }
}
