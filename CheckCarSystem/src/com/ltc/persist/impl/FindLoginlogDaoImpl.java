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
import com.ltc.domain.LoginLog;
import com.ltc.domain.Page;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.persist.FindLoginlogDao;
/**
 * 查询日志的持久层接口实现类
 * @author ltc 2016-5-22 10:16:22
 *
 */
public class FindLoginlogDaoImpl implements FindLoginlogDao {

	@Override
	//此方法用于查询数据库中的用户登录日志
	public Page findLoginlogPersist(LoginLog ll,int pageIndex) {
		//创建一个集合用于存放所有的数据
		List<LoginLog> logList = new ArrayList<LoginLog>();
		Connection conn = null;
		Page p = new Page();
		try {
			conn = JdbcUtil.getConnection();
			int from = (pageIndex-1) * Constants.PAGE_ITEM_NUMBER_10 +1;
			int to = pageIndex * Constants.PAGE_ITEM_NUMBER_10;
			String sql = Tools.createPageSelectLoginLogSql(ll, from, to);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				LoginLog lgl = new LoginLog();
				lgl.setLoginID(rs.getLong("loginid"));
				lgl.setLoginName(rs.getString("loginname"));
				lgl.setLoginIP(rs.getString("loginip"));
				lgl.setLoginState(rs.getString("loginstate"));
				lgl.setLoginDate(rs.getDate("logindate"));
				lgl.setRownum(rs.getLong("r"));
				logList.add(lgl);
			}
			//满足条件的总条数
			String countSql = Tools.createSelectLoginLogTotalSql(ll);
			PreparedStatement ps2 = conn.prepareStatement(countSql);
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
			p.setResult(logList);
			//总页数
			p.setTotalPage(totalPage);
			p.setPageIndex(pageIndex);
		}catch(SQLException e) {
			throw new ApplicationException("操作失败，请重试");
		}catch(Exception e) {
			throw new ApplicationError("操作失败");
		}finally {
			JdbcUtil.closeConnection(conn);
		}
		return p;
	}

}
