<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.ltc.domain.Menus"%>
<%@page import="com.ltc.domain.Users"%>
<%@page import="com.ltc.commons.Constants"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>顶部菜单项</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	 
</script>
</head>

<body style="background:url(images/topbg.gif) repeat-x;">
    <div class="topleft">
		<a href="main.jsp" target="_parent"><img src="images/logo.png" title="系统首页" /></a>
    </div>
    <ul class="nav">
    	<%
    	Users user = (Users)session.getAttribute(Constants.USER_SESSION_KEY);
    	List<Menus> menuList = user.getMenuList();
    	%>
   		<%
    	for(int i = 0;i < menuList.size();i++) {
    		Menus menu = menuList.get(i);%>
    	  <%if(menu.getMenuID() == 131) {%>
    	<li>
			<a href="<%=menu.getMenuURL()%>" target="rightFrame">
				<img src="images/icon01.png" title="<%=menu.getMenuName()%>" />
				<h2><%=menu.getMenuName()%></h2>
			</a>
		</li>
    	   <%}%>
    	   <%if(menu.getMenuID() == 132) {%>
    	<li>
			<a href="<%=menu.getMenuURL()%>" target="rightFrame">
				<img src="images/icon03.png" title="<%=menu.getMenuName()%>" />
				<h2><%=menu.getMenuName()%></h2>
			</a>
		</li>
    	   <%}%>
    	   <%if(menu.getMenuID() == 133) {%>
    	<li>
			<a href="<%=menu.getMenuURL()%>" target="rightFrame">
				<img src="images/icon05.png" title="<%=menu.getMenuName()%>" />
				<h2><%=menu.getMenuName()%></h2>
			</a>
		</li>
    	   <%}%>
      <%}%>
    </ul>
    <div class="topright">    
		<ul>
			<li><span><img src="images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
			<li><a href="ExitSystemServlet.do" target="_parent">退出</a></li>
		</ul>
		<div class="user">
			<span><%=user.getUserName()%></span>
			<i><%=user.getRole().getRoleName()%></i>
		</div>  
    </div>
</body>
</html>