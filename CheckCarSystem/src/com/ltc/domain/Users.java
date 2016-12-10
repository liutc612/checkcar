package com.ltc.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 此类用于封装用户信息
 * 
 * @author ltc 2016-5-18
 */
public class Users {
	private String userName;
	private String userPwd;
	private String userSex;
	private int userAge;
	private String phoneNum;
	private String address;
	//建立用户与角色的关系
	private Roles role;
	//建立用户与菜单的关系
	private List<Menus> menuList = new ArrayList<Menus>();
	//建立用户与功能的关系
	private List<Funcs> funcList = new ArrayList<Funcs>();
	
	public List<Menus> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menus> menuList) {
		this.menuList = menuList;
	}

	public List<Funcs> getFuncList() {
		return funcList;
	}

	public void setFuncList(List<Funcs> funcList) {
		this.funcList = funcList;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Users [userName=" + userName + ", userPwd=" + userPwd + ", userSex=" + userSex + ", userAge=" + userAge
				+ ", phoneNum=" + phoneNum + ", address=" + address + "]";
	}

}
