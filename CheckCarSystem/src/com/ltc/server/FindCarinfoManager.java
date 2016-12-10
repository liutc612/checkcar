package com.ltc.server;

import java.util.List;

import com.ltc.domain.CarInfo;
import com.ltc.domain.Page;

/**
 * 查询车检信息的业务层接口
 * @author ltc
 * 2016-5-22 16:24:10
 */
public interface FindCarinfoManager {
	//此方法用于查询车检信息
	public Page findCarInfoServer(CarInfo ci,int pageIndex);
	//此方法用于查询车牌号码
	public List<String> findCarNumberServer();
}
