package com.application.cloud.system.api.factory;

import com.application.cloud.common.core.domain.GenericResult;
import com.application.cloud.system.api.RemoteUserService;
import com.application.cloud.system.api.domain.SysUser;
import com.application.cloud.system.api.model.UserInfo;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 * 
 * @author cloud
 */
@Slf4j
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public GenericResult<UserInfo> getUserInfo(String username){
                return null;
            }
	        @Override
	        public GenericResult<Integer> findUserInfo(String infoKey){
		        return null;
	        }
	        @Override
	        public GenericResult<Integer> saveUser(SysUser sysUser){
		        return null;
	        }
        };
    }
}
