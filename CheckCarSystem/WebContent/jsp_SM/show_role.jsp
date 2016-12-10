<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示角色信息页面</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<style>
		#box_relative {position: absolute;left: 723px;top: 45px;}
		.info{border:#d3dbde solid 1px; float:right;height:130px;}
		#msgtab td {font-size:15px;padding:4px;height:40px;}
		#tablist{width:50%;}
		#d2{margin-left: 350px;}
	</style>
	<script type="text/javascript">
		//删除按钮-------------------------------------------------------------------------------
		function delRole(roleID){
			//先删除数据库中的用户信息
			$.ajax({
				url:"OperateRoleServlet.do?key=del",
				type:"post",
				dataType:"text",
				data:{roleID:roleID},
				success:function(msg){
					alert(msg);
				}
			});
			//再清除表格中的行
			$("#"+roleID).closest("tr").remove();
		};
	</script>
</head>

<body background="images/bgimg.png">
	<div class="place">
		<span>位置：</span>
			<ul class="placeul">
			<li>首页</li>
			<li>角色信息</li>
		</ul>
    </div>
    <div class="mainindex" id="d2">
		<table class="tablelist" id="tablist">
			<thead>
				<tr>
					<th width="100px">角色ID</th>
					<th>角色名</th>
					<th width="100px">操作</th>
				</tr>
			</thead>
			<tbody>
				<!-- 首页数据将写到这里 -->
				<c:forEach items="${requestScope.list}" var="role">
					<tr>
						<td>${role.roleID}</td>
						<td>${role.roleName}</td>
						<td id="${role.roleID}">
							<a href="OperateRoleServlet.do?key=pupd&roleID=${role.roleID}" class="tablelink">编辑</a>&nbsp;&nbsp;&nbsp;
							<a onclick="delRole('${role.roleID}')" style="cursor: pointer;" class="tablelink">删除</a>
						</td>
					</tr> 
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>