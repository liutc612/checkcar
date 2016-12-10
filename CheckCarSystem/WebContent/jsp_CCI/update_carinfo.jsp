<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" ttp://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>更新车检信息页面</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
	input,select {width:200px;height:30px;border-radius:3px;}
	table {margin:auto;width:55%;}
	td {font-size:16px;padding:4px;height:40px;}
	body {TEXT-ALIGN: center;}
	#d2 {MARGIN-RIGHT: auto;MARGIN-LEFT: auto;width:200px;}
</style>
<script>
	//提交表单
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

<body background="images/bgimg.png">
	<form action="OperateCarinfoServlet.do?key=upd" method="post">
		<div class="place">
			<span>位置：</span>
			<ul class="placeul">
				<li>首页</li>
				<li>更新车检信息</li>
			</ul>
	    </div>
	
		<div class="rightinfo" id="d2">
			<div class="tools">
				<ul class="toolbar">
					<li onclick="subForm()"><span><img src="images/t04.png" /></span>提交</li>
					<li onclick="cls()"><span><img src="images/t02.png" /></span>重置</li>
				</ul>
			</div>
	    </div>
		<!-- 添加内容 -->
		<div>
			<table>
				<tr>
					<td align="right">车牌号码</td>
					<td align="center"><input type="text" name="carNumber" value="${requestScope.CarInfo.carNumber}" readonly="readonly"/></td>
					<td align="right">汽车类型</td>
					<td align="center"><input type="text" name="carStyle" value="${requestScope.CarInfo.carStyle}"/></td>
				</tr>
				<tr>
					<td align="right">车主姓名</td>
					<td align="center"><input type="text" name="userName" value="${requestScope.CarInfo.userName}"/></td>
					<td align="right">手机号码</td>
					<td align="center"><input type="text" name="phoneNum" value="${requestScope.CarInfo.phoneNum}"/></td>
				</tr>
				<tr>
					<td align="right">车辆年检</td>
					<td align="center"><input type="text" name="carYearCheck" onfocus="WdatePicker({isShowClear:false,readOnly:true})" value="${requestScope.CarInfo.carYearCheck}"/></td>
					<td align="right">技术等级</td>
					<td align="center"><input type="text" name="technologyLevel" onfocus="WdatePicker({isShowClear:false,readOnly:true})" value="${requestScope.CarInfo.technologyLevel}"/></td>
				</tr>
				<tr>
					<td align="right">二级维护</td>
					<td align="center"><input type="text" name="towLevel" onfocus="WdatePicker({isShowClear:false,readOnly:true})" value="${requestScope.CarInfo.towLevel}"/></td>
					<td align="right">强制保险</td>
					<td align="center"><input type="text" name="compulsoryInsurance" onfocus="WdatePicker({isShowClear:false,readOnly:true})" value="${requestScope.CarInfo.compulsoryInsurance}"/></td>
				</tr>
				<tr>
					<td align="right">是否通知</td>
					<td align="center">
						<script type="text/javascript">
							window.onload = function() {
								document.getElementById('${requestScope.CarInfo.notice}').selected = true;
							};
						</script>
						<select name="notice">
							<option value="Y" id="Y">已通知</option>
							<option value="N" id="N">未通知</option>
						</select>
					</td>
					<td colspan="2"></td>
				</tr>
			</table>
		</div>
	</form>    
</body>
</html>