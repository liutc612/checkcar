package com.ltc.server.impl;

import com.ltc.domain.Users;
import com.ltc.persist.OperateUserDao;
import com.ltc.persist.impl.OperateUserDaoImpl;
import com.ltc.server.OperateUserManager;
/**
 * 用户操作的业务层接口实现类
 * @author ltc
 * 2016-5-24 09:46:35
 */
public class OperateUserManagerImpl implements OperateUserManager {

	@Override
	//此方法用于删除用户信息
	public void delUserService(String userName) {
		// 调用持久层进行删除数据库中的数据
		OperateUserDao oud = new OperateUserDaoImpl();
		oud.delUserPersist(userName);
	}

	@Override
	//此方法用于预更新用户查询动作
	public Users preUpdateUserService(String userName) {
		// 调用持久层进行删除数据库中的数据
		OperateUserDao oud = new OperateUserDaoImpl();
		Users user = oud.preUpdateUserPersist(userName);
		return user;
	}

	@Override
	//此方法用于更新用户
	public void updateUserService(Users user) {
		// 调用持久层进行删除数据库中的数据
		OperateUserDao oud = new OperateUserDaoImpl();
		oud.updateUserPersist(user);
	}

}
