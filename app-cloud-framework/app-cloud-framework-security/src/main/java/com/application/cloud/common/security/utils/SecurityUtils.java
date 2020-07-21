package com.application.cloud.common.security.utils;

import com.application.cloud.common.core.utils.StringUtils;
import com.application.cloud.common.security.domain.LoginUser;
import com.application.cloud.common.security.userdetail.CloudUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 权限获取工具类
 * 
 * @author cloud
 */
public class SecurityUtils{
	
    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     */
    public static String getUsername(){
        return getLoginUser().getUsername();
    }

    /**
     * 获取用户
     */
    public static LoginUser getLoginUser(Authentication authentication){
        Object principal = authentication.getPrincipal();
        if (principal instanceof LoginUser)
        {
            return (LoginUser) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public static LoginUser getLoginUser(){
        Authentication authentication = getAuthentication();
        if (authentication == null)
        {
            return null;
        }
        return getLoginUser(authentication);
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (StringUtils.isEmpty(password)){
        	password="123456";
        }
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId){
        return userId != null && 1L == userId;
    }
	
	
	public static Long getTenantId() {
		CloudUser user = getPrincipal();
		if (user != null) {
			return user.getTenantId();
		}
		return null;
	}
	
	public static String getLoginName() {
		CloudUser user = getPrincipal();
		if (user != null) {
			return user.getUsername();
		}
		return null;
	}
	
	public static CloudUser getPrincipal() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal != null) {
			if (principal instanceof CloudUser) {
				return (CloudUser) principal;
			} else {
				throw new IllegalStateException("获取用户数据失败");
			}
		}
		return null;
	}
	
	public static boolean hasRole(String role) {
		CloudUser user = getPrincipal();
		if (user != null) {
			if (user.getRoles().contains(role)) {
				return true;
			}
			
		}
		return false;
	}
	
	public static Long getUserId() {
		CloudUser user = getPrincipal();
		if (user != null) {
			return user.getUserId();
		}
		return null;
	}
	
	public static boolean hasAuthority(String authority) {
		CloudUser user = getPrincipal();
		if (user != null) {
			if (user.getAuthorities().contains(authority)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean hasResource(String resource) {
		CloudUser user = getPrincipal();
		if (user != null) {
			if (user.getResources().contains(resource)) {
				return true;
			}
		}
		return false;
	}
}
