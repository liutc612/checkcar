<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../base.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>显示车检信息页面</title>
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
	<script type="text/javascript">
		//删除按钮-------------------------------------------------------------------------------
		function delCarInfo(carNumber){
			//先删除数据库中的用户信息
			$.ajax({
				url:"OperateCarinfoServlet.do?key=del",
				type:"post",
				dataType:"text",
				data:{"carNumber":carNumber},
				success:function(msg){
					alert(msg);
				}
			});
			//再清除表格中的行
			$("#"+carNumber).closest("tr").remove();
		};
	</script>
</head>

<body background="images/bgimg.png">
<form action="FindCarinfoServlet.do?key=fci" method="post">
	<input type="hidden" name="carNumber" value="${requestScope.CarInfo.carNumber}"/>
   	<input type="hidden" name="carStyle" value="${requestScope.CarInfo.carStyle}"/>
   	<input type="hidden" name="userName" value="${requestScope.CarInfo.userName}"/>
   	<input type="hidden" name="carYearCheck" value="${requestScope.CarInfo.carYearCheck}"/>
   	<input type="hidden" name="technologyLevel" value="${requestScope.CarInfo.technologyLevel}"/>
   	<input type="hidden" name="towLevel" value="${requestScope.CarInfo.towLevel}"/>
   	<input type="hidden" name="compulsoryInsurance" value="${requestScope.CarInfo.compulsoryInsurance}"/>
   	<input type="hidden" name="phoneNum" value="${requestScope.CarInfo.phoneNum}"/>
   	<input type="hidden" name="notice" value="${requestScope.CarInfo.notice}"/>
   	<input type="hidden" name="indexPage" id="indexPage"/>
   	
	<div class="place">
		<span>位置：</span>
			<ul class="placeul">
			<li>首页</li>
			<li>车检信息</li>
		</ul>
    </div>
    <div class="mainindex">
		<table class="tablelist">
			<thead>
				<tr>
					<th>序号</th>
					<th>车牌号码</th>
					<th>车辆类型</th>
					<th>车主姓名</th>
					<th>车辆年检</th>
					<th>技术等级</th>
					<th>二级维护</th>
					<th>强制保险</th>
					<th>手机号码</th>
					<th>通知</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<!-- 首页数据将写到这里 -->
				<c:forEach items="${requestScope.page.result}" var="ci" varStatus="v">
					<tr>
						<td>${v.count}</td>
						<td>${ci.carNumber}</td>
						<td>${ci.carStyle}</td>
						<td>${ci.userName}</td>
						<td>${ci.carYearCheck}</td>
						<td>${ci.technologyLevel}</td>
						<td>${ci.towLevel}</td>
						<td>${ci.compulsoryInsurance}</td>
						<td>${ci.phoneNum}</td>
						<td>${ci.notice}</td>
						<td id="${ci.carNumber}">
							<a href="OperateCarinfoServlet.do?key=pupd&carNumber=${ci.carNumber}" class="tablelink">编辑</a>&nbsp;&nbsp;&nbsp;
							<a onclick="delCarInfo('${ci.carNumber}')" style="cursor: pointer;" class="tablelink">删除</a>
						</td>
					</tr> 
				</c:forEach>
			</tbody>
		</table>
		
		
		<!-- 做上一页  1 2 3 4 下一页 -->
		
		<div class="pagin">
			<div class="message">共&nbsp;<i class="blue">${requestScope.page.totalNumber}</i>&nbsp;条记录，当前显示第&nbsp;<i class="blue">${requestScope.page.pageIndex}</i>&nbsp;页</div>
			<ul class="paginList">
				<c:choose>
					<c:when test="${requestScope.page.pageIndex > 1}">
						<li class="paginItem"><a style="cursor: pointer;" onclick="subForm('${requestScope.page.pageIndex -1}')"><span class="pagepre"></span></a></li>
					</c:when>
					<c:otherwise>
						<li class="paginItem"><a style="cursor: pointer;"><span class="pagepre"></span></a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="1"  end="${requestScope.page.totalPage}" var="s"> 
					<c:choose>
						<c:when test="${requestScope.page.pageIndex == s}">
							<li class="paginItem"><a style="cursor: pointer;color:black" onclick="subForm('${s}')">${s}</a></li>
						</c:when>
						<c:otherwise>
							<li class="paginItem"><a style="cursor: pointer;" onclick="subForm('${s}')">${s}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
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