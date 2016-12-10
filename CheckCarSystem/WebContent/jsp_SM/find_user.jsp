<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询用户页面</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<style type="text/css">
	#seltab input,select {width:200px;height:30px;border-radius:3px;}
	#seltab {margin:auto;width:55%;}
	#seltab td {font-size:16px;padding:4px;height:40px;}
	/*body {TEXT-ALIGN: center;}*/
	#d2 {MARGIN-RIGHT: auto;MARGIN-LEFT: auto;width:200px;}
</style>
<script>
	//查询所有角色信息------------------------------------------------------------------------------
	$(function(){
		findRoleAll();
	});
	//ajax异步请求
	function findRoleAll(){
		$.ajax({
			url:"../AddUserServlet.do?key=fr",
			type:"post",
			dataType:"json",
			success:function(roleList){
				//调用方法将角色信息插入到表格
				initRole(roleList);	
			}
		});
	};
	//将角色信息插入到表格
	function initRole(roleList){
		var str = "";
		$.each(roleList,function(){
			str += "<option value="+$(this).attr("roleID")+">"+$(this).attr("roleName")+"</option>";
		});
		$("#sel").append(str);
	};
	
	//查询用户按钮--------------------------------------------------------------------------------------
	function findUser(){
		document.forms[0].submit();
	};
	
	//重置按钮--------------------------------------------------
	function cls(){ 
		var t = document.getElementsByTagName("INPUT"); 
		for (var i=0; i <t.length;i++){ 
			if (t[i].type=='text' | t[i].type=='password'){ 
				t[i].value="";//清空 
			} 
		}
	};
</script>
</head>

<body background="../images/bgimg.png">	
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>查询用户</li>
		</ul>
    </div>
	
	<div>
	
	<!-- 添加查询条件的form表单 -->
	<form id="fm1" action="../FindUserServlet.do?key=fu" method="post">
		<table id="seltab">
			<tr>
				<td align="right">用户名</td>
				<td align="center"><input type="text" name="userName"/></td>
				<td align="right">年龄</td>
				<td align="center"><input type="text" name="userAge"/></td>
			</tr>
			<tr>
				<td align="right">角色</td>
				<td align="center">
					<select name="userRole" id="sel">
						<option value=""></option>
						<!-- 查询得到的角色信息将写到这里 -->
					</select>
				</td>
				<td align="right">性别</td>
				<td align="center">
					<select name="userSex">
						<option value=""></option>
						<option value="M">男</option>
						<option value="F">女</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">地址</td>
				<td align="center"><input type="text" name="userAddr"/></td>
				<td align="right">手机号码</td>
				<td align="center"><input type="text" name="phoneNum"/></td>
			</tr>
		</table>
	</form>
	</div>
    
    <div class="rightinfo">
		<div class="tools" style="margin:auto;width:20%;">
			<ul class="toolbar">
				<li onclick="findUser()"><span><img src="../images/t04.png" /></span>查询</li>
				<li onclick="cls()"><span><img src="../images/t03.png" /></span>重置</li>
			</ul>
		</div>
    </div>
</body>
</html>