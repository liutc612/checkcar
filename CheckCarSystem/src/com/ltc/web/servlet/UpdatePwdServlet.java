package com.ltc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ltc.commons.MD5;
import com.ltc.domain.Users;
import com.ltc.server.UpdatePwdManager;
import com.ltc.server.impl.UpdatePwdManagerImpl;

/**
 * 修改密码的servlet
 * @author ltc 2016-5-21 16:41:52
 */
public class UpdatePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("key");
		if("cop".equals(flag)) {
			this.checkOldPwd(request, response);
		}else {
			this.updatePwd(request, response);
		}
	}
	
	//验证旧密码
	private void checkOldPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取页面输入的旧密码
		String oldPwd = MD5.getMD5(request.getParameter("oldPwd"));
		String userName = request.getParameter("userName");
		//调用业务层验证旧密码
		UpdatePwdManager upm = new UpdatePwdManagerImpl();
		Users user = upm.findOldPwdServer(userName);
		if(!oldPwd.equals(user.getUserPwd())) {
			//旧密码不匹配
			response.setContentType("text/plain;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.println(1);
			pw.flush();
			pw.close();
		}
	}
	//修改密码
	private void updatePwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取页面输入的新密码
		String newPwd = MD5.getMD5(request.getParameter("newPwd"));
		String userName = request.getParameter("userName");
		//调用业务层修改密码
		UpdatePwdManager upm = new UpdatePwdManagerImpl();
		upm.updatePwdServer(userName, newPwd);
		response.setContentType("text/plain;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.println("修改成功");
		pw.flush();
		pw.close();
		//退出登录，删除用户的session信息
		HttpSession session = request.getSession();
		session.invalidate();
	}
}
