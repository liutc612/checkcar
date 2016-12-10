package com.ltc.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ltc.commons.Tools;
import com.ltc.domain.Users;
import com.ltc.exception.ApplicationException;
import com.ltc.server.FindUserManager;
import com.ltc.server.impl.FindUserManagerImpl;

/**
 * 查询用户的servlet
 * @author ltc 2016-5-19 20:25:32
 */
public class FindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//获取uri信息
			String flag = request.getParameter("key");
			if("fu".equals(flag)){
				//查询用户
				this.findUser(request, response);
			}
		}catch(ApplicationException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//查询用户(模糊查询)
	private void findUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		response.setContentType("text/plain;charset=UTF-8");
		//获取页面输入的查询条件
		Users user = Tools.getUserInputInfo(request);
		//获取当前页
//		String index = request.getParameter("indexPage");
//		int pageIndex = 1;
//		if(index != null && index.length() > 0){
//			pageIndex = Integer.parseInt(index);
//		}
		//调用业务层查询用户
		FindUserManager fum = new FindUserManagerImpl();
		List<Users> list = fum.findUserServer(user);
		request.setAttribute("list", list);
		request.getRequestDispatcher("jsp_SM/show_user.jsp").forward(request, response);
	}
}
