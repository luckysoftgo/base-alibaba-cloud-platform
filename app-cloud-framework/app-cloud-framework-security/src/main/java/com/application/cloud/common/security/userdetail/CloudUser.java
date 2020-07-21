package com.application.cloud.common.security.userdetail;

import com.application.cloud.system.api.domain.SysUser;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author : 孤狼
 * @NAME: CloudUser
 * @DESC: CloudUser类设计
 **/
public class CloudUser extends SysUser implements UserDetails, CredentialsContainer {

	private Collection<String> resources = new ArrayList<>();
	private Collection<String> rolesName = new ArrayList<>();
	private Collection<Integer> deptIds = new ArrayList<>();
	private Collection<GrantedAuthority> grantedAuthorities;
	private Long tenantId;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (grantedAuthorities == null) {
			this.grantedAuthorities = this.getRolesName().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		}
		return grantedAuthorities;
	}
	
	@Override
	public String getUsername() {
		return getUserName();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	
	public Collection<String> getRolesName() {
		return rolesName;
	}
	
	public void setRolesName(Collection<String> rolesName) {
		this.rolesName = rolesName;
	}
	
	public Collection<Integer> getDeptIds() {
		return deptIds;
	}
	
	public void setDeptIds(Collection<Integer> deptIds) {
		this.deptIds = deptIds;
	}
	
	public Collection<String> getResources() {
		return resources;
	}
	
	public void setResources(Collection<String> resources) {
		this.resources = resources;
	}
	
	@Override
	public void eraseCredentials() {
	
	}
}