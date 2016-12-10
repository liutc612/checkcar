<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" ttp://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改用户页面</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<style type="text/css">
	input,select {width:200px;height:30px;border-radius:3px;}
	table {margin:auto;width:40%;}
	td {font-size:16px;padding:4px;height:40px;}
	body {TEXT-ALIGN: center;}
	#d2 {MARGIN-RIGHT: auto;MARGIN-LEFT: auto;width:240px;}
</style>
<script>
	$(function(){
		findRoleAll();
	});
	function findRoleAll(){
		$.ajax({
			url:"AddUserServlet.do?key=fr",
			type:"post",
			dataType:"json",
			success:function(roleList){
				init(roleList);	
			}
		});
	};
	function init(roleList){
		var str = "";
		$.each(roleList,function(){
			str += "<option value="+$(this).attr("roleID")+" id="+$(this).attr("roleID")+">"+$(this).attr("roleName")+"</option>"
		});
		$("#sel").append(str);
	};
	//提交按钮
	function subForm(){
		document.forms[0].submit();
	};
</script>
</head>

<body background="images/bgimg.png">
	<form action="OperateUserServlet.do?key=upd" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>首页</li>
				<li>修改用户</li>
			</ul>
	    </div>
		<!-- 添加内容 -->
		<div>
			<table>
				<tr>
					<td align="right">用户名</td>
					<td align="center"><input type="text" value="${requestScope.user.userName}" readonly="readonly" name="userName" id="userName" onfocus="nick_input()" onblur="nick_out()"/></td>
					<td width="160">
						<span id="unMsg"></span>
					</td>
				</tr>
				<tr>
					<td align="right">年龄</td>
					<td align="center"><input type="text" name="userAge" value="${requestScope.user.userAge}"/></td>
					<td>
						<span id="uaMsg"></span>
					</td>
				</tr>
				<tr>
					<td align="right">性别</td>
					<td align="center">
						<script type="text/javascript">
							window.onload = function() {
								document.getElementById('${requestScope.user.userSex}').selected = true;
							};
						</script>
						<select name="userSex">
							<option value="M" id="M">男</option>
							<option value="F" id="F">女</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">角色</td>
					<td align="center">
						<select name="userRole" id="sel">
						
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">地址</td>
					<td align="center"><input type="text" name="userAddr" value="${requestScope.user.address}"/></td>
				</tr>
				<tr>
					<td align="right">手机号码</td>
					<td align="center"><input type="text" name="phoneNum" value="${requestScope.user.phoneNum}"/></td>
				</tr>
			</table>
		</div>
		<div class="rightinfo" id="d2">
			<div class="tools">
				<ul class="toolbar">
					<li onclick="subForm()"><span><img src="images/t01.png" /></span>提交</li>
					<li onclick=""><span><img src="images/t02.png" /></span>重置</li>
				</ul>
			</div>
	    </div>
	</form>    
</body>
</html>