package com.ltc.server.impl;

import java.util.List;

import com.ltc.domain.CarInfo;
import com.ltc.domain.Page;
import com.ltc.persist.FindCarInfoDao;
import com.ltc.persist.impl.FindCarInfoDaoImpl;
import com.ltc.server.FindCarinfoManager;
/**
 * 查询车检信息的业务层接口实现类
 * @author ltc
 * 2016-5-22 16:26:53
 */
public class FindCarinfoManagerImpl implements FindCarinfoManager {

	@Override
	//此方法用于查询车检信息
	public Page findCarInfoServer(CarInfo ci,int pageIndex) {
		//调用持久层查询数据库中的车检信息
		FindCarInfoDao fcd = new FindCarInfoDaoImpl();
		Page p = fcd.findCarInfoPsersist(ci,pageIndex);
		return p;
	}

	@Override
	//此方法用于查询车牌号码
	public List<String> findCarNumberServer() {
		// 调用持久层查询数据库中的车牌号码信息
		FindCarInfoDao fcd = new FindCarInfoDaoImpl();
		List<String> list = fcd.findCarNumberPsersist();
		return list;
	}

}
