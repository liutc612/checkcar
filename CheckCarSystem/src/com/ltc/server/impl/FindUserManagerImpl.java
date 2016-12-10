package com.ltc.server.impl;

import java.util.List;

import com.ltc.domain.Users;
import com.ltc.persist.FindUserDao;
import com.ltc.persist.impl.FindUserDaoImpl;
import com.ltc.server.FindUserManager;
/**
 * 查询用户模块的业务层实现类
 * @author ltc 2016-5-19 20:41:40
 *
 */
public class FindUserManagerImpl implements FindUserManager {
	//此方法用查询用户信息
	@Override
	public List<Users> findUserServer(Users user) {
		// 调用持久层查询数据库中的用户信息
		FindUserDao fud = new FindUserDaoImpl();
		List<Users> list = fud.findUserPersist(user);
		return list;
	}

}
