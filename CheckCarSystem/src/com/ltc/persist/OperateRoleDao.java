package com.ltc.persist;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ltc.domain.Roles;

/**
 * 角色管理的持久层接口
 * @author ltc
 * 2016-5-27 11:13:59
 */
public interface OperateRoleDao {
	//此方法用于查询数据库中的角色数据
	public List<Roles> findRolePersist(String roleName);
	//此方法用于删除数据库中的角色数据(角色菜单联合表)
	public void deleteRoleMenuPersist(long roleID,Connection conn)  throws SQLException;
	//此方法用于删除数据库中的角色数据(角色表)
	public void deleteRolePersist(long roleID,Connection conn)  throws SQLException;
}
