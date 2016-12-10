package com.ltc.domain;

/**
 * 功能信息的类
 * 
 * @author ltc 2016-5-18 19:54:08
 *
 */
public class Funcs {
	private long funcID;
	private String funcName;
	private String funcURL;
	//建立功能与菜单的关系
	private Menus menu;

	public Menus getMenu() {
		return menu;
	}

	public void setMenu(Menus menu) {
		this.menu = menu;
	}

	public long getFuncID() {
		return funcID;
	}

	public void setFuncID(long funcID) {
		this.funcID = funcID;
	}

	public String getFuncName() {
		return funcName;
	}

	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	public String getFuncURL() {
		return funcURL;
	}

	public void setFuncURL(String funcURL) {
		this.funcURL = funcURL;
	}

	@Override
	public String toString() {
		return "Funcs [funcID=" + funcID + ", funcName=" + funcName + ", funcURL=" + funcURL + ", menu=" + menu + "]";
	}

}
