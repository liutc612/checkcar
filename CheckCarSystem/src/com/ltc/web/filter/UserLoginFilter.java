package com.ltc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ltc.commons.Constants;
import com.ltc.domain.Users;
/**
 * 用户登录的过滤器
 * @author ltc 
 * 2016-5-18
 */
public class UserLoginFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		//查看用户访问的资源是不是用户登录所使用的资源，是则放行，不是则无法登录
		HttpServletRequest request = (HttpServletRequest)arg0;
		//从URI中获取用户访问的资源
		String uri = request.getRequestURI();
		//判断访问的是否是完成登录的资源
		if(uri.indexOf("login.jsp") != -1 || uri.indexOf("UserLoginServlet.do") != -1 || uri.indexOf("ValidateCodeServlet.do") != -1) {
			//是则放行
			arg2.doFilter(arg0, arg1);
		}else {
			//不是则过滤
			//看session中有无用户信息
			HttpSession session = request.getSession();
			Users user = (Users)session.getAttribute(Constants.USER_SESSION_KEY);
			if(user != null && user.getUserName().length() > 0) {
				//如果登录过则放行进入主页
				arg2.doFilter(arg0, arg1);
			}else {
				//不是则不能进入主页，跳转到登录页面
				request.setAttribute(Constants.MSG_REQUEST_KEY, "请先登录");
				//请求转发到登录页面
				request.getRequestDispatcher("login.jsp").forward(arg0, arg1);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
