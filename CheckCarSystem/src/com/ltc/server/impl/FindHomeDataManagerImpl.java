package com.ltc.server.impl;

import com.ltc.domain.Page;
import com.ltc.persist.FindHomeDataDao;
import com.ltc.persist.impl.FindHomeDataDaoImpl;
import com.ltc.server.FindHomeDataManager;
/**
 * 查询首页数据的业务层接口实现类
 * @author ltc 2016-5-20 14:51:58
 *
 */
public class FindHomeDataManagerImpl implements FindHomeDataManager {
	//此方法用于查询首页提示的数据
	@Override
	public Page findHomeDataServer(int pageIndex) {
		// 调用持久层查询数据库中的数据
		FindHomeDataDao fhdd = new FindHomeDataDaoImpl();
		Page p = fhdd.findHomeDataPersist(pageIndex);
		return p;
	}

}
