package com.ltc.persist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ltc.commons.JdbcUtil;
import com.ltc.domain.Roles;
import com.ltc.domain.Users;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.persist.OperateUserDao;
/**
 * 用户操作的持久层接口实现类
 * @author ltc
 * 2016-5-24 09:48:40
 */
public class OperateUserDaoImpl implements OperateUserDao {

	@Override
	//此方法用于删除数据库中的用户信息
	public void delUserPersist(String userName) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("delete from users where username = ?");
			ps.setString(1, userName);
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
	//此方法用于更新用户的预查询数据库中的信息
	public Users preUpdateUserPersist(String userName) {
		//创建user对象用于存放用户信息
		Users u = new Users();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from users u,roles r where 1=1 and u.roleid = r.roleid and username = ?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				u.setUserName(rs.getString("username"));
				u.setUserSex(rs.getString("usersex"));
				u.setUserAge(rs.getInt("userage"));
				u.setPhoneNum(rs.getString("phonenum"));
				u.setAddress(rs.getString("address"));
				//创建Roles对象用于存放角色信息
				Roles role = new Roles();
				role.setRoleName(rs.getString("rolename"));
				u.setRole(role);
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
		return u;
	}

	@Override
	//此方法用于更新数据库中的用户信息
	public void updateUserPersist(Users user) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("update users set userage = ?,usersex = ?,roleid = ?,address = ?,phonenum = ? where username = ?");
			ps.setInt(1, user.getUserAge());
			ps.setString(2, user.getUserSex());
			ps.setLong(3, user.getRole().getRoleID());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getPhoneNum());
			ps.setString(6, user.getUserName());
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
