package com.ltc.server;

import com.ltc.domain.CarInfo;

/**
 * 操作车检信息的业务层接口
 * @author ltc
 * 2016-5-24 14:13:57
 */
public interface OperateCarinfoManager {
	//此方法用于删除车检信息
	public void delCarInfoService(String carNumber);
	//此方法用于预更新车检信息查询动作
	public CarInfo preUpdateCarInfoService(String carNumber);
	//此方法用于更新车检信息
	public void updateCarInfoService(CarInfo ci);
	//此方法用于更新车检信息的通知信息
	public void updateCarInfoNoticeService(CarInfo ci);
}
