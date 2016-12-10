package com.ltc.server.impl;

import com.ltc.domain.CarInfo;
import com.ltc.persist.OperateCarinfoDao;
import com.ltc.persist.impl.OperateCarinfoDaoImpl;
import com.ltc.server.OperateCarinfoManager;
/**
 * 操作车检信息的业务层接口的实现类
 * @author ltc
 * 2016-5-24 14:15:07
 */
public class OperateCarinfoManagerImpl implements OperateCarinfoManager {

	@Override
	//此方法用于删除车检信息
	public void delCarInfoService(String carNumber) {
		// 调用持久层进行删除数据库中的数据
		OperateCarinfoDao ocd = new OperateCarinfoDaoImpl();
		ocd.delCarInfoPersist(carNumber);
	}

	@Override
	//此方法用于预更新车检信息查询动作
	public CarInfo preUpdateCarInfoService(String carNumber) {
		// 调用持久层进行删除数据库中的数据
		OperateCarinfoDao ocd = new OperateCarinfoDaoImpl();
		CarInfo ci = ocd.preUpdateCarInfoPersist(carNumber);
		return ci;
	}

	@Override
	//此方法用于更新车检信息
	public void updateCarInfoService(CarInfo ci) {
		// 调用持久层进行更新数据库中的数据
		OperateCarinfoDao ocd = new OperateCarinfoDaoImpl();
		ocd.updateCarInfoPersist(ci);
	}

	@Override
	//此方法用于更新车检信息的通知信息
	public void updateCarInfoNoticeService(CarInfo ci) {
		// 调用持久层进行更新数据库中的车检信息的通知信息数据
		OperateCarinfoDao ocd = new OperateCarinfoDaoImpl();
		ocd.updateCarInfoNoticePersist(ci);
	}

}
