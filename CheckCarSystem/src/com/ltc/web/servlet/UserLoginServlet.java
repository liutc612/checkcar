package com.ltc.web.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ltc.commons.Constants;
import com.ltc.commons.MD5;
import com.ltc.domain.Users;
import com.ltc.exception.ApplicationError;
import com.ltc.exception.ApplicationException;
import com.ltc.exception.UserNotFoundException;
import com.ltc.server.UserLoginManager;
import com.ltc.server.impl.UserLoginManagerImpl;

/**
 * 用户登录的servlet
 * @author ltc 
 * 2016-05-18
 */
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取登录页面的用户名和密码
		String userName = request.getParameter("userName");
		//密码需要加密处理
		String userPwd = MD5.getMD5(request.getParameter("userPwd"));
		//取出验证码
		String validate = request.getParameter("validate");
		//获取session对象
		HttpSession session  = request.getSession();
		//取出服务端提供给用户的验证码
		String serverValidate = (String)session.getAttribute(Constants.VALIDATE_CODE_KEY);
		try {
			//先判断验证码是否失效
			if(serverValidate == null) {
				//验证码失效，做提示
				request.setAttribute("msg", "验证码已过期，请重新获取验证码");
				request.getRequestDispatcher("user_login.jsp").forward(request, response);
			}else {
				//先判断验证码是否正确
				if(serverValidate.equalsIgnoreCase(validate)) {
					//实现登录功能
					//调用业务层判断用户名和密码是否正确
					UserLoginManager ulm = new UserLoginManagerImpl();
					Users user = ulm.userLogin(userName, userPwd);
					//登录前查看ServletContext中有无用户的session信息
					//获取ServletContext
					ServletContext sc = this.getServletContext();
					//查看ServletContext中有无用户的session信息
					HttpSession ses = (HttpSession)sc.getAttribute(user.getUserName());
					if(ses != null) {
						//有则删除session信息
						ses.invalidate();
					}
					//登录成功跳转页面到主页
					//1.1：登录成功后，记得把这个登录成功的user对象放入到他所对应的Httpsession中
					session.setAttribute(Constants.USER_SESSION_KEY, user);
					//将这个用户的session存到ServletContext全局上下文中
					sc.setAttribute(user.getUserName(), session);
					//将用户登录的IP地址放入session中
					session.setAttribute("IP", request.getRemoteAddr());
					//1.2：跳转到主页
					response.sendRedirect("main.jsp");
				}else {
					//验证码不正确则提示
					request.setAttribute(Constants.MSG_REQUEST_KEY, "验证码有误");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
			}
		}catch(UserNotFoundException e) {
			//e.printStackTrace();
			request.setAttribute(Constants.MSG_REQUEST_KEY, e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}catch(ApplicationException e) {
			//e.printStackTrace();
			request.setAttribute(Constants.MSG_REQUEST_KEY, e.getMessage());
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}catch(ApplicationError e) {
			e.printStackTrace();
			response.sendRedirect("err_page.jsp");
		}
	}
}
