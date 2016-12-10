<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="base.jsp" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ltc.domain.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>操作页面</title>
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
//显示不同颜色----------------------------------------------------------------------------------
		/* $(function(){
			var date = $("#cyc").html();
			changeColor(date);
		});
		function changeColor(date){
			var oldDate = new Date(date);
			//alert("1---"+oldDate);
			var nowDate = new Date();
			//alert("2---"+nowDate);
			var num = (oldDate - nowDate)/(24*60*60*1000);
			//alert("3---"+num);
			if(num < 7) {
				$("#cyc").css("background","red");
			}
		};
		//获取当前时间，格式YYYY-MM-DD
	    function formatDate(date) {
	        var seperator1 = "-";
	        var year = date.getFullYear();
	        var month = date.getMonth() + 1;
	        var strDate = date.getDate();
	        if (month >= 1 && month <= 9) {
	            month = "0" + month;
	        }
	        if (strDate >= 0 && strDate <= 9) {
	            strDate = "0" + strDate;
	        }
	        var currentdate = year + seperator1 + month + seperator1 + strDate;
	        return currentdate;
	    }; */
//-----------------------------------------------------------------------------------------
	    //修改通知类型
	    function notice(carNumber){
	    	$.ajax({
			 	type: "POST",
	            url: "OperateCarinfoServlet.do?key=updnt",
	            dataType: "json",
	            data:{carNumber:carNumber,notice:"Y"},
	            success:function(msg){
	            	$("."+carNumber).html('<img src="images/Y.png" width="30%"/>');
	            }
		 	});
	    };
	</script>
</head>

<body background="images/bgimg.png">
<form action="FindHomeDataServlet.do" method="post">
   	<input type="hidden" name="indexPage" id="indexPage"/>
   	
	<div class="place">
	    <span>位置：</span>
	    <ul class="placeul">
		    <li>首页</li>
		    <li>系统提示</li>
	    </ul>
    </div>
    <div class="mainindex">
		<div class="welinfo">
			<span><img src="images/sun.png" alt="天气" /></span>
			<b><font color="red">${sessionScope.user.userName }&nbsp;</font>您好，欢迎使用车检信息管理系统</b>(liutc612@gmail.com)
		</div>
		<div class="welinfo">
			<span><img src="images/dp.png" alt="提醒" /></span>
			<i>本系统仅限内部人员使用，禁止利用他人账号登录 </i> （不是您登录的？<a href="ExitSystemServlet.do">请点这里</a>）
		</div>
		<div class="welinfo">
			<span><img src="images/time.png" alt="时间" /></span>
			<b>系统提示时间为车辆<font color="red">下一次</font>各种检测日期距离今天小于或者等于<font color="red">7</font>天</b>
		</div>
		
		<div id="box_relative" class="info">
			<div class="listtitle"><a href="OperateDataServlet.do?key=bd" class="more1">每天请在下班之前点击【数据备份】</a>各项颜色说明</div>
			<table id="msgtab">
				<tr>
					<td><img src="images/year.png"/></td>
					<td>车辆年检即将到期</td>
					<td><img src="images/second.png"/></td>
					<td>技术等级即将到期</td>
				</tr>
				<tr>
					<td><img src="images/technology.png"/></td>
					<td>二级维护即将到期</td>
					<td><img src="images/safe.png"/></td>
					<td>强制保险即将到期</td>
				</tr>
			</table>
		</div>

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
					<th>编辑</th>
				</tr>
			</thead>
			<tbody>
				<%
				Page p = (Page)request.getAttribute("page");
				List<CarInfo> list = (List<CarInfo>)p.getResult();
				Date date = new Date();
				for(int i=0;i<list.size();i++){
					String cn = list.get(i).getCarNumber();
					String cs = list.get(i).getCarStyle();
					String un = list.get(i).getUserName();
					Date cyc = list.get(i).getCarYearCheck();
					Date tll = list.get(i).getTechnologyLevel();
					Date tl = list.get(i).getTowLevel();
					Date cli = list.get(i).getCompulsoryInsurance();
					String pn = list.get(i).getPhoneNum();
					String nt = list.get(i).getNotice();
					long n1 = cyc.getTime() - date.getTime();
					long n2 = tll.getTime() - date.getTime();
					long n3 = tl.getTime() - date.getTime();
					long n4 = cli.getTime() - date.getTime();
				%>
				<tr>
					<td><%=i+1%></td>
					<td id="carNumber"><%=cn%></td>
					<td><%=cs%></td>
					<td><%=un%></td>
					
					<!-- 车辆年检 -->
					<%
					if(n1/(24*60*60*1000) < 7) {
					%>
					<td bgcolor="FF0066"><%=cyc%></td>
				  <%}else{%>
				  	<td><%=cyc%></td>
				  <%}%>
					<!-- 技术等级 -->
					<%
					if(n2/(24*60*60*1000) < 7) {
					%>
					<td bgcolor="0099CC"><%=tll%></td>
				  <%}else{%>
				  	<td><%=tll%></td>
				  <%}%>
					<!-- 二级维护 -->
					<%
					if(n3/(24*60*60*1000) < 7) {
					%>
					<td bgcolor="FF6699"><%=tl%></td>
				  <%}else{%>
				  	<td><%=tl%></td>
				  <%}%>
					<!-- 强制保险 -->
					<%
					if(n4/(24*60*60*1000) < 7) {
					%>
					<td bgcolor="6666FF"><%=cli%></td>
				  <%}else{%>
				  	<td><%=cli%></td>
				  <%}%>
					
					<td><%=pn%></td>
					
					<!-- 是否通知 -->
					<%
					if("Y".equals(nt)) {
					%>
					<td><img src="images/Y.png" width="30%"/></td>
				  <%}else{%>
				  	<td class="<%=cn%>"><a onclick="notice('<%=cn%>')" style="cursor: pointer;" class="tablelink"><img src="images/N.png" width="30%"/></a></td>
				  <%}%>
					
					<td><a href="OperateCarinfoServlet.do?key=pupd&carNumber=<%=cn%>" class="tablelink">编辑</a></td>
				</tr>
			  <%}%>
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