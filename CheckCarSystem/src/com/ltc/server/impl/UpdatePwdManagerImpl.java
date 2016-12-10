package com.ltc.server.impl;

import com.ltc.domain.Users;
import com.ltc.persist.UpdatePwdDao;
import com.ltc.persist.impl.UpdatePwdDaoImpl;
import com.ltc.server.UpdatePwdManager;
/**
 * 修改密码的持久层接口实现类
 * @author ltc 2016-5-21 17:21:20
 *
 */
public class UpdatePwdManagerImpl implements UpdatePwdManager {
	@Override
	//此方法用于查询用户的旧密码
	public Users findOldPwdServer(String userName) {
		// 调用持久层查询数据库中的密码
		UpdatePwdDao upd = new UpdatePwdDaoImpl();
		Users user = upd.findOldPwdPersist(userName);
		return user;
	}

	@Override
	//此方法用于修改密码
	public void updatePwdServer(String userName, String newPwd) {
		// 调用持久层修改密码
		UpdatePwdDao upd = new UpdatePwdDaoImpl();
		upd.updatePwdPersist(userName, newPwd);
	}

}
