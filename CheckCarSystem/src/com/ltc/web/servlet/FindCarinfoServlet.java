package com.ltc.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ltc.commons.Tools;
import com.ltc.domain.CarInfo;
import com.ltc.domain.Page;
import com.ltc.server.FindCarinfoManager;
import com.ltc.server.impl.FindCarinfoManagerImpl;

import net.sf.json.JSONArray;

/**
 * 查询车检信息的Servlet
 * @author ltc
 * 2016-5-22 16:10:52
 */
public class FindCarinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("key");
		if("fci".equals(flag)) {
			this.findCarInfo(request, response);
		}else {
			this.matchCarNum(request, response);
		}
	}
	//查询车检信息
	private void findCarInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取用户页面输入的查询车检信息条件
		CarInfo ci = Tools.getInputFindCarInfo(request);
		String index = request.getParameter("indexPage");
		int pageIndex =1;
		if(index != null && index.length() > 0){
			pageIndex = Integer.parseInt(index);
		}
		//调用业务层查询车检信息
		FindCarinfoManager fcm = new FindCarinfoManagerImpl();
		Page p = fcm.findCarInfoServer(ci,pageIndex);
		request.setAttribute("page", p);
		request.setAttribute("CarInfo", ci);
		request.getRequestDispatcher("jsp_CCI/show_carinfo.jsp").forward(request, response);
	}
	//添加车检信息是匹配车牌号码
	private void matchCarNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain;charset=UTF-8");
		//调用业务层查询车牌号码信息
		FindCarinfoManager fcm = new FindCarinfoManagerImpl();
		List<String> list = fcm.findCarNumberServer();
		JSONArray arr = JSONArray.fromObject(list);
		PrintWriter pw = response.getWriter();
		pw.println(arr.toString());
		pw.flush();
		pw.close();
	}
}
