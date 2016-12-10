package com.ltc.commons;
/**
 * 此类放置一些配置信息
 */

public class Constants {
	// 链接数据库的配置信息
	public static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	public static final String DB_USER = "checkcar";
	public static final String DB_PASSWORD = "orcl";
	//session存放登录用户的key
	public static final String USER_SESSION_KEY = "user";
	//页面提示信息的request的key
	public static final String MSG_REQUEST_KEY = "msg";
	//session中存放验证码的key
	public static final String VALIDATE_CODE_KEY = "vd";
	//定义分页显示时每页显示的条数(8条)
	public static final int PAGE_ITEM_NUMBER = 8;
	//定义分页显示时每页显示的条数(10条)
	public static final int PAGE_ITEM_NUMBER_10 = 10;
	//其他对象的key
	public static final String VAL_REQUEST_KEY = "val";
}
