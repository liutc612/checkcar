package com.ltc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ltc.domain.Roles;
import com.ltc.server.OperateRoleManager;
import com.ltc.server.impl.OperateRoleManagerImpl;

/**
 * 角色管理的Servlet
 * @author ltc
 * 2016-5-27 11:01:40
 */
public class OperateRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取URI信息
		String flag = request.getParameter("key");
		if("fr".equals(flag)) {
			this.findRole(request, response);
		}else if("del".equals(flag)) {
			this.deleteRole(request, response);
		}
	}
	
	//查询角色信息的方法
	private void findRole(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		//获取页面的查询条件
		String roleName = request.getParameter("roleName");
		//调用业务层查询角色
		OperateRoleManager orm = new OperateRoleManagerImpl();
		List<Roles> list = orm.findRoleService(roleName);
		request.setAttribute("list", list);
		request.getRequestDispatcher("jsp_SM/show_role.jsp").forward(request, response);
	}
	//删除角色信息的方法
	private void deleteRole(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		//获取页面的删除条件
		String roleID = request.getParameter("roleID");
		//调用业务层删除角色
		OperateRoleManager orm = new OperateRoleManagerImpl();
		orm.deleteRoleService(Long.parseLong(roleID));
		PrintWriter pw = response.getWriter();
		pw.println("删除成功");
		pw.flush();
		pw.close();
	}
}
