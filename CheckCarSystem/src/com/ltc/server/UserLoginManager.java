package com.ltc.server;

import com.ltc.domain.LoginLog;
import com.ltc.domain.Users;

/**
 * 用户登录的业务层接口
 * @author ltc  2016-05-18
 *
 */
public interface UserLoginManager {
	//此方法用于实现用户登录功能（登录时判断用户输入的信息是否正确）
	public Users userLogin(String userName,String userPwd);
	//此方法用于记录用户的登录日志
	public void addLoginLog(LoginLog ll);
}
