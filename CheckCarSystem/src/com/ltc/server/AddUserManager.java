package com.ltc.server;

import java.util.List;

import com.ltc.domain.Roles;
import com.ltc.domain.Users;

/**
 * 添加用户的业务层接口
 * @author ltc 2016-5-19 10:18:20
 *
 */
public interface AddUserManager {
	//此方法用于添加用户前的角色信息查询
	public List<Roles> findRoleAllServer();
	//此方法用于添加用户
	public void addUserServer(Users user);
}
