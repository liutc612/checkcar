package com.ltc.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单信息的类
 * 
 * @author ltc 2016-5-18 19:51:18
 *
 */
public class Menus {
	private long menuID;
	private String menuName;
	private String menuURL;
	private long fatherID;
	//建立菜单与角色的关系
	private List<Roles> roleList = new ArrayList<Roles>();
	//建立菜单与功能的关系
	private List<Funcs> funcList = new ArrayList<Funcs>();

	public List<Roles> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Roles> roleList) {
		this.roleList = roleList;
	}

	public List<Funcs> getFuncList() {
		return funcList;
	}

	public void setFuncList(List<Funcs> funcList) {
		this.funcList = funcList;
	}

	public long getMenuID() {
		return menuID;
	}

	public void setMenuID(long menuID) {
		this.menuID = menuID;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuURL() {
		return menuURL;
	}

	public void setMenuURL(String menuURL) {
		this.menuURL = menuURL;
	}

	public long getFatherID() {
		return fatherID;
	}

	public void setFatherID(long fatherID) {
		this.fatherID = fatherID;
	}

}
