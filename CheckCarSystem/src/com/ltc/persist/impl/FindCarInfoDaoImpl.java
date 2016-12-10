package com.ltc.persist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ltc.commons.Constants;
import com.ltc.commons.JdbcUtil;
import com.ltc.commons.Tools;
import com.ltc.domain.CarInfo;
import com.ltc.domain.Page;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.persist.FindCarInfoDao;
/**
 * 查询车检信息的持久层接口实现类
 * @author ltc 2016-5-21 14:07:57
 *
 */
public class FindCarInfoDaoImpl implements FindCarInfoDao {
	//此方法用于备份时查询所有的数据
	@Override
	public List<CarInfo> findCarInfoAllPersist() {
		Connection conn = null;
		List<CarInfo> list = new ArrayList<CarInfo>();
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from carinfo");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				//创建CarInfo对象用于存放车检信息
				CarInfo ci = new CarInfo();
				ci.setCarNumber(rs.getString("carnumber"));
				ci.setCarStyle(rs.getString("carstyle"));
				ci.setUserName(rs.getString("username"));
				ci.setCarYearCheck(rs.getDate("caryearcheck"));
				ci.setTechnologyLevel(rs.getDate("technologylevel"));
				ci.setTowLevel(rs.getDate("towlevel"));
				ci.setCompulsoryInsurance(rs.getDate("compulsoryinsurance"));
				ci.setPhoneNum(rs.getString("phonenum"));
				ci.setNotice(rs.getString("notice"));
				list.add(ci);
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
		return list;
	}

	@Override
	public void deleteCarInfoAll(Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("delete from carinfo");
		ps.execute();
	}

	@Override
	public void insertCarInfoAll(Connection conn, List<CarInfo> list) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("insert into carinfo values(?,?,?,?,?,?,?,?,?)");
		for(int i=0;i<list.size();i++){
			CarInfo ci = list.get(i);
			ps.setString(1, ci.getCarNumber());
			ps.setString(2, ci.getCarStyle());
			ps.setString(3, ci.getUserName());
			ps.setDate(4, new java.sql.Date(ci.getCarYearCheck().getTime()));
			ps.setDate(5, new java.sql.Date(ci.getTechnologyLevel().getTime()));
			ps.setDate(6, new java.sql.Date(ci.getTowLevel().getTime()));
			ps.setDate(7, new java.sql.Date(ci.getCompulsoryInsurance().getTime()));
			ps.setString(8, ci.getPhoneNum());
			ps.setString(9, ci.getNotice());
			ps.addBatch();
		}
		ps.executeBatch();
	}

	@Override
	//此方法用于根据输入条件查询车检信息
	public Page findCarInfoPsersist(CarInfo ci,int pageIndex) {
		Connection conn = null;
		List<CarInfo> list = new ArrayList<CarInfo>();
		Page p = new Page();
		try {
			conn = JdbcUtil.getConnection();
			int from = (pageIndex-1) * Constants.PAGE_ITEM_NUMBER_10 +1;
			int to = pageIndex * Constants.PAGE_ITEM_NUMBER_10;
			String sql = Tools.createSelectCarInfoPage(ci, from, to);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				//创建CarInfo对象用于存放车检信息
				CarInfo c = new CarInfo();
				c.setCarNumber(rs.getString("carnumber"));
				c.setCarStyle(rs.getString("carstyle"));
				c.setUserName(rs.getString("username"));
				c.setCarYearCheck(rs.getDate("caryearcheck"));
				c.setTechnologyLevel(rs.getDate("technologylevel"));
				c.setTowLevel(rs.getDate("towlevel"));
				c.setCompulsoryInsurance(rs.getDate("compulsoryinsurance"));
				c.setPhoneNum(rs.getString("phonenum"));
				c.setNotice(rs.getString("notice"));
				c.setRownum(rs.getLong("r"));
				list.add(c);
			}
			//满足条件的总条数
			String sql2 = Tools.createSelectCarInfoTotal(ci);
			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ResultSet rs2 = ps2.executeQuery();
			int totalNumber = 0;//为总条数
			while(rs2.next()) {
				totalNumber = rs2.getInt(1);
			}
			p.setTotalNumber(totalNumber);
			//换算总页数
			int totalPage;
			if(totalNumber % Constants.PAGE_ITEM_NUMBER == 0){
				totalPage = totalNumber / Constants.PAGE_ITEM_NUMBER_10;
			}else{
				totalPage = totalNumber / Constants.PAGE_ITEM_NUMBER_10+1;
			}
			//将查询到结果集放入page对象中
			p.setResult(list);
			//总页数
			p.setTotalPage(totalPage);
			p.setPageIndex(pageIndex);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("操作失败，请重试");
		}catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationError("操作失败");
		}finally {
			JdbcUtil.closeConnection(conn);
		}
		return p;
	}

	@Override
	//此方法用于查询数据库中的车牌号码信息
	public List<String> findCarNumberPsersist() {
		Connection conn = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("select carnumber from carinfo");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				list.add(rs.getString(1));
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
		return list;
	}

}
