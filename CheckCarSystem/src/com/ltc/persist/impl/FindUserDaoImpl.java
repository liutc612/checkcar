package com.ltc.persist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ltc.commons.JdbcUtil;
import com.ltc.commons.Tools;
import com.ltc.domain.Roles;
import com.ltc.domain.Users;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.persist.FindUserDao;
/**
 * 查询用户模块的持久层实现类
 * @author ltc 2016-5-19 20:44:49 
 *
 */
public class FindUserDaoImpl implements FindUserDao {
	//此方法用于查询数据库中的用户信息
	@Override
	public List<Users> findUserPersist(Users user) {
		//创建一个集合用于存放查询得到的用户信息
		List<Users> list = new ArrayList<Users>();
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = Tools.createFindUserSQL(user);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				//创建user对象用于存放用户信息
				Users u = new Users();
				u.setUserName(rs.getString("username"));
				u.setUserSex(rs.getString("usersex"));
				u.setUserAge(rs.getInt("userage"));
				u.setPhoneNum(rs.getString("phonenum"));
				u.setAddress(rs.getString("address"));
				//创建Roles对象用于存放角色信息
				Roles role = new Roles();
				role.setRoleName(rs.getString("rolename"));
				u.setRole(role);
				list.add(u);
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
