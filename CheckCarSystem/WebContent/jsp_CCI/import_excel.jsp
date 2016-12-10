<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据导入页面</title>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<style type="text/css">
	table {margin-top:20px;margin-left:400px;margin-bottom:4px;border:1px double blue;}
	th {font-size:18px;padding:4px;height:40px;border:1px solid blue;}
	td {font-size:16px;padding:4px;height:40px;border:1px solid blue;}
	body {TEXT-ALIGN: center;}
</style>
<script type="text/javascript">
	function subForm(fileName){
		document.forms[0].submit(); 
	}
</script>
</head>

<body background="../images/bgimg.png">
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>导入Excel文件</li>
		</ul>
    </div>
	<form action="../OperateDataServlet.do?key=imp" method="post" enctype="multipart/form-data">
   		<table id="tab">
   			<tr align="center">
   				<td><input type="file" name="fileName"/></td>
   				<td width="50">
   					<a style="color:blue;text-decoration:underline;cursor:pointer" onclick="subForm()">导入</a>
   				</td>
   			</tr>
   		</table>
   	</form>
	
</body>
</html>