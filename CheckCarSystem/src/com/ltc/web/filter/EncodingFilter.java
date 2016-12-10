package com.ltc.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 * 设置编码的过滤器
 */
public class EncodingFilter implements Filter {
	//设置一个默认的编码格式
	private String encode = "utf-8";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(this.encode);
		//一定要放行
		chain.doFilter(request, response);
	}
	
	//初始化编码格式
	public void init(FilterConfig filterConfig) throws ServletException {
		String encode = filterConfig.getInitParameter("encoding");
		if(encode != null && encode.length() > 0) {
			this.encode = encode;
		}
	}

	public void destroy() {
		
	}

}
