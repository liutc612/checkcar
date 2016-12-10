package com.ltc.server.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ltc.commons.JdbcUtil;
import com.ltc.domain.Roles;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.persist.OperateRoleDao;
import com.ltc.persist.impl.OperateRoleDaoImpl;
import com.ltc.server.OperateRoleManager;
/**
 * 角色管理业务层接口的实现类
 * @author ltc
 * 2016-5-27 11:12:27
 */
public class OperateRoleManagerImpl implements OperateRoleManager {

	@Override
	//此方法用于查询角色信息
	public List<Roles> findRoleService(String roleName) {
		// 调用持久层查询数据库中的角色数据
		OperateRoleDao ord = new OperateRoleDaoImpl();
		List<Roles> list = ord.findRolePersist(roleName);
		return list;
	}

	@Override
	//此方法用于删除角色信息
	public void deleteRoleService(long roleID) {
		// 调用持久层删除数据库中的角色数据
		OperateRoleDao ord = new OperateRoleDaoImpl();
		//需要删除角色表和角色菜单联合表(做事物控制)
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			//先删除联合表的数据
			ord.deleteRoleMenuPersist(roleID, conn);
			//再删除角色表
			ord.deleteRolePersist(roleID, conn);
		}catch(SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollbackConnection(conn);
			throw new ApplicationException("操作失败，请重试");
		}catch(Exception e) {
			e.printStackTrace();
			throw new ApplicationError("操作失败");
		}finally {
			JdbcUtil.closeConnection(conn);
		}
	}

}
