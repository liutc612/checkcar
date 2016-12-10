package com.ltc.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.ltc.domain.CarInfo;
import com.ltc.domain.LoginLog;
import com.ltc.domain.Roles;
import com.ltc.domain.Users;

/**
 * 自定义工具类
 * @author ltc 2016-5-19 16:17:17
 *
 */
public class Tools {
	
	//此方法用于添加和查询用户时获取页面输入的信息
	public static Users getUserInputInfo(HttpServletRequest request) {
		//创建user对象用于存放用户信息
		Users user = new Users();
		user.setUserName(request.getParameter("userName"));
		if(request.getParameter("userAge") != null && request.getParameter("userAge").length() > 0) {
			user.setUserAge(Integer.parseInt(request.getParameter("userAge")));
		}
		//密码需要加密处理
		user.setUserPwd(MD5.getMD5(request.getParameter("userPwd")));
		//创建role对象用于存放角色ID
		Roles role = new Roles();
		if(request.getParameter("userRole") != null && request.getParameter("userRole").length() > 0) {
			role.setRoleID(Long.parseLong(request.getParameter("userRole")));
			user.setRole(role);
		}
		user.setUserSex(request.getParameter("userSex"));
		user.setAddress(request.getParameter("userAddr"));
		user.setPhoneNum(request.getParameter("phoneNum"));
		return user;
	}
	//此方法用于拼接查询用户条件的SQL语句
	public static String createFindUserSQL(Users user) {
		StringBuffer sb = new StringBuffer("select * from users u,roles r where 1=1 and u.roleid = r.roleid ");
		if(user.getUserName() != null && user.getUserName().length() > 0) {
			sb.append(" and u.username like '%").append(user.getUserName()).append("%'");
		}
		if(user.getUserSex() != null && user.getUserSex().length() > 0) {
			sb.append(" and u.usersex = '").append(user.getUserSex()).append("'");
		}
		if(user.getUserAge() > 0) {
			sb.append(" and u.userage = ").append(user.getUserAge());
		}
		if(user.getPhoneNum() != null && user.getPhoneNum().length() > 0) {
			sb.append(" and u.phonenum like '%").append(user.getPhoneNum()).append("%'");
		}
		if(user.getAddress() != null && user.getAddress().length() > 0) {
			sb.append(" and u.address like '%").append(user.getAddress()).append("%'");
		}
		if(user.getRole() != null && user.getRole().getRoleID() >0) {
			sb.append(" and r.roleid = ").append(user.getRole().getRoleID());
		}
		return sb.toString();
	}
	
