package com.ltc.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ltc.domain.Page;
import com.ltc.server.FindHomeDataManager;
import com.ltc.server.impl.FindHomeDataManagerImpl;

/**
 * 此servlet控制器用于查询首页展示的即将到期的数据项
 *@author ltc 2016-5-20 14:34:33
 *
 */
public class FindHomeDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=UTF-8");
		//调用业务层查询即将到期的数据
		FindHomeDataManager fhdm = new FindHomeDataManagerImpl();
		String index = request.getParameter("indexPage");
		int pageIndex =1;
		if(index != null && index.length() > 0){
			pageIndex = Integer.parseInt(index);
		}
		Page p = fhdm.findHomeDataServer(pageIndex);
		request.setAttribute("page", p);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
