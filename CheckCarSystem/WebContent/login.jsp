<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车检信息管理系统</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<script src="js/cloud.js" type="text/javascript"></script>
<style>
	.loginbody{margin-top: -60px;}
</style>
<script language="javascript">
	$(function(){
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){  
	    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	    })  
	});  
</script> 
<!-- 判断当前窗口有无父窗口 -->
<script type="text/javascript">
	if(window.parent.length > 0){
		window.parent.location = "login.jsp";
	}
</script>
<!-- 重新获取验证码 -->
<script type="text/javascript">
	function reloadValidateCode(){
		document.getElementById("img").src="ValidateCodeServlet.do?flag="+Math.random();
	}
</script>

</head>

<body style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">
    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  

	<div class="logintop">    
    	<span>欢迎登录车检信息管理系统</span>    
    </div>
    
    <div class="loginbody">
		<span class="systemlogo"></span> 
		<div class="loginbox loginbox1">
			<form action="UserLoginServlet.do" method="post">
				<ul>
					<li><input name="userName" type="text" value="${requestScope.userName}" class="loginuser"/></li>
					<li><input name="userPwd" type="password" value="${requestScope.userPwd}" class="loginpwd"/></li>
					<li class="yzm">
					<span><input name="validate" type="text"/></span><img id="img" src="ValidateCodeServlet.do" onclick="reloadValidateCode()"/> 
					</li>
					<li><input type="submit" class="loginbtn" value="登录"/><label><input name="" type="checkbox" value="" checked="checked" />记住密码</label><label><a href="#">忘记密码？</a></label></li>
					<li><font color="red">${requestScope.msg}</font></li>
				</ul>
			</form>
		</div>
    </div>
    
    <div class="loginbm">版权所有  2016  liutc612@gmail.com 仅供学习交流，勿用于任何商业用途</div>
</body>

</html>