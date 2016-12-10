package com.ltc.persist;

import java.util.List;

import com.ltc.domain.Roles;
import com.ltc.domain.Users;

/**
 * 添加用户的持久层接口
 * @author ltc 2016-5-19 10:23:11
 *
 */
public interface AddUserDao {
	//此方法用于添加用户前的查询数据库中的角色信息
	public List<Roles> findRoleAllPersist();
	//此方法用于将用户信息录入到数据库
	public void addUserPersist(Users user);
}
