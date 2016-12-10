package com.ltc.persist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ltc.commons.JdbcUtil;
import com.ltc.domain.Users;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.persist.UpdatePwdDao;
/**
 * 修改密码的持久层接口实现类
 * @author ltc 2016-5-21 17:21:39
 *
 */
public class UpdatePwdDaoImpl implements UpdatePwdDao {

	@Override
	//此方法用于查询数据库中用户的旧密码
	public Users findOldPwdPersist(String userName) {
		Connection conn = null;
		Users user = new Users();
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("select userpwd  from users where username = ?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user.setUserPwd(rs.getString(1));
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
		return user;
	}

	@Override
	//修改数据库中的用户密码
	public void updatePwdPersist(String userName, String newPwd) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("update users set userpwd = ? where username = ?");
			ps.setString(1, newPwd);
			ps.setString(2, userName);
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
