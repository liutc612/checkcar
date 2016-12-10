package com.ltc.persist;

import com.ltc.domain.LoginLog;
import com.ltc.domain.Users;

/**
 * 用户登录功能的持久层接口
 * @author ltc 2016-5-18
 *
 */
public interface UserLoginDao {
	//此方法用于根据输入的信息查询数据库中的的用户信息
	public Users selectUserByName(String userName);
	//此方法用于将用户的登录日志记录到数据库中
	public void insertLoginLog(LoginLog ll);
}
