package com.application.cloud.common.security.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 登录用户身份权限
 * 
 * @author cloud
 */
public class LoginUser extends User {
	
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
	/**
	 * 用户ID
	 */
	private Long tenantId;
	
	
	public LoginUser(Long userId,String username, String password, boolean enabled, boolean accountNonExpired,
	                 boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
	{
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userId = userId;
	}
	
    public LoginUser(Long userId,Long tenantId,String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
    {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.tenantId = tenantId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
	
	public Long getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
}
