package com.ltc.server.impl;

import com.ltc.domain.LoginLog;
import com.ltc.domain.Users;
import com.ltc.exception.UserNotFoundException;
import com.ltc.persist.UserLoginDao;
import com.ltc.persist.impl.UserLoginDaoImpl;
import com.ltc.server.UserLoginManager;
/**
 * 用户登录业务层接口的实现类
 * @author ltc  2016-05-18
 *
 */
public class UserLoginManagerImpl implements UserLoginManager {
	//此方法用于登录时判断用户输入的信息是否正确
	@Override
	public Users userLogin(String userName,String userPwd) {
		// 调用持久层判断用户信息是否正确
		UserLoginDao uld = new UserLoginDaoImpl();
		Users user = uld.selectUserByName(userName);
		//判断输入的用户信息与数据库中的信息是否一致
		if(user == null) {
			//用户不存在
			throw new UserNotFoundException("用户名不存在");
		}else {
			//用户存在，继续判断密码
			if(!user.getUserPwd().equals(userPwd)) {
				//密码不正确
				throw new UserNotFoundException("密码不正确");
			}
		}
		return user;
	}
	//此方法用于记录用户的登录日志
	@Override
	public void addLoginLog(LoginLog ll) {
		//调用持久层进行日记录
		UserLoginDao uld = new UserLoginDaoImpl();
		uld.insertLoginLog(ll);
	}

}
