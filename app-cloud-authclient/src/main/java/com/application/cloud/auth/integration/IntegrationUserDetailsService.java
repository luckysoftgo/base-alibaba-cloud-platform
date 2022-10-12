package com.application.cloud.auth.integration;

import com.application.cloud.auth.integration.api.IntegrationAuthenticator;
import com.application.cloud.auth.integration.dto.IntegrationAuthentication;
import com.application.cloud.auth.integration.type.ClientAuthType;
import com.application.cloud.common.core.enums.UserStatus;
import com.application.cloud.common.core.exception.BaseException;
import com.application.cloud.common.core.utils.StringUtils;
import com.application.cloud.common.security.domain.LoginUser;
import com.application.cloud.system.api.domain.SysUser;
import com.application.cloud.system.api.model.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 集成认证用户服务
 * @author : 孤狼
 * @NAME: IntegrationUserDetailsService
 * @DESC: IntegrationUserDetailsService类设计
 **/
@Slf4j
@Service
public class IntegrationUserDetailsService implements UserDetailsService {
	
	/**
	 * 认证对象的集合.
	 */
	private List<IntegrationAuthenticator> authenticators;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired(required = false)
	public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
		this.authenticators = authenticators;
	}
	
	@Override
	public UserDetails loadUserByUsername(String authInstance) throws UsernameNotFoundException {
		log.info("认证的对象是:{}",authInstance);
		IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();
		//判断是否是集成登录
		if (integrationAuthentication == null) {
			log.info("不是支持的登录方式");
			integrationAuthentication = new IntegrationAuthentication();
		}
		//得到认证的结果对象.
		integrationAuthentication.setAuthInstance(authInstance);
		UserInfo userInfo = this.authenticate(integrationAuthentication);
		if(userInfo == null){
			throw new UsernameNotFoundException("获得认证信息出错了");
		}
		checkUser(userInfo,authInstance);
		return getUserDetails(userInfo,integrationAuthentication);
	}
	
	/**
	 * 集成认证,得到认证之后的结果信息.
	 * @param integrationAuthentication
	 * @return
	 */
	private UserInfo authenticate(IntegrationAuthentication integrationAuthentication) {
		if (this.authenticators != null) {
			for (IntegrationAuthenticator authenticator : authenticators) {
				if (authenticator.support(integrationAuthentication)) {
					return authenticator.authenticate(integrationAuthentication);
				}
			}
		}
		log.error("不支持的认证方式，认证类型是:{},认证实例是:{}",integrationAuthentication.getAuthType(),integrationAuthentication.getAuthInstance());
		return null;
	}
	
	/**
	 * 获得认证的对象信息.
	 * 给 UserDetails赋值操作.
	 *
	 * @param info
	 * @return
	 */
	private UserDetails getUserDetails(UserInfo info,IntegrationAuthentication integrationAuthentication){
		Set<String> dbAuthsSet = new HashSet<String>();
		if (StringUtils.isNotEmpty(info.getRoles())){
			// 获取角色
			dbAuthsSet.addAll(info.getRoles());
			// 获取权限
			dbAuthsSet.addAll(info.getPermissions());
		}
		Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(dbAuthsSet.toArray(new String[0]));
		SysUser user = info.getSysUser();
		if (ClientAuthType.SMS_AUTH_TYPE.getAuthType().equalsIgnoreCase(integrationAuthentication.getAuthType())||
				ClientAuthType.VERCODE_AUTH_TYPE.getAuthType().equalsIgnoreCase(integrationAuthentication.getAuthType())||
				ClientAuthType.SOCIAL_WECHAT_AUTH_TYPE.getAuthType().equalsIgnoreCase(integrationAuthentication.getAuthType())){
			user.setPassword(passwordEncoder.encode(integrationAuthentication.getAuthParam()));
		}
		LoginUser loginUser = new LoginUser(user.getUserId(), user.getUserName(), user.getPassword(), true, true, true, true,authorities);
		loginUser.setDeptId(user.getDeptId());
		loginUser.setUserName(user.getUserName());
		loginUser.setNickName(user.getNickName());
		loginUser.setEmail(user.getEmail());
		loginUser.setPhonenumber(user.getPhonenumber());
		loginUser.setSex(user.getSex());
		loginUser.setStatus(user.getStatus());
		loginUser.setDelFlag(user.getDelFlag());
		loginUser.setRoleIds(user.getRoleIds());
		loginUser.setRoles(info.getRoles());
		loginUser.setRoleInfos(user.getRoles());
		loginUser.setPostIds(user.getPostIds());
		loginUser.setDepts(info.getDepts());
		loginUser.setPermissions(info.getPermissions());
		//带租户的方式,在 SysUser 表中加入 tenantId 字段.
		return loginUser;
	}
	
	/**
	 * 检查用户
	 * @param userInfo
	 * @param username
	 */
	public void checkUser(UserInfo userInfo, String username){
		if (StringUtils.isNull(userInfo)){
			log.info("登录用户：{} 不存在.", username);
			throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
		}
		else if (UserStatus.DELETED.getCode().equals(userInfo.getSysUser().getDelFlag())){
			log.info("登录用户：{} 已被删除.", username);
			throw new BaseException("对不起，您的账号：" + username + " 已被删除");
		}
		else if (UserStatus.DISABLE.getCode().equals(userInfo.getSysUser().getStatus())){
			log.info("登录用户：{} 已被停用.", username);
			throw new BaseException("对不起，您的账号：" + username + " 已停用");
		}
	}
}
