package com.ltc.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限信息的类
 * 
 * @author ltc 2016-5-18 19:49:44
 *
 */
public class Roles {
	private long roleID;
	private String roleName;
	//建立角色与用户的关系
	private List<Users> userList = new ArrayList<Users>();
	//建立角色与菜单的关系
	private List<Menus> menuList = new ArrayList<Menus>();

	public List<Users> getUserList() {
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

	public List<Menus> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menus> menuList) {
		this.menuList = menuList;
	}

	public long getRoleID() {
		return roleID;
	}

	public void setRoleID(long roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "Roles [roleID=" + roleID + ", roleName=" + roleName + "]";
	}

}
