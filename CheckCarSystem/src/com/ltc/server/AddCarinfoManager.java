package com.ltc.server;

import com.ltc.domain.CarInfo;

/**
 * 添加车检信息的业务层接口
 * @author ltc 2016-5-22 14:20:41
 *
 */
public interface AddCarinfoManager {
	//此方法用于添加车检信息
	public void addCarInfoServer(CarInfo ci);
}
