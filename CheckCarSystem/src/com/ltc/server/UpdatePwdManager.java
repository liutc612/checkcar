package com.ltc.server;

import com.ltc.domain.Users;

/**
 * 修改密码的业务层接口
 * @author ltc 2016-5-21 17:05:27
 *
 */
public interface UpdatePwdManager {
	//此方法用于查询用户的旧密码
	public Users findOldPwdServer(String userName);
	//此方法用于修改密码
	public void updatePwdServer(String userName,String newPwd);
}
