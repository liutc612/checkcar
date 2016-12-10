package com.ltc.persist;

import com.ltc.domain.CarInfo;

/**
 * 添加车检信息的持久层接口
 * @author ltc
 * 2016-5-22 14:23:21
 */
public interface AddCarinfoDao {
	//此方法用于将车检信息录入数据库
	public void addCarInfoPersist(CarInfo ci);
}
