package com.ltc.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ltc.commons.ParseExcel;
import com.ltc.domain.CarInfo;
import com.ltc.server.OperateDataManager;
import com.ltc.server.impl.OperateDataManagerImpl;

/**
 * 数据管理的servlet
 * @author ltc 
 * 2016-5-21 13:45:21
 */
public class OperateDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取URI信息
		String flag = request.getParameter("key");
		if("bd".equals(flag)) {
			this.backupData(request, response);
		}else if("rd".equals(flag)) {
			String[] arr = this.findBackupFile();
			request.setAttribute("arr", arr);
			request.getRequestDispatcher("jsp_DM/recovery_data.jsp").forward(request, response);
		}else if("ie".equals(flag)){
			this.importExcel(request, response);
		}else if("dd".equals(flag)) {
			String[] arr = this.findBackupFile();
			request.setAttribute("arr", arr);
			request.getRequestDispatcher("jsp_DM/download_data.jsp").forward(request, response);
		}else if("dl".equals(flag)){
			this.dowmloadFile(request, response);
		}else {
			List<CarInfo> list = this.uploadFile(request);
			OperateDataManager odm = new OperateDataManagerImpl();
			try {
				odm.importExcel(list);
				response.sendRedirect("ok.jsp");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//将数据备份到excel文件中
	private void backupData(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//相对路径转绝对路径
		ServletContext sc = this.getServletContext();
		String realPath = sc.getRealPath("backupfile");
		//调用业务层进行数据备份
		OperateDataManager odm = new OperateDataManagerImpl();
		odm.backupDataServer(realPath);
		response.sendRedirect("ok.jsp");
	}
	//查询备份文件
	private String[] findBackupFile(){
		ServletContext sc = this.getServletContext();
		String realPath = sc.getRealPath("backupfile");
		File file = new File(realPath);
		String[] arr = file.list();
		return arr;
	}
	//将特定的excel文件导入到数据库中
	private void importExcel(HttpServletRequest request, HttpServletResponse response){
		String fileName = request.getParameter("fileName");
		ServletContext sc = this.getServletContext();
		String realPath = sc.getRealPath("backupfile/"+fileName);
		OperateDataManager odm = new OperateDataManagerImpl();
		try {
			odm.importExcel(realPath);
			response.sendRedirect("ok.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//下载数据文件
	private void dowmloadFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("bin");
		String fileName = request.getParameter("fileName");
		response.addHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		ServletContext sc = this.getServletContext();
		String realPath = sc.getRealPath("backupfile/"+fileName);
		InputStream is = new FileInputStream(realPath);
		OutputStream os = response.getOutputStream();
		byte[] buff = new byte[is.available()];
		int len = is.read(buff);
		while(len != -1){
			os.write(buff);
			len = is.read(buff, 0, len);
		}
		os.flush();
		os.close();
		is.close();
	}
	
	//导入Excel文件
	private List<CarInfo> uploadFile(HttpServletRequest request){
		List<CarInfo> ciList = null;
		//创建磁盘工厂
		DiskFileItemFactory dif = new DiskFileItemFactory();
		//创建解析器
		ServletFileUpload sfu = new ServletFileUpload(dif);
		//将request对象交给解析器去解析
		try {
			List<FileItem> list = sfu.parseRequest(request);
			//处理解析结果。挑选出你要内容
			for(FileItem f:list){
				//判断文件
				if(!f.isFormField()){
					//获取扩展名
					String str = f.getName().substring((f.getName().lastIndexOf("."))+1);
					//用ParseExcel读取文件获得List
					if(("xls".equals(str) || "xlsx".equals(str)) && str.length() > 0) {
						ParseExcel p = new ParseExcel();
						ciList = p.createCheckCarExcel(f.getInputStream(), str);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ciList;
	}
}
