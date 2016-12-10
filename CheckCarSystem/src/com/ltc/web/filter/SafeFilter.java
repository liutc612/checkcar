package com.ltc.web.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ltc.commons.Constants;
import com.ltc.domain.Funcs;
import com.ltc.domain.Users;
/**
 * 权限过滤器
 */
public class SafeFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,	FilterChain chain) throws IOException, ServletException {
		//获取请求的URI
		String uri = ((HttpServletRequest)request).getRequestURI();
		//判断是否是要登录
		if(uri.indexOf("login.jsp") != -1 || uri.indexOf("UserLoginServlet.do") != -1 || uri.indexOf("ValidateCodeServlet.do") != -1) {
			//是登录则放行
			chain.doFilter(request, response);
		}else {
			//不是登录则做权限过滤
			//从session里取出user对象
			HttpSession session = ((HttpServletRequest)request).getSession();
			Users user = (Users)session.getAttribute(Constants.USER_SESSION_KEY);
			//从user对象中取出用户的功能
			List<Funcs> funcList = user.getFuncList();
			boolean flag = false;
			for(Funcs func : funcList) {
				//判断是否拥有此功能
				if(uri.indexOf(func.getFuncURL()) != -1) {
					//拥有此功能，放行
					flag = true;
					break;
				}
			}
			if(flag) {
				//放行
				chain.doFilter(request, response);
			}else {
				//无此功能，跳转到无权限的提示页面
				((HttpServletResponse)response).sendRedirect("norole.jsp");
			}
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
