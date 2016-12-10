package com.ltc.persist;

import com.ltc.domain.LoginLog;
import com.ltc.domain.Page;

/**
 * 查询日志的持久层接口
 * @author ltc 2016-5-22 10:14:47
 *
 */
public interface FindLoginlogDao {
	//此方法用于查询数据库中的用户登录日志
	public Page findLoginlogPersist(LoginLog ll,int pageIndex);
}