	//日期转字符串的方法
	public static String dateToString(Date date,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	//字符串转日期的方法
	public static Date stringToDate(String source,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			if(source != null && source.length() > 0) {
				if(source.length() > 10) {
					SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
					date = sdf1.parse(source);
				}else {
					date = sdf.parse(source);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	//此方法用于获取用户页面输入的查询登录日志的条件
	public static LoginLog getFindLoginLogInfo(HttpServletRequest request) {
		//获取用户页面输入的查询条件
		String loginName = request.getParameter("loginName");
		String loginIP = request.getParameter("loginIP");
		String loginState = request.getParameter("loginState");
		String loginDate = request.getParameter("loginDate");
		LoginLog ll = new LoginLog();
		ll.setLoginName(loginName);
		ll.setLoginIP(loginIP);
		ll.setLoginState(loginState);
		ll.setLoginDate(stringToDate(loginDate, "yyyy-MM-dd"));
		return ll;
	}
	
	//此方法用来拼接查询登录日志的SQL语句(子查询)
	public static String createSelectLoginLogSql(LoginLog ll) {
		StringBuffer sql = new StringBuffer("select rownum r, l.* from loginlog l where 1=1 ");
		if(ll.getLoginName() != null && ll.getLoginName().length() > 0) {
			sql.append(" and l.loginname like '%").append(ll.getLoginName()).append("%'");
		}
		if(ll.getLoginIP() != null && ll.getLoginIP().length() > 0) {
			sql.append(" and l.loginip = '").append(ll.getLoginIP()).append("'");
		}
		if(ll.getLoginState() != null && ll.getLoginState().length() > 0) {
			sql.append(" and l.loginstate = '").append(ll.getLoginState()).append("'");
		}
		if(ll.getLoginDate() != null) {
			sql.append(" and to_char(l.logindate,'yyyy-MM-dd') = '").append(Tools.dateToString(ll.getLoginDate(), "yyyy-MM-dd")).append("'");
		}
		return sql.toString();
	}
	//此方法用来拼接查询登录日志的SQL语句(分页查询)
	public static String createPageSelectLoginLogSql(LoginLog ll,int from,int to) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (").append(createSelectLoginLogSql(ll)).append(") t where t.r between ").append(from).append(" and ").append(to);
		return sql.toString();
	}
	//此方法用来拼接查询总条数的sql语句
	public static String createSelectLoginLogTotalSql(LoginLog ll){
		StringBuffer sb = new StringBuffer("select count(*) from loginlog l where 1=1 ");
		if(ll.getLoginDate() != null ){
			sb.append(" and to_char(l.logindate,'yyyy-MM-dd') = '").append(Tools.dateToString(ll.getLoginDate(), "yyyy-MM-dd")).append("'");
		}
		if(ll.getLoginIP() != null && ll.getLoginIP().length() > 0){
			sb.append(" and l.loginip = '").append(ll.getLoginIP()).append("'");
		}
		if(ll.getLoginName() != null && ll.getLoginName().length() > 0){
			sb.append(" and l.loginname like '%").append(ll.getLoginName()).append("%'");
		}
		if(ll.getLoginState() != null && ll.getLoginState().length() > 0){
			sb.append(" and l.loginstate = '").append(ll.getLoginState()).append("'");
		}
		return sb.toString();
	}
	
	//此方法用于获取用户输入的查询车检信息的条件
	public static CarInfo getInputFindCarInfo(HttpServletRequest request) {
		//获取用户页面输入的查询条件
		String carNumber = request.getParameter("carNumber");
		String carStyle = request.getParameter("carStyle");
		String userName = request.getParameter("userName");
		String phoneNum = request.getParameter("phoneNum");
		String carYearCheck = request.getParameter("carYearCheck");
		String technologyLevel = request.getParameter("technologyLevel");
		String towLevel = request.getParameter("towLevel");
		String compulsoryInsurance = request.getParameter("compulsoryInsurance");
		String notice = request.getParameter("notice");
		//创建一个CarInfo对象用于封装查询条件
		CarInfo ci = new CarInfo();
		ci.setCarNumber(carNumber);
		ci.setCarStyle(carStyle);
		ci.setUserName(userName);
		ci.setPhoneNum(phoneNum);
		ci.setCarYearCheck(stringToDate(carYearCheck, "yyyy-MM-dd"));
		ci.setTechnologyLevel(stringToDate(technologyLevel, "yyyy-MM-dd"));
		ci.setTowLevel(stringToDate(towLevel, "yyyy-MM-dd"));
		ci.setCompulsoryInsurance(stringToDate(compulsoryInsurance, "yyyy-MM-dd"));
		ci.setNotice(notice);
		return ci;
	}
	
	//此方法用于拼接查询车检信息的SQL语句(子查询)
	public static String createSelectCarInfoSql(CarInfo ci) {
		StringBuffer sql = new StringBuffer("select rownum r, c.* from carinfo c where 1=1 ");
		if(ci.getCarNumber() != null && ci.getCarNumber().length() > 0) {
			sql.append(" and c.carnumber like '%").append(ci.getCarNumber()).append("%'");
		}
		if(ci.getCarStyle() != null && ci.getCarStyle().length() > 0) {
			sql.append(" and c.carstyle = '").append(ci.getCarStyle()).append("'");
		}
		if(ci.getUserName() != null && ci.getUserName().length() > 0) {
			sql.append(" and c.username like '%").append(ci.getUserName()).append("%'");
		}
		if(ci.getCarYearCheck() != null) {
			sql.append(" and to_char(c.caryearcheck,'yyyy-MM-dd') = '").append(Tools.dateToString(ci.getCarYearCheck(), "yyyy-MM-dd")).append("'");
		}
		if(ci.getTechnologyLevel() != null) {
			sql.append(" and to_char(c.technologylevel,'yyyy-MM-dd') = '").append(Tools.dateToString(ci.getTechnologyLevel(), "yyyy-MM-dd")).append("'");
		}
		if(ci.getTowLevel() != null) {
			sql.append(" and to_char(c.towlevel,'yyyy-MM-dd') = '").append(Tools.dateToString(ci.getTowLevel(), "yyyy-MM-dd")).append("'");
		}
		if(ci.getCompulsoryInsurance() != null) {
			sql.append(" and to_char(c.compulsoryinsurance,'yyyy-MM-dd') = '").append(Tools.dateToString(ci.getCompulsoryInsurance(), "yyyy-MM-dd")).append("'");
		}
		if(ci.getPhoneNum() != null && ci.getPhoneNum().length() > 0) {
			sql.append(" and c.phonenum = '").append(ci.getPhoneNum()).append("'");
		}
		if(ci.getNotice() != null && ci.getNotice().length() > 0) {
			sql.append(" and c.notice = '").append(ci.getNotice()).append("'");
		}
		return sql.toString();
	}
	
	//此方法用于拼接查询车检信息总条数的SQL语句
	public static String createSelectCarInfoTotal(CarInfo ci) {
		StringBuffer sql = new StringBuffer("select count(*) from carinfo where 1=1 ");
		if(ci.getCarNumber() != null && ci.getCarNumber().length() > 0) {
			sql.append(" and carnumber like '%").append(ci.getCarNumber()).append("%'");
		}
		if(ci.getCarStyle() != null && ci.getCarStyle().length() > 0) {
			sql.append(" and carstyle = '").append(ci.getCarStyle()).append("'");
		}
		if(ci.getUserName() != null && ci.getUserName().length() > 0) {
			sql.append(" and username like '%").append(ci.getUserName()).append("%'");
		}
		if(ci.getCarYearCheck() != null) {
			sql.append(" and to_char(caryearcheck,'yyyy-MM-dd') = '").append(Tools.dateToString(ci.getCarYearCheck(), "yyyy-MM-dd")).append("'");
		}
		if(ci.getTechnologyLevel() != null) {
			sql.append(" and to_char(technologylevel,'yyyy-MM-dd') = '").append(Tools.dateToString(ci.getTechnologyLevel(), "yyyy-MM-dd")).append("'");
		}
		if(ci.getTowLevel() != null) {
			sql.append(" and to_char(towlevel,'yyyy-MM-dd') = '").append(Tools.dateToString(ci.getTowLevel(), "yyyy-MM-dd")).append("'");
		}
		if(ci.getCompulsoryInsurance() != null) {
			sql.append(" and to_char(compulsoryinsurance,'yyyy-MM-dd') = '").append(Tools.dateToString(ci.getCompulsoryInsurance(), "yyyy-MM-dd")).append("'");
		}
		if(ci.getPhoneNum() != null && ci.getPhoneNum().length() > 0) {
			sql.append(" and phonenum = '").append(ci.getPhoneNum()).append("'");
		}
		if(ci.getNotice() != null && ci.getNotice().length() > 0) {
			sql.append(" and notice = '").append(ci.getNotice()).append("'");
		}
		return sql.toString();
	}
	//此方法用于拼接查询车检信息的SQL语句(分页查询)
	public static String createSelectCarInfoPage(CarInfo ci,int from,int to){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (").append(createSelectCarInfoSql(ci)).append(") t where t.r between ").append(from).append(" and ").append(to);
		return sql.toString();
	}
}
