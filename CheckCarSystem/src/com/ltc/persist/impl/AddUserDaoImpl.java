package com.ltc.persist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ltc.commons.JdbcUtil;
import com.ltc.domain.Roles;
import com.ltc.domain.Users;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.persist.AddUserDao;
/**
 * 添加用户的持久层实现类
 * @author ltc 2016-5-19 10:28:15 
 *
 */
public class AddUserDaoImpl implements AddUserDao {
	//此方法用于添加用户前的查询数据库中的角色信息
	@Override
	public List<Roles> findRoleAllPersist() {
		Connection conn = null;
		//创建一个List用于存放角色信息
		List<Roles> list = new ArrayList<Roles>();
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from roles");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				//创建Roles对象存放每个角色
				Roles role = new Roles();
				role.setRoleID(rs.getLong("roleid"));
				role.setRoleName(rs.getString("rolename"));
				list.add(role);
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
	//此方法用于实现将用户信息录入到数据库
	@Override
	public void addUserPersist(Users user) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("insert into users values(?,?,?,?,?,?,?)");
			ps.setString(1, user.getUserName());
			ps.setLong(2, user.getRole().getRoleID());
			ps.setString(3, user.getUserPwd());
			ps.setString(4, user.getUserSex());
			ps.setInt(5, user.getUserAge());
			ps.setString(6, user.getPhoneNum());
			ps.setString(7, user.getAddress());
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
