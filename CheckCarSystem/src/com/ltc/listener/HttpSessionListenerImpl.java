package com.ltc.listener;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.ltc.commons.Constants;
import com.ltc.domain.LoginLog;
import com.ltc.domain.Users;
import com.ltc.server.UserLoginManager;
import com.ltc.server.impl.UserLoginManagerImpl;
/**
 * session销毁的监听器
 */
public class HttpSessionListenerImpl implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent arg0) {

	}
	//此方法的作用是：当客户端浏览器的session失效时，立即触发，然后删除ServletContext中用户所对应的session
	public void sessionDestroyed(HttpSessionEvent arg0) {
		//获取ServletContext
		HttpSession session = arg0.getSession();
		ServletContext sc = session.getServletContext();
		//从session获取用户信息
		Users user = (Users)session.getAttribute(Constants.USER_SESSION_KEY);
		//如果user不为空说明用户登录过，但是session失效了，ServletContext中删除用户所对应的session
		if(user != null && user.getUserName().length() > 0) {
			sc.removeAttribute(user.getUserName());
		}
		//记录退出系统的日志
		this.addExitLoginLog(session, user);
	}
	//此方法用来监听用户退出登录，做退出登录的日志记录
	private void addExitLoginLog(HttpSession session,Users user) {
		LoginLog ll = new LoginLog();
		if(user.getUserName() != null && user.getUserName().length() > 0) {
			ll.setLoginName(user.getUserName());
		}
		ll.setLoginIP((String)session.getAttribute("IP"));
		ll.setLoginState("0");
		ll.setLoginDate(new Date());
		//调用业务层进行日志记录
		UserLoginManager ulm = new UserLoginManagerImpl();
		ulm.addLoginLog(ll);
	}
}
