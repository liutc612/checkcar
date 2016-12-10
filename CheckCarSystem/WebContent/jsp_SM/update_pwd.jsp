<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改密码页面</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<style type="text/css">
	#pwdtab input,select {width:200px;height:30px;border-radius:3px;}
	#pwdtab {margin:auto;width:40%;}
	#pwdtab td {font-size:16px;padding:4px;height:40px;}
</style>
<script type="text/javascript">
//修改按钮(新方法)-------------------------------------------------------------------------------------------
	function updatePwd(){
		var oldPwd = $("#oldPwd").val();
		var newPwd = $("#pwd1").val();
		var userName = "${sessionScope.user.userName}";
		//先与数据库中的旧密码匹配
		$.ajax({
			type:"post",
			url:"../UpdatePwdServlet.do?key=cop",
			dataType:"text",
			data:{"oldPwd":oldPwd,"userName":userName},
			success:function(info){
				if(info > 0){
					alert("旧密码不正确,请重新输入");
				}else{
					$.ajax({
						type:"post",
						url:"../UpdatePwdServlet.do?key=up",
						dataType:"text",
						data:{"newPwd":newPwd,"userName":userName},
						success:function(info){
							close();
							alert(info);
							window.location.href = "../ExitSystemServlet.do";
						}
					});
				}
			}
		});
	};
	function close(){
		$("#oldPwd").val("");
		$("#pwd1").val("");
		$("#pwd2").val("");
	}
//重置按钮--------------------------------------------------
	function cls(){ 
		var t = document.getElementsByTagName("INPUT"); 
		for (var i=0; i <t.length;i++){ 
			if (t[i].type=='text' | t[i].type=='password'){ 
				t[i].value="";//清空 
			} 
		}
	};
	
//====================================================================================================	
	
	//修改按钮-------------------------------------------------------------------------------------------
/* 	function updatePwd(){
		var newPwd = $("#pwd1").val();
		var userName = "${sessionScope.user.userName}";
		$.ajax({
			type:"post",
			url:"../UpdatePwdServlet.do?key=up",
			dataType:"text",
			data:{"newPwd":newPwd,"userName":userName},
			success:function(info){
				close();
				alert(info);
				window.location.href = "../ExitSystemServlet.do";
			}
		});
	};
	function close(){
		$("#oldPwd").val("");
		$("#pwd1").val("");
		$("#pwd2").val("");
	}
	//验证旧密码------------------------------------------------------------------------------------------
	function checkOldPwd(oldPwd){
		var oldPwd = $("#oldPwd").val();
		var userName = "${sessionScope.user.userName}";
		//先与数据库中的旧密码匹配
		$.ajax({
			type:"post",
			url:"../UpdatePwdServlet.do?key=cop",
			dataType:"text",
			data:{"oldPwd":oldPwd,"userName":userName},
			success:function(info){
				init(info);
			}
		});
	};
	function init(info){
		if(info > 0) {
			//旧密码不匹配
			document.getElementById("old").innerHTML="<font color='red'>旧密码不正确</font>";
		}else {
			document.getElementById("old").innerHTML="<font color='green'>密码正确</font>";
		}
	};
	 */
//新密码的校验--------------------------------------------------------------------------------
	//校验密码1
	function pwd1_input(){
		$("#pwd1_msg").html("<font color='green'>6-15位的字母或数字</font>");
	}
	function pwd1_out(){
		var passwordRegex = /^[a-zA-Z0-9]{6,15}$/;
		var pwd1 = $("#pwd1").val();
		if(pwd1 == null || pwd1.length<=0) {
		$("#pwd1_msg").html("<font color='red'>密码不能为空</font>");
		return;
	}
	if(!passwordRegex.test(pwd1)) {
		$("#pwd1_msg").html("<font color='red'>密码格式不符</font>");
		}else{
			$("#pwd1_msg").html("<font color='green'>密码可用</font>");
		}
	}
	//校验密码2
	function pwd2_input(){
		$("#pwd2_msg").html("<font color='green'>6-15位的字母或数字</font>");
	}
	function pwd2_out(){
		var passwordRegex = /^[a-zA-Z0-9]{6,15}$/;
	    var pwd1 = $("#pwd1").val();
		var pwd2 = $("#pwd2").val();
		if(pwd2 == null || pwd2.length<=0) {
			$("#pwd2_msg").html("<font color='red'>密码不能为空</font>");
			return;
		}
		if(!passwordRegex.test(pwd1)) {
			$("#pwd2_msg").html("<font color='red'>密码格式不符</font>");
		}else if(pwd1 != pwd2){
			$("#pwd2_msg").html("<font color='red'>两次密码不一致</font>");
		}else{
			$("#pwd2_msg").html("<font color='green'>密码可用</font>");
		}
	}
</script>
</head>

<body background="../images/bgimg.png">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>修改密码</li>
		</ul>
    </div>
	
	<div>
	<form id="fm1">
		<table id="pwdtab">
			<tr>
				<td></td>
				<td></td>
				<td align="left"><font color="white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font></td>
			</tr>
			<tr>
				<td align="right">旧密码</td>
				<td align="center"><input type="password" name="oldPwd" id="oldPwd" onblur="checkOldPwd()"/></td>
				<td align="left" id="old"></td>
			</tr>
			<tr>
				<td align="right">新密码</td>
				<td align="center"><input type="password" name="pwd1" id="pwd1" onfocus="pwd1_input()" onblur="pwd1_out()"/></td>
				<td align="left" id="pwd1_msg"></td>
			</tr>
			<tr>
				<td align="right">确认密码</td>
				<td align="center"><input type="password" name="pwd2" id="pwd2" onfocus="pwd2_input()" onblur="pwd2_out()"/></td>
				<td align="left" id="pwd2_msg"></td>
			</tr>
		</table>
	</form>
	</div>
    
    <div class="rightinfo">
		<div class="tools" style="margin:auto;width:19%;">
			<ul class="toolbar">
				<li onclick="updatePwd()"><span><img src="../images/t02.png" /></span>修改</li>
				<li onclick="cls()"><span><img src="../images/t03.png" /></span>重置</li>
			</ul>
		</div>
    </div>
</body>
</html>