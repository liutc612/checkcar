package com.ltc.persist.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ltc.commons.JdbcUtil;
import com.ltc.domain.Roles;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.persist.OperateRoleDao;
/**
 * 角色管理的持久层接口实现类
 * @author ltc
 * 2016-5-27 11:15:50
 */
public class OperateRoleDaoImpl implements OperateRoleDao {

	@Override
	//此方法用于查询数据库中的角色数据
	public List<Roles> findRolePersist(String roleName) {
		Connection conn = null;
		//创建一个List用于存放角色信息
		List<Roles> list = new ArrayList<Roles>();
		try {
			conn = JdbcUtil.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from roles where rolename like '%"+roleName+"%'");
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

	@Override
	//此方法用于删除数据库中的角色数据(角色菜单联合表)
	public void deleteRoleMenuPersist(long roleID, Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("delete from roles_menus where roleid = ?");
		ps.setLong(1, roleID);
		ps.execute();
	}
	
	@Override
	//此方法用于删除数据库中的角色数据(角色表)
	public void deleteRolePersist(long roleID,Connection conn) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("delete from roles where roleid = ?");
		ps.setLong(1, roleID);
		ps.execute();
	}
	
}
