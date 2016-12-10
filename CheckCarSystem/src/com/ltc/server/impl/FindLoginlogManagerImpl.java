package com.ltc.server.impl;

import com.ltc.domain.LoginLog;
import com.ltc.domain.Page;
import com.ltc.persist.FindLoginlogDao;
import com.ltc.persist.impl.FindLoginlogDaoImpl;
import com.ltc.server.FindLoginlogManager;
/**
 * 查询日志的业务层接口实现类
 * @author ltc 2016-5-22 10:13:29
 *
 */
public class FindLoginlogManagerImpl implements FindLoginlogManager {

	@Override
	//此方法用于查询用户的登录日志
	public Page findLoginlogServer(LoginLog ll,int pageIndex) {
		// 调用持久层查询数据库中的数据
		FindLoginlogDao fld = new FindLoginlogDaoImpl();
		Page p  = fld.findLoginlogPersist(ll, pageIndex);
		return p;
	}

}
