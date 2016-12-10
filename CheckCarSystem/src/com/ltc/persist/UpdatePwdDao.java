package com.ltc.persist;

import com.ltc.domain.Users;

/**
 * 修改密码的持久层接口
 * @author ltc 2016-5-21 17:19:40
 *
 */
public interface UpdatePwdDao {
	//此方法用于查询数据库中用户的旧密码
	public Users findOldPwdPersist(String userName);
	//修改数据库中的用户密码
	public void updatePwdPersist(String userName,String newPwd);
}
