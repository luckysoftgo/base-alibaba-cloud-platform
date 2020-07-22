package com.application.cloud.system.service.impl;

import com.application.cloud.system.api.domain.SysUser;
import com.application.cloud.system.service.ISysDeptService;
import com.application.cloud.system.service.ISysMenuService;
import com.application.cloud.system.service.ISysPermissionService;
import com.application.cloud.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysPermissionServiceImpl implements ISysPermissionService
{
    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysMenuService menuService;
	
	@Autowired
	private ISysDeptService deptService;
	
    /**
     * 获取角色数据权限
     * 
     * @param userId 用户Id
     * @return 角色权限信息
     */
    @Override
    public Set<String> getRolePermission(Long userId)
    {
        Set<String> roles = new HashSet<String>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(userId))
        {
            roles.add("admin");
        }
        else
        {
            roles.addAll(roleService.selectRolePermissionByUserId(userId));
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     * 
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    @Override
    public Set<String> getMenuPermission(Long userId)
    {
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (SysUser.isAdmin(userId))
        {
            perms.add("*:*:*");
        }
        else
        {
            perms.addAll(menuService.selectMenuPermsByUserId(userId));
        }
        return perms;
    }
	
	@Override
	public List<Long> getDeptPermission(Long userId,Long[] roleIds) {
		List<Long> perms = new ArrayList<>();
		if (userId==null || roleIds==null){
			return perms;
		}
		// 管理员拥有所有权限
		if (SysUser.isAdmin(userId)){
			perms.add(0L);
		}else{
			for (Long roleId : roleIds ) {
				perms.addAll(deptService.selectDeptListByRoleId(roleId));
			}
		}
		return perms;
	}
}
