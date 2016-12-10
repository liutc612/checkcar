package com.ltc.server;

import java.util.List;

import com.ltc.domain.Roles;

/**
 * 角色管理的业务层接口
 * @author ltc
 * 2016-5-27 11:11:04
 */
public interface OperateRoleManager {
	//此方法用于查询角色信息
	public List<Roles> findRoleService(String roleName);
	//此方法用于删除角色信息
	public void deleteRoleService(long roleID);
}
