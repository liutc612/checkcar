package com.ltc.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ltc.commons.Tools;
import com.ltc.domain.CarInfo;
import com.ltc.server.AddCarinfoManager;
import com.ltc.server.impl.AddCarinfoManagerImpl;

/**
 * 添加车检信息的servlet
 * @author ltc 2016-5-22 14:18:20
 */
public class AddCarinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户页面输入的车检信息
		CarInfo ci = Tools.getInputFindCarInfo(request);
		//调用业务层添加车检信息
		AddCarinfoManager acm = new AddCarinfoManagerImpl();
		acm.addCarInfoServer(ci);
		response.sendRedirect("ok.jsp");
	}

}
