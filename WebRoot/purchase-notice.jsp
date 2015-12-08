<%@ page import="purchase.Approve"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//未登录跳转
Object obj = session.getAttribute("userID");
String zhi_wei = (String)session.getAttribute("zhiwei");
if(obj == null||!("采购员".equals(zhi_wei))){
response.sendRedirect("login.jsp");
}
String userName = (String)session.getAttribute("userName") ;

%>
<jsp:useBean id="purchase" class="purchase.Purchase" scope="page"></jsp:useBean>
<%
	String userID = (String)session.getAttribute("userID") ;
	purchase.setUserID(userID) ;
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Purchase||Notice</title>
    
	
	<link rel="stylesheet" type="text/css" href="css/purchase.css">
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" >
	<script type="text/javascript" src="js/CookieUtil.js"></script>
	<script type="text/javascript">
		var quit = function(){
			CookieUtil.unset("userID") ;
			CookieUtil.unset("password") ;
		}
	</script>

  </head>
  
  <body>
    <header id="header">
		<hgroup>
			<h1 class="site_title">Function</h1>
			<h2 class="section_title">Dashboard</h2>
			<div class="btn_help">
				<a href="">Help</a>
			</div>
		</hgroup>
	</header>
	<!--end of first header-->
	<section id="second_bar">
		<div class="user">
			<p><%=userName  %>（
				<a href="">3个未处理</a>
				）
			</p>
		</div>
		<div class="web_palce">
			<article class="place">
				<a   class="user_status" href="purchase-notice.jsp">采购部门</a>
				<div class="place_driver"></div>
				<a  class="current" href="#">待办事项</a>	
			</article>
		</div>
	</section>
	<!-- end second bar -->
	<aside id="side_bar">
		<div>
			<p id="alt">请选择</p>
			<hr>
		</div>
		<div class="function">
			<div id="notify"><a href="purchase-notice.jsp">待办事项</a></div>
			<div id="query"><a href="purchase-query.jsp">订单查看</a></div>
			<div id="support"><a href="purchase-support.jsp">供应商管理</a></div>
			<div id="exit" onclick="quit()"><a href="doQuit.jsp">退出登录</a></div>
		</div>
		<footer>
			<hr />
			<p>Copyright &copy; 2015 Silence</p>
			<p>Design by <a href="">Silence</a></p>
		</footer>
	</aside>
	<!-- end sidebar -->
	<section id="main">
		<div id = "main_div">
			<header class="notice">
				<h3>通知</h3>
			</header>
		</div>
		<div id="side_div1">
			<header id="time">
				<h3>时间</h3>
				<embed src="./images/time.swf" />
			</header>
		</div>

		<div id="side_div2">
			 <header id="curRecord">
			 	<h3>审批记录</h3>

			 </header>
			 	<table class="table table-striped table-hover table-bordered">
			 		<thead>
			 			<tr>
			 				<td>申请单号</td>
			 				<td>商品名称</td>
			 				<td>数量</td>
			 				<td>金额</td>
			 				<td>状态</td>
			 			</tr>
			 		</thead>
			 		
			 		<tbody>
			 				<%
						 		purchase.setApprove() ;
						 		ArrayList<Approve> approves = purchase.getApprove() ;
						 		Iterator<Approve> iterator = approves.iterator() ;
						 		
						 		while(iterator.hasNext()){
						 			out.print("<tr>") ;
						 			Approve approve = iterator.next() ;
						 			//out.println(approve.getRequestID()+","+approve.getItemName()+","+approve.getCount()+","+approve.getPrice()+","+approve.getStatus()) ;
						 			out.print("<td>"+approve.getRequestID()+"</td>");
						 			out.print("<td>"+approve.getItemName()+"</td>");
						 			out.print("<td>"+approve.getCount()+"</td>");
						 			out.print("<td>"+approve.getPrice()+"</td>");
						 			out.print("<td>"+approve.getStatus()+"</td>");
						 			out.print("</tr>") ;
						 		}
						 		
			 				 %>
			 		</tbody>
			 	</table>
		</div>
	</section>
  </body>
</html>