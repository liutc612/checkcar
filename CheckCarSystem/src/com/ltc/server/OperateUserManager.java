package com.ltc.server;

import com.ltc.domain.Users;

/**
 * 用户操作的业务层接口
 * @author ltc
 * 2016-5-24 09:45:11
 */
public interface OperateUserManager {
	//此方法用于删除用户信息
	public void delUserService(String userName);
	//此方法用于预更新用户查询动作
	public Users preUpdateUserService(String userName);
	//此方法用于更新用户
	public void updateUserService(Users user);
}
