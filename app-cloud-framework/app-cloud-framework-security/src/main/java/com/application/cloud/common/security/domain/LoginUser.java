package com.application.cloud.common.security.domain;

import com.application.cloud.system.api.domain.SysRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 登录用户身份权限
 * 
 * @author cloud
 */
public class LoginUser extends User {
	
    private static final long serialVersionUID = 1L;

	/**用户ID*/
    private Long userId;
	/**租户ID*/
	private Long tenantId;
	/** 部门ID */
	private Long deptId;
	/** 用户账号 */
	private String userName;
	/** 用户昵称 */
	private String nickName;
	/** 用户邮箱 */
	private String email;
	/** 手机号码 */
	private String phonenumber;
	/** 用户性别0=男,1=女,2=未知 */
	private String sex;
	/** 帐号状态（0正常 1停用） */
	private String status;
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;
	
	/** 权限标识集合*/
	private Set<String> permissions;
	
	/** 角色组 */
	private Long[] roleIds;
	/**角色名称集合*/
	private Set<String> roles;
	/** 角色对象 */
	private List<SysRole> roleInfos;
	
	/**部门集合*/
	private List<Long> depts;
	/** 岗位组 */
	private Long[] postIds;
	
	/**
	 * 构造器
	 * @param userId
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public LoginUser(Long userId,String username, String password, boolean enabled, boolean accountNonExpired,
	                 boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
	{
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userId = userId;
	}
	
	/**
	 * 构造器
	 * @param userId
	 * @param tenantId
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
    public LoginUser(Long userId,Long tenantId,String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities)
    {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.tenantId = tenantId;
    }
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}
	
	public Long getDeptId() {
		return deptId;
	}
	
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getNickName() {
		return nickName;
	}
	
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhonenumber() {
		return phonenumber;
	}
	
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDelFlag() {
		return delFlag;
	}
	
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	public Set<String> getPermissions() {
		return permissions;
	}
	
	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}
	
	public Set<String> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
	
	public List<Long> getDepts() {
		return depts;
	}
	
	public void setDepts(List<Long> depts) {
		this.depts = depts;
	}
	
	public List<SysRole> getRoleInfos() {
		return roleInfos;
	}
	
	public void setRoleInfos(List<SysRole> roleInfos) {
		this.roleInfos = roleInfos;
	}
	
	public Long[] getRoleIds() {
		return roleIds;
	}
	
	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	
	public Long[] getPostIds() {
		return postIds;
	}
	
	public void setPostIds(Long[] postIds) {
		this.postIds = postIds;
	}
}
