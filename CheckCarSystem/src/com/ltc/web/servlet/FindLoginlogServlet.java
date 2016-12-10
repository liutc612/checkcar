package com.ltc.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ltc.commons.Tools;
import com.ltc.domain.LoginLog;
import com.ltc.domain.Page;
import com.ltc.server.FindLoginlogManager;
import com.ltc.server.impl.FindLoginlogManagerImpl;

/**
 * 查询登录日志的servlet
 * @author ltc 2016-5-22 09:53:54 
 */
public class FindLoginlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户页面输入的查询登录日志的条件
		LoginLog ll = Tools.getFindLoginLogInfo(request);
		//调用业务层进行查询
		FindLoginlogManager flm= new FindLoginlogManagerImpl();
		String index = request.getParameter("indexPage");
		int pageIndex =1;
		if(index != null && index.length() > 0){
			pageIndex = Integer.parseInt(index);
		}
		Page p = flm.findLoginlogServer(ll, pageIndex);
		request.setAttribute("page", p);
		request.setAttribute("loginlog", ll);
		request.getRequestDispatcher("jsp_SM/show_loginlog.jsp").forward(request, response);
	}

}
