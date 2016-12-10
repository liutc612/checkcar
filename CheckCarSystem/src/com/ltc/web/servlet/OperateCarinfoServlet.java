package com.ltc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ltc.commons.Tools;
import com.ltc.domain.CarInfo;
import com.ltc.server.OperateCarinfoManager;
import com.ltc.server.impl.OperateCarinfoManagerImpl;

/**
 * 操作车检信息的Servlet
 * @author ltc
 * 2016-5-24 14:11:14
 */
public class OperateCarinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取指令信息
		String flag = request.getParameter("key");
		if("del".equals(flag)) {
			this.delCarInfo(request, response);
		}else if("pupd".equals(flag)) {
			this.preUpdateCarInfo(request, response);
		}else if("updnt".equals(flag)) {
			this.updateCarInfoNotice(request);
		}else {
			this.updateCarInfo(request, response);
		}
	}
	
	//删除车检信息
	public void delCarInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		//获取页面的删除条件
		String carNumber = request.getParameter("carNumber");
		//调用业务层进行删除
		OperateCarinfoManager ocm = new OperateCarinfoManagerImpl();
		ocm.delCarInfoService(carNumber);
		PrintWriter pw = response.getWriter();
		pw.println("删除成功");
		pw.flush();
		pw.close();
	}
	//更新用户预查询动作
	private void preUpdateCarInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取查询条件
		String carNumber = request.getParameter("carNumber");
		//调用业务层进行删除
		OperateCarinfoManager ocm = new OperateCarinfoManagerImpl();
		CarInfo ci = ocm.preUpdateCarInfoService(carNumber);
		request.setAttribute("CarInfo", ci);
		request.getRequestDispatcher("jsp_CCI/update_carinfo.jsp").forward(request, response);
	}
	//更新用户动作
	public void updateCarInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//获取更新的数据
		CarInfo ci = Tools.getInputFindCarInfo(request);
		//调用业务层进行删除
		OperateCarinfoManager ocm = new OperateCarinfoManagerImpl();
		ocm.updateCarInfoService(ci);
		response.sendRedirect("ok.jsp");
	}
	//更新通知
	public void updateCarInfoNotice(HttpServletRequest request) {
		//获取更新的数据
		CarInfo ci = Tools.getInputFindCarInfo(request);
		//调用业务层进行删除
		OperateCarinfoManager ocm = new OperateCarinfoManagerImpl();
		ocm.updateCarInfoNoticeService(ci);
	}
}
