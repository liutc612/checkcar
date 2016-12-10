package com.ltc.persist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ltc.commons.Constants;
import com.ltc.commons.JdbcUtil;
import com.ltc.domain.CarInfo;
import com.ltc.domain.Page;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.persist.FindHomeDataDao;
/**
 * 查询首页数据的持久层接口实现类
 * @author ltc 2016-5-20 14:54:47
 *
 */
public class FindHomeDataDaoImpl implements FindHomeDataDao {
	//此方法用于查询数据库中首页提示的数据
	@Override
	public Page findHomeDataPersist(int pageIndex) {
		//创建一个集合用于存放所有的数据
		List<CarInfo> list = new ArrayList<CarInfo>();
		Connection conn = null;
		Page p =  new Page();
		try {
			conn = JdbcUtil.getConnection();
			int from = (pageIndex-1) * Constants.PAGE_ITEM_NUMBER +1;
			int to = pageIndex * Constants.PAGE_ITEM_NUMBER;
			PreparedStatement ps = conn.prepareStatement("select t.* from (select rownum r, c.* from carinfo c where (c.caryearcheck-sysdate) < 7 or (c.technologylevel-sysdate) < 7 or (c.towlevel-sysdate) < 7 or (c.compulsoryinsurance-sysdate) < 7) t where t.r between "+from+" and "+to);
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
				ci.setRownum(rs.getLong("r"));
				list.add(ci);
			}
			//满足条件的总条数
			PreparedStatement ps2 = conn.prepareStatement("select count(*) from carinfo c where (c.caryearcheck-sysdate) < 7 or (c.technologylevel-sysdate) < 7 or (c.towlevel-sysdate) < 7 or (c.compulsoryinsurance-sysdate) < 7");
			ResultSet rs2 = ps2.executeQuery();
			int totalNumber = 0;//为总条数
			while(rs2.next()){
				totalNumber = rs2.getInt(1);
			}
			p.setTotalNumber(totalNumber);
			//换算总页数
			int totalPage;
			if(totalNumber % Constants.PAGE_ITEM_NUMBER == 0){
				totalPage = totalNumber / Constants.PAGE_ITEM_NUMBER;
			}else{
				totalPage = totalNumber / Constants.PAGE_ITEM_NUMBER+1;
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

}
