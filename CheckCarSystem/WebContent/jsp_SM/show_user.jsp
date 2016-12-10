<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示用户信息页面</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<style>
		#box_relative {position: absolute;left: 723px;top: 45px;}
		.info{border:#d3dbde solid 1px; float:right;height:130px;}
		#msgtab td {font-size:15px;padding:4px;height:40px;}
	</style>
	<script type="text/javascript">
		//删除按钮-------------------------------------------------------------------------------
		function delUser(userName){
			//先删除数据库中的用户信息
			$.ajax({
				url:"OperateUserServlet.do?key=del",
				type:"post",
				dataType:"text",
				data:{"userName":userName},
				success:function(msg){
					alert(msg);
				}
			});
			//再清除表格中的行
			$("#"+userName).closest("tr").remove();
		};
	</script>
</head>

<body background="images/bgimg.png">
	<div class="place">
		<span>位置：</span>
			<ul class="placeul">
			<li>首页</li>
			<li>用户信息</li>
		</ul>
    </div>
    <div class="mainindex">
		<table class="tablelist">
			<thead>
				<tr>
					<th>序号</th>
					<th>用户名</th>
					<th>角色</th>
					<th>性别</th>
					<th>年龄</th>
					<th>地址</th>
					<th>手机号码</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<!-- 首页数据将写到这里 -->
				<c:forEach items="${requestScope.list}" var="user" varStatus="v">
					<tr>
						<td>${v.count}</td>
						<td>${user.userName}</td>
						<td>${user.role.roleName}</td>
						<td>${user.userSex}</td>
						<td>${user.userAge}</td>
						<td>${user.address}</td>
						<td>${user.phoneNum}</td>
						<td id="${user.userName}">
							<a href="OperateUserServlet.do?key=pupd&userName=${user.userName}" class="tablelink">编辑</a>&nbsp;&nbsp;&nbsp;
							<a onclick="delUser('${user.userName}')" style="cursor: pointer;" class="tablelink">删除</a>
						</td>
					</tr> 
				</c:forEach>
			</tbody>
		</table>
		
		<!-- 做上一页  1 2 3 4 下一页 -->
		<%-- <div class="pagin">
			<div class="message">共&nbsp;<i class="blue">${requestScope.page.totalNumber}</i>&nbsp;条记录，当前显示第&nbsp;<i class="blue">${requestScope.page.pageIndex}</i>&nbsp;页</div>
			<ul class="paginList">
				<!-- 上一页   -->
				<c:choose>
					<c:when test="${requestScope.page.pageIndex > 1}">
						<li class="paginItem"><a style="cursor: pointer;" onclick="subForm('${requestScope.page.pageIndex -1}')"><span class="pagepre"></span></a></li>
					</c:when>
					<c:otherwise>
						<li class="paginItem"><a style="cursor: pointer;"><span class="pagepre"></span></a></li>
					</c:otherwise>
				</c:choose>
				<!-- 1 2 3 4 5 -->
				<c:forEach begin="${requestScope.page.pageIndex}"  end="${requestScope.page.pageIndex + 9}" var="s"> 
					<c:choose>
						<c:when test="${requestScope.page.pageIndex == s}">
							<li class="paginItem"><a style="cursor: pointer;color:black" onclick="subForm('${s}')">${s}</a></li>
						</c:when>
						<c:otherwise>
							<li class="paginItem"><a style="cursor: pointer;" onclick="subForm('${s}')">${s}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<!-- 下一页 -->
				<c:choose>
					<c:when test="${requestScope.page.pageIndex < requestScope.page.totalPage}">
						<li class="paginItem"><a style="cursor: pointer;" onclick="subForm('${requestScope.page.pageIndex + 1}')"><span class="pagenxt"></span></a></li>
					</c:when>
					<c:otherwise>
						<li class="paginItem"><a style="cursor: pointer;"><span class="pagenxt"></span></a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div> --%>
	</div>
</body>
</html>