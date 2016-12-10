package com.ltc.web.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ltc.commons.Constants;
import com.ltc.domain.LoginLog;
import com.ltc.domain.Users;
import com.ltc.server.UserLoginManager;
import com.ltc.server.impl.UserLoginManagerImpl;
/**
 * 记录用户登录日志的过滤器
 * @author ltc 2016-5-19 09:17:27
 *
 */
public class LoginLogFilter implements Filter {
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		//放行
		arg2.doFilter(arg0, arg1);
		//判断登录是否成功（依据：session中有无Users对象）
		//获取session并判断有无Users
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute(Constants.USER_SESSION_KEY);
		if(user != null && user.getUserName().length() > 0) {
			//登录成功，记录日志
			LoginLog ll = this.createLoginLog(request, user);
			//调用业务层进行日志记录
			UserLoginManager ulm = new UserLoginManagerImpl();
			ulm.addLoginLog(ll);
		}
	}
	//获取登录信息的方法
	private LoginLog createLoginLog(HttpServletRequest request,Users user) {
		LoginLog ll = new LoginLog();
		ll.setLoginName(user.getUserName());
		ll.setLoginIP(request.getRemoteAddr());
		ll.setLoginDate(new Date());
		ll.setLoginState("1");
		return ll;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
