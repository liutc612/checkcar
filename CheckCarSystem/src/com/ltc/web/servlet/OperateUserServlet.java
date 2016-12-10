package com.ltc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ltc.commons.Tools;
import com.ltc.domain.Users;
import com.ltc.server.OperateUserManager;
import com.ltc.server.impl.OperateUserManagerImpl;

/**
 * 操作用户的Servlet
 * @author ltc
 * 2016-5-24 09:40:28
 */
public class OperateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取指令信息
		String flag = request.getParameter("key");
		if("del".equals(flag)) {
			this.delUser(request, response);
		}else if("pupd".equals(flag)) {
			this.preUpdateUser(request, response);
		}else {
			this.updateUser(request, response);
		}
	}
	
	//删除用户动作
	private void delUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		//获取页面的删除条件
		String userName = request.getParameter("userName");
		//调用业务层进行删除
		OperateUserManager oum = new OperateUserManagerImpl();
		oum.delUserService(userName);
		PrintWriter pw = response.getWriter();
		pw.println("删除成功");
		pw.flush();
		pw.close();
	}
	//更新用户预查询动作
	private void preUpdateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取查询条件
		String userName = request.getParameter("userName");
		//调用业务层进行删除
		OperateUserManager oum = new OperateUserManagerImpl();
		Users user = oum.preUpdateUserService(userName);
		request.setAttribute("user", user);
		request.getRequestDispatcher("jsp_SM/update_user.jsp").forward(request, response);
	}
	//更新用户动作
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取更新的数据
		Users user = Tools.getUserInputInfo(request);
		String userRole =  request.getParameter("userRole");
		if(userRole != null && userRole.length() > 0) {
			user.getRole().setRoleID(Integer.parseInt(userRole));
		}
		//调用业务层进行删除
		OperateUserManager oum = new OperateUserManagerImpl();
		oum.updateUserService(user);
		response.sendRedirect("ok.jsp");
	}
}
