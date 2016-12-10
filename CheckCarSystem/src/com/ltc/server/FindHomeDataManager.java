package com.ltc.server;

import com.ltc.domain.Page;

/**
 * 查询首页数据的业务层接口
 * @author ltc 2016-5-20 14:36:28
 *
 */
public interface FindHomeDataManager {
	//此方法用于查询首页提示的数据
	public Page findHomeDataServer(int pageIndex);
}
