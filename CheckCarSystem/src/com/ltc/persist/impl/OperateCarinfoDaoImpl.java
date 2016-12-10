package com.ltc.persist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ltc.commons.JdbcUtil;
import com.ltc.domain.CarInfo;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.persist.OperateCarinfoDao;
/**
 * 操作车检信息的持久层接口的实现类
 * @author ltc
 * 2016-5-24 14:18:16
 */
public class OperateCarinfoDaoImpl implements OperateCarinfoDao {

	@Override
	//此方法用于删除数据库中的车检信息
	public void delCarInfoPersist(String carNumber) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("delete from carinfo where carnumber = ?");
			ps.setString(1, carNumber);
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

	@Override
	//此方法用于更新车检信息的预查询数据库中的信息
	public CarInfo preUpdateCarInfoPersist(String carNumber) {
		//创建user对象用于存放用户信息
		CarInfo ci = new CarInfo();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from carinfo where 1=1 and carnumber = ?");
			ps.setString(1, carNumber);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ci.setCarNumber(rs.getString("carnumber"));
				ci.setCarStyle(rs.getString("carstyle"));
				ci.setUserName(rs.getString("username"));
				ci.setCarYearCheck(rs.getDate("caryearcheck"));
				ci.setTechnologyLevel(rs.getDate("technologylevel"));
				ci.setTowLevel(rs.getDate("towlevel"));
				ci.setCompulsoryInsurance(rs.getDate("compulsoryinsurance"));
				ci.setPhoneNum(rs.getString("phonenum"));
				ci.setNotice(rs.getString("notice"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("操作失败，请重试");
		}catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationError("操作失败");
		}finally {
			JdbcUtil.closeConnection(conn);
		}
		return ci;
	}

	@Override
	//此方法用于更新数据库中的车检信息
	public void updateCarInfoPersist(CarInfo ci) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("update carinfo set carstyle = ?,username = ?,caryearcheck = ?,technologylevel = ?,towlevel = ?,compulsoryinsurance = ?,phonenum = ?,notice = ? where carnumber = ?");
			ps.setString(1, ci.getCarStyle());
			ps.setString(2, ci.getUserName());
			ps.setDate(3, new java.sql.Date(ci.getCarYearCheck().getTime()));
			ps.setDate(4, new java.sql.Date(ci.getTechnologyLevel().getTime()));
			ps.setDate(5, new java.sql.Date(ci.getTowLevel().getTime()));
			ps.setDate(6, new java.sql.Date(ci.getCompulsoryInsurance().getTime()));
			ps.setString(7, ci.getPhoneNum());
			ps.setString(8, ci.getNotice());
			ps.setString(9, ci.getCarNumber());
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

	@Override
	//此方法用于更新数据库中的车检信息的通知信息
	public void updateCarInfoNoticePersist(CarInfo ci) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("update carinfo set notice = ? where carnumber = ?");
			ps.setString(1, ci.getNotice());
			ps.setString(2, ci.getCarNumber());
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
