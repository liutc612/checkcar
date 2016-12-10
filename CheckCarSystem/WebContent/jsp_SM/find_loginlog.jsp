<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询登录日志页面</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
	#seltab input {width:200px;height:30px;border-radius:3px;}
	#seltab {margin:auto;width:40%;}
	#seltab td {font-size:16px;padding:4px;height:40px;}
</style>
<script>
	//查询日志按钮--------------------------------------------------------------------------------------
	function findLoginLog(){
		document.forms[0].submit();
	};
</script>
</head>

<body background="../images/bgimg.png">	
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>日志查询</li>
		</ul>
    </div>
	
	<div>
		<!-- 添加查询条件的form表单 -->
		<form id="fm1" action="../FindLoginlogServlet.do" method="post">
			<table id="seltab">
				<tr>
					<td align="right">用户名</td>
					<td align="center"><input type="text" name="loginName"/></td>
					<td width="130"></td>
				</tr>
				<tr>
					<td align="right">登录状态</td>
					<td align="center"><input type="text" name="loginState"/></td>
				</tr>
				<tr>
					<td align="right">时间</td>
					<td align="center"><input type="text" name="loginDate" onfocus="WdatePicker({isShowClear:false,readOnly:true})"/></td>
				</tr>
				<tr>
					<td align="right" width=100px;>IP地址</td>
					<td align="center"><input type="text" name="loginIP"/></td>
				</tr>
			</table>
		</form>
		<div class="rightinfo">
			<div class="tools" style="margin:auto;width:10%;">
				<ul class="toolbar">
					<li onclick="findLoginLog()"><span><img src="../images/t04.png" /></span>查询</li>
				</ul>
			</div>
	    </div>
	</div>
</body>
</html>