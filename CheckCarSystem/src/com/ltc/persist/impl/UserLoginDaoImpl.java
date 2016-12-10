package com.ltc.persist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ltc.commons.JdbcUtil;
import com.ltc.domain.Funcs;
import com.ltc.domain.LoginLog;
import com.ltc.domain.Menus;
import com.ltc.domain.Roles;
import com.ltc.domain.Users;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.persist.UserLoginDao;
/**
 * 用户登录功能的持久层接口的实现类
 * @author ltc 2016-5-18
 *
 */
public class UserLoginDaoImpl implements UserLoginDao {
	//此方法用于根据输入的信息查询数据库中的的用户信息
	@Override
	public Users selectUserByName(String userName) {
		Connection conn = null;
		//创建Users对象存储查询的user信息
		Users user = null;
		//预定义一个roleID
		long roleID = -1;
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from users where username = ?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				user = new Users();
				user.setUserName(rs.getString("username"));
				user.setUserPwd(rs.getString("userpwd"));
				//取出用户的角色信息
				roleID = rs.getLong("roleid");
			}
			//根据查出的已经存在的用户去查询其所对应的角色
			if(roleID != -1 && user != null) {
				//用户存在，根据roleId去数据库中查询角色信息
				PreparedStatement ps2 = conn.prepareStatement("select * from roles where roleid = ?");
				ps2.setLong(1, roleID);
				ResultSet rs2 = ps2.executeQuery();
				//创建Roles对象存储角色信息
				Roles role = new Roles();
				while(rs2.next()) {
					role.setRoleID(rs2.getLong("roleid"));
					role.setRoleName(rs2.getString("rolename"));
				}
				//把role对象和user对象建立联系
				user.setRole(role);
				//根据roleId查询菜单
				PreparedStatement ps3 = conn.prepareStatement("select * from roles_menus r,menus m where r.menuid = m.menuid and r.roleid = ?");
				ps3.setLong(1, roleID);
				ResultSet rs3 = ps3.executeQuery();
				while(rs3.next()) {
					//创建Menus对象存储菜单信息
					Menus menu = new Menus();
					menu.setMenuID(rs3.getLong("menuid"));
					menu.setMenuName(rs3.getString("menuname"));
					menu.setMenuURL(rs3.getString("menuurl"));
					menu.setFatherID(rs3.getLong("fatherid"));
					//将菜单信息存放到Users的菜单属性中
					user.getMenuList().add(menu);
				}
				//根据roleId查询功能Func
				PreparedStatement ps4 = conn.prepareStatement("select * from roles_menus r,menus m,funcs f where r.menuid = m.menuid and m.menuid = f.menuid and r.roleid = ?");
				ps4.setLong(1, roleID);
				ResultSet rs4 = ps4.executeQuery();
				while(rs4.next()) {
					//创建Funcs对象存放功能
					Funcs func = new Funcs();
					func.setFuncID(rs4.getLong("funcid"));
					func.setFuncName(rs4.getString("funcname"));
					func.setFuncURL(rs4.getString("funcurl"));
					//将功能信息存放到Users的功能属性中
					user.getFuncList().add(func);
				}
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
	//此方法用于将用户的登录日志记录到数据库中
	@Override
	public void insertLoginLog(LoginLog ll) {
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("insert into loginlog values(myseq.nextval,?,?,?,?)");
			ps.setString(1, ll.getLoginName());
			ps.setString(2, ll.getLoginIP());
			ps.setString(3, ll.getLoginState());
			ps.setDate(4, new java.sql.Date(ll.getLoginDate().getTime()));
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
