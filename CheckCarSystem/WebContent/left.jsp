<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.ltc.domain.Menus"%>
<%@page import="com.ltc.domain.Users"%>
<%@page import="com.ltc.commons.Constants"%>
<%@page import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>左侧菜单项</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>


</head>

<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>菜单列表</div>
    <dl class="leftmenu">
    	<%
    	Users user = (Users)session.getAttribute(Constants.USER_SESSION_KEY);
    	List<Menus> menuList = user.getMenuList();
    	%>
	    <dd>
		    <div class="title">
		   		<span><img src="images/leftico01.png" /></span>系统管理
		    </div>
	    	<ul class="menuson">
	    		<%
	    		for(int i = 0;i < menuList.size();i++) {
	    			Menus menu = menuList.get(i);
	    			if(menu.getFatherID() == 11) {
	    		%>
	    		<li><cite></cite><a href="jsp_SM/<%=menu.getMenuURL()%>" target="rightFrame"><%=menu.getMenuName()%></a><i></i></li>
	    	  	  <%} %>
	    	  <%}%>
	        </ul>    
	    </dd>
	    <dd>
		    <div class="title">
		    	<span><img src="images/leftico03.png" /></span>车检信息管理
		    </div>
		    <ul class="menuson">
		        <%
	    		for(int i = 0;i < menuList.size();i++) {
	    			Menus menu = menuList.get(i);
	    			if(menu.getFatherID() == 12) {
	    		%>
	    		<li><cite></cite><a href="jsp_CCI/<%=menu.getMenuURL()%>" target="rightFrame"><%=menu.getMenuName()%></a><i></i></li>
	    	  	  <%} %>
	    	  <%}%>
		    </ul>    
	    </dd> 
    </dl>
</body>
</html>