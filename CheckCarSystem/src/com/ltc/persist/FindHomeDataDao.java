package com.ltc.persist;

import com.ltc.domain.Page;
/**
 * 查询首页数据的持久层接口
 * @author ltc 2016-5-20 14:53:38
 * 
 */
public interface FindHomeDataDao {
	//此方法用于查询数据库中首页提示的数据
	public Page findHomeDataPersist(int pageIndex);
}
