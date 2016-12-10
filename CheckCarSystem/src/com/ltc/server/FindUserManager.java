package com.ltc.server;

import java.util.List;

import com.ltc.domain.Users;

/**
 * 查询用户模块的业务层接口
 * @author ltc 2016-5-19 20:39:31
 *
 */
public interface FindUserManager {
	//此方法用查询用户信息
	public List<Users> findUserServer(Users user);
}
