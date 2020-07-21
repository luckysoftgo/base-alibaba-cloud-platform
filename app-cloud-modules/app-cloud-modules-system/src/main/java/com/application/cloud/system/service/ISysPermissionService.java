package com.application.cloud.system.service;

import java.util.List;
import java.util.Set;

public interface ISysPermissionService
{
    /**
     * 获取角色数据权限
     * 
     * @param userId 用户Id
     * @return 角色权限信息
     */
    public Set<String> getRolePermission(Long userId);

    /**
     * 获取菜单数据权限
     * 
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(Long userId);
	
	/**
	 * 获取角色部门权限
	 *
	 * @param userId 用户Id
	 * @param roleId 角色Id
	 * @return 角色部门信息
	 */
	public List<Integer> getDeptPermission(Long userId,Long[] roleId);
	
}
