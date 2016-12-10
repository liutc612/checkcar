<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示登录日志页面</title>
	<link href="css/style.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<style>
		#box_relative {position: absolute;left: 723px;top: 45px;}
		.info{border:#d3dbde solid 1px; float:right;height:130px;}
		#msgtab td {font-size:15px;padding:4px;height:40px;}
	</style>
	<script type="text/javascript">
		function subForm(pageIndex){
			document.getElementById("indexPage").value = pageIndex;
			document.forms[0].submit();
		};
	</script>
</head>

<body background="images/bgimg.png">
<form action="FindLoginlogServlet.do" method="post">
	<input type="hidden" name="loginName" value="${requestScope.loginlog.loginName}"/>
   	<input type="hidden" name="loginIP" value="${requestScope.loginlog.loginIP}"/>
   	<input type="hidden" name="loginState" value="${requestScope.loginlog.loginState}"/>
   	<input type="hidden" name="loginDate" value="${requestScope.loginlog.loginDate}"/>
   	<input type="hidden" name="indexPage" id="indexPage"/>
   	
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li>首页</li>
			<li>登录日志</li>
		</ul>
    </div>
    <div class="mainindex">
		<table class="tablelist">
			<thead id="thead">
				<tr>
					<th>序号</th>
					<th>登录编号</th>
					<th>用户名</th>
					<th>IP地址</th>
					<th>状态</th>
					<th>时间</th>
				</tr>
			</thead>
			<tbody id="tbody">
				<!-- 查询得到的用户信息将插入到这里 -->
				<c:forEach items="${requestScope.page.result}" var="ll">
					<tr>
						<td>${ll.rownum}</td>
						<td>${ll.loginID}</td>
						<td>${ll.loginName}</td>
						<td>${ll.loginIP}</td>
						<td>${ll.loginState}</td>
						<td>${ll.loginDate}</td>
					</tr> 
				</c:forEach>				
			</tbody>
		</table>
		<!-- 做上一页  1 2 3 4 下一页 -->
		<div class="pagin">
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
		</div>
	</div>
</form>
</body>
</html>