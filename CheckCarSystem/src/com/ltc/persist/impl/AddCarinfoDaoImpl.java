package com.ltc.persist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ltc.commons.JdbcUtil;
import com.ltc.domain.CarInfo;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.persist.AddCarinfoDao;
/**
 * 添加车检信息的持久层接口实现类
 * @author ltc
 * 2016-5-22 14:24:51
 */
public class AddCarinfoDaoImpl implements AddCarinfoDao {

	@Override
	//此方法用于将车检信息录入数据库
	public void addCarInfoPersist(CarInfo ci) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("insert into carinfo values(?,?,?,?,?,?,?,?,default)");
			ps.setString(1, ci.getCarNumber());
			ps.setString(2, ci.getCarStyle());
			ps.setString(3, ci.getUserName());
			ps.setDate(4, new java.sql.Date(ci.getCarYearCheck().getTime()));
			ps.setDate(5, new java.sql.Date(ci.getTechnologyLevel().getTime()));
			ps.setDate(6, new java.sql.Date(ci.getTowLevel().getTime()));
			ps.setDate(7, new java.sql.Date(ci.getCompulsoryInsurance().getTime()));
			ps.setString(8, ci.getPhoneNum());
			ps.execute();
			conn.commit();
		}catch(SQLException e) {
			JdbcUtil.rollbackConnection(conn);
			e.printStackTrace();
			throw new ApplicationException("操作失败，请重试");
		}catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationError("操作失败");
		}finally {
			JdbcUtil.closeConnection(conn);
		}
	}

}
