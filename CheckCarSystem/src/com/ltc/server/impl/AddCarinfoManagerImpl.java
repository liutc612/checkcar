package com.ltc.server.impl;

import com.ltc.domain.CarInfo;
import com.ltc.persist.AddCarinfoDao;
import com.ltc.persist.impl.AddCarinfoDaoImpl;
import com.ltc.server.AddCarinfoManager;
/**
 * 添加车检信息业务层接口的实现类
 * @author ltc
 * 2016-5-22 14:22:08
 */
public class AddCarinfoManagerImpl implements AddCarinfoManager {

	@Override
	//此方法用于添加车检信息
	public void addCarInfoServer(CarInfo ci) {
		//调用持久层将数据录入数据库
		AddCarinfoDao acd = new AddCarinfoDaoImpl();
		acd.addCarInfoPersist(ci);
	}

}
