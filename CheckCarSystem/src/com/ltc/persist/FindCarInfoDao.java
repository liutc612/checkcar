package com.ltc.persist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ltc.domain.CarInfo;
import com.ltc.domain.Page;

/**
 * 查询车检信息的持久层接口
 * @author ltc 2016-5-21 14:04:38
 *
 */
public interface FindCarInfoDao {
	//此方法用于备份时查询所有的数据
	public List<CarInfo> findCarInfoAllPersist();
	//删除数据库中的所有数据
	public void deleteCarInfoAll(Connection conn) throws SQLException;
	//将备份数据插入到数据库
	public void insertCarInfoAll(Connection conn, List<CarInfo> list) throws SQLException;
	//此方法用于根据输入条件查询车检信息
	public Page findCarInfoPsersist(CarInfo ci,int pageIndex);
	//此方法用于查询数据库中的车牌号码信息
	public List<String> findCarNumberPsersist();
}
