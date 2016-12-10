package com.ltc.server;

import com.ltc.domain.LoginLog;
import com.ltc.domain.Page;

/**
 * 查询日志的业务层接口
 * @author ltc 2016-5-22 10:04:45
 *
 */
public interface FindLoginlogManager {
	//此方法用于查询用户的登录日志
	public Page findLoginlogServer(LoginLog ll,int pageIndex);
}
