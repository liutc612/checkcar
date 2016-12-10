package com.ltc.persist;

import com.ltc.domain.Users;

/**
 * 用户操作的持久层接口
 * @author ltc
 * 2016-5-24 09:47:35
 */
public interface OperateUserDao {
	//此方法用于删除数据库中的用户信息
	public void delUserPersist(String userName);
	//此方法用于更新用户的预查询数据库中的信息
	public Users preUpdateUserPersist(String userName);
	//此方法用于更新数据库中的用户信息
	public void updateUserPersist(Users user);
}
