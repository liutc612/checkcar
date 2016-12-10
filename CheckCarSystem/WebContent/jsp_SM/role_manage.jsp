<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" ttp://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色管理页面</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<style type="text/css">
	input,select {width:200px;height:30px;border-radius:3px;}
	table {margin:auto;width:25%;margin-left: 390px;}
	td {font-size:16px;padding:4px;height:40px;}
	body {TEXT-ALIGN: center;}
	#d2 {MARGIN-RIGHT: auto;MARGIN-LEFT: auto;width:200px;}
</style>
<script>
	//提交表单---------------------------------------------------
	function subForm(){
		document.forms[0].submit();
	};
	
</script>
</head>

<body background="../images/bgimg.png">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>角色管理</li>
		</ul>
    </div>
	<form action="../OperateRoleServlet.do?key=fr" method="post">
		<div>
			<table>
				<tr>
					<td align="right">角色名</td>
					<td align="center"><input type="text" name="roleName"/></td>
				</tr>
			</table>
		</div>
		<div class="rightinfo" id="d2">
			<div class="tools">
				<ul class="toolbar">
					<li onclick="subForm()"><span><img src="../images/t04.png" /></span>查询</li>
					<li onclick=""><span><img src="../images/t01.png" /></span>添加</li>
				</ul>
			</div>
	   </div>
	</form>    
</body>
</html>