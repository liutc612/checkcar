package com.ltc.persist;

import com.ltc.domain.CarInfo;

/**
 * 操作车检信息的持久层接口
 * @author ltc
 * 2016-5-24 14:16:18
 */
public interface OperateCarinfoDao {
	//此方法用于删除数据库中的车检信息
	public void delCarInfoPersist(String carNumber);
	//此方法用于更新车检信息的预查询数据库中的信息
	public CarInfo preUpdateCarInfoPersist(String carNumber);
	//此方法用于更新数据库中的车检信息
	public void updateCarInfoPersist(CarInfo ci);
	//此方法用于更新数据库中的车检信息的通知信息
	public void updateCarInfoNoticePersist(CarInfo ci);
}
