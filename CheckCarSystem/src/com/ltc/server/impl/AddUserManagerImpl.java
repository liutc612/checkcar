package com.ltc.server.impl;

import java.util.List;

import com.ltc.domain.Roles;
import com.ltc.domain.Users;
import com.ltc.persist.AddUserDao;
import com.ltc.persist.impl.AddUserDaoImpl;
import com.ltc.server.AddUserManager;
/**
 * 添加用户业务层的实现类
 * @author ltc 2016-5-19 10:22:05
 *
 */
public class AddUserManagerImpl implements AddUserManager {
	//此方法用于添加用户前的角色信息查询
	@Override
	public List<Roles> findRoleAllServer() {
		// 调用持久层查询角色信息
		AddUserDao aud = new AddUserDaoImpl();
		List<Roles> list = aud.findRoleAllPersist();
		return list;
	}
	//此方法用于实现添加用户
	@Override
	public void addUserServer(Users user) {
		// 调用持久层将用户信息录入到数据库
		AddUserDao aud = new AddUserDaoImpl();
		aud.addUserPersist(user);
	}

}
