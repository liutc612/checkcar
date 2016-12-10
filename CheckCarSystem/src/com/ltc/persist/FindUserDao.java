package com.ltc.persist;

import java.util.List;

import com.ltc.domain.Users;

/**
 * 查询用户模块的持久层接口
 * @author ltc 2016-5-19 20:43:00
 *
 */
public interface FindUserDao {
	//此方法用于查询数据库中的用户信息
	public List<Users> findUserPersist(Users user);
}
