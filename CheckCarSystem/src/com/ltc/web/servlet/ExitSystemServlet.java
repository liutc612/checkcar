package com.ltc.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 退出登录的控制器 	2016-5-18 23:21:52
 */
public class ExitSystemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//退出登录，删除用户的session信息
		HttpSession session = request.getSession();
		session.invalidate();
		//重定向到用户登录页面
		response.sendRedirect("login.jsp");
	}
}
