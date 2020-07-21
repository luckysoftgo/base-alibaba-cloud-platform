package com.application.cloud.system.api;

import com.application.cloud.common.core.constant.ServiceNameConstants;
import com.application.cloud.common.core.domain.GenericResult;
import com.application.cloud.system.api.domain.SysUser;
import com.application.cloud.system.api.factory.RemoteUserFallbackFactory;
import com.application.cloud.system.api.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 用户服务
 * 
 * @author cloud
 */
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.SYSTEM_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名/手机号/邮箱
     * @return 结果
     */
    @GetMapping(value = "/user/info/{username}")
    public GenericResult<UserInfo> getUserInfo(@PathVariable("username") String username);
	
	/**
	 * 查找用户是否存在.
	 * @param infoKey 用户名/手机号/邮箱
	 * @return 结果
	 */
	@GetMapping(value = "/user/judge/{infoKey}")
	public GenericResult<Integer> findUserInfo(@PathVariable("infoKey") String infoKey);
	
	/**
	 * 添加用户
	 *
	 * @param sysUser 用户信息
	 * @return 结果
	 */
	@PostMapping(value = "/user/simpleSave",consumes = "application/json")
	public GenericResult<Integer> saveUser(SysUser sysUser);
	
}
