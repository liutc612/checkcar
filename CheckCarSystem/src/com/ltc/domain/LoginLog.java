package com.ltc.domain;

import java.util.Date;

/**
 * 用户登录日志的类
 * 
 * @author ltc 2016-5-19 08:50:56
 *
 */
public class LoginLog {
	private long loginID;
	private String loginName;
	private String loginIP;
	private String loginState;
	private Date loginDate;
	private long rownum;

	public long getLoginID() {
		return loginID;
	}

	public void setLoginID(long loginID) {
		this.loginID = loginID;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public long getRownum() {
		return rownum;
	}

	public void setRownum(long rownum) {
		this.rownum = rownum;
	}

}
