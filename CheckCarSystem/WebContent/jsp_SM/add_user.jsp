<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" ttp://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加用户页面</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<style type="text/css">
	input,select {width:200px;height:30px;border-radius:3px;}
	table {margin:auto;width:40%;}
	td {font-size:16px;padding:4px;height:40px;}
	body {TEXT-ALIGN: center;}
	#d2 {MARGIN-RIGHT: auto;MARGIN-LEFT: auto;width:240px;}
</style>
<script>
	//校验用户名
	function nick_input(){
		$("#unMsg").html("<font color='green'>请输入用户名</font>");
	}
	//校验内容是否合法
	function nick_out(){
		var nicknameRegex = /^[a-zA-Z]{6,15}$/;
		var nick = $("#userName").val();
		if(nick == null || nick.length<=0) {
			$("#unMsg").html("<font color='red'>用户名不能为空</font>");
			return;
		}
		if(!nicknameRegex.test(nick)) {
			$("#unMsg").html("<font color='red'>长度为6-15的字母组合</font>");
			return;
		}else{
			$("#unMsg").html("<font color='green'>用户名可用</font>");
		}
	}
	//校验密码
	//校验密码1
	function pwd1_input(){
		$("#up1Msg").html("<font color='green'>长度是6-15位的字母或数字</font>");
	}
	function pwd1_out(){
		var passwordRegex = /^[a-zA-Z0-9]{6,15}$/;
		var pwd1 = $("#userPwd").val();
		if(pwd1 == null || pwd1.length<=0) {
			$("#up1Msg").html("<font color='red'>密码不能为空</font>");
			return;
		}
		if(!passwordRegex.test(pwd1)) {
			$("#up1Msg").html("<font color='red'>密码格式不符</font>");
		}else{
			$("#up1Msg").html("<font color='green'>密码可用</font>");
		}
		}
	//校验密码2
	function pwd2_input(){
		$("#up2Msg").html("<font color='green'>长度是6-15位的字母或数字</font>");
	}
	function pwd2_out(){
		var passwordRegex = /^[a-zA-Z0-9]{6,15}$/;
		var pwd1 = $("#userPwd").val();
		var pwd2 = $("#userPwd2").val();
		if(pwd2 == null || pwd2.length<=0) {
			$("#up2Msg").html("<font color='red'>密码不能为空</font>");
			return;
		}
		if(!passwordRegex.test(pwd1)) {
			$("#up2Msg").html("<font color='red'>密码格式不符</font>");
		}else if(pwd1 != pwd2){
			$("#up2Msg").html("<font color='red'>两次密码不一致</font>");
		}else{
			$("#up2Msg").html("<font color='green'>密码可用</font>");
		}
	}
</script>
<script>
	//查询所有角色信息-------------------------------------------------
	$(function(){
		findRoleAll();
	});
	function findRoleAll(){
		$.ajax({
			url:"../AddUserServlet.do?key=fr",
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
			str += "<option value="+$(this).attr("roleID")+">"+$(this).attr("roleName")+"</option>"
		});
		$("#sel").append(str);
	};
	//提交按钮----------------------------------------------------------
	function subForm(){
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
	<form action="../AddUserServlet.do?key=addu" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>首页</li>
				<li>添加用户</li>
			</ul>
	    </div>
		<!-- 添加内容 -->
		<div>
			<table>
				<tr>
					<td></td>
					<td>${requestScope.msg}</td>
					<td></td>
				</tr>
				<tr>
					<td align="right">用户名</td>
					<td align="center"><input type="text" name="userName" id="userName" onfocus="nick_input()" onblur="nick_out()"/></td>
					<td width="160">
						<span id="unMsg"></span>
					</td>
				</tr>
				<tr>
					<td align="right">密码</td>
					<td align="center"><input type="password" name="userPwd" id="userPwd" onfocus="pwd1_input()" onblur="pwd1_out()"/></td>
					<td>
						<span id="up1Msg"></span>
					</td>
				</tr>
				<tr>
					<td align="right">确认密码</td>
					<td align="center"><input type="password" name="userPwd2" id="userPwd2" onfocus="pwd2_input()" onblur="pwd2_out()"/></td>
					<td>
						<span id="up2Msg"></span>
					</td>
				</tr>
				<tr>
					<td align="right">年龄</td>
					<td align="center"><input type="text" name="userAge"/></td>
					<td>
						<span id="uaMsg"></span>
					</td>
				</tr>
				<tr>
					<td align="right">性别</td>
					<td align="center">
						<select name="userSex">
							<option value="M">男</option>
							<option value="F">女</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">角色</td>
					<td align="center">
						<select name="userRole" id="sel">
							<!-- 角色信息将插入到这里 -->
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">地址</td>
					<td align="center"><input type="text" name="userAddr"/></td>
				</tr>
				<tr>
					<td align="right">手机号码</td>
					<td align="center"><input type="text" name="phoneNum"/></td>
				</tr>
			</table>
		</div>
		<div class="rightinfo" id="d2">
			<div class="tools">
				<ul class="toolbar">
					<li onclick="subForm()"><span><img src="../images/t01.png" /></span>添加</li>
					<li onclick="cls()"><span><img src="../images/t02.png" /></span>重置</li>
				</ul>
			</div>
	    </div>
	</form>    
</body>
</html>