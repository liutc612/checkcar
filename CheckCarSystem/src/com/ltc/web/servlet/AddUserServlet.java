package com.ltc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ltc.commons.Constants;
import com.ltc.commons.Tools;
import com.ltc.domain.Roles;
import com.ltc.domain.Users;
import com.ltc.exception.ApplicationException;
import com.ltc.server.AddUserManager;
import com.ltc.server.impl.AddUserManagerImpl;

import net.sf.json.JSONArray;

/**
 * 添加用户的servlet
 * @author ltc 2016-5-19 10:13:12
 */
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//判断URI信息
			String flag = request.getParameter("key");
			//添加前页面加载先查询角色信息
			if("fr".equals(flag)) {
				//添加前页面加载先查询角色信息
				this.findRoleAll(response);
			}else {
				//确认提交添加用户的请求。调用添加用户的方法
				this.addUser(request, response);
			}
			//添加用户前的角色预查询
			//List<Roles> list = this.findRoleAll();
		}catch(ApplicationException e){
			//e.printStackTrace();
			String msg = e.getMessage();
			request.setAttribute(Constants.MSG_REQUEST_KEY, msg);
			request.getRequestDispatcher("jsp_SM/add_user.jsp").forward(request, response);
		}catch (Exception e) {
			//e.printStackTrace();
			response.sendRedirect("err_page.jsp");
		}
	}
	
	//此方法用于添加用户前的角色预查询
	private void findRoleAll(HttpServletResponse response) throws IOException {
		//调用业务层查询角色信息
		AddUserManager aum = new AddUserManagerImpl();
		List<Roles> list = aum.findRoleAllServer();
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		JSONArray arr = JSONArray.fromObject(list);
		pw.println(arr.toString());
		pw.flush();
		pw.close();
	}
	//此方法用于实现添加用户
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取页面输入的用户信息
		Users user = Tools.getUserInputInfo(request);
		//调用业务层添加用户
		AddUserManager aum = new AddUserManagerImpl();
		aum.addUserServer(user);
		//添加成功跳转
		request.setAttribute("addok", "添加成功");
		response.sendRedirect("jsp_SM/add_user.jsp");
	}
}
