
<%@page import="purchase.Supplier"%>
<%@page import="java.util.HashMap" %>
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
    
    <title>Purchase||Support</title>
    
	
	<link rel="stylesheet" type="text/css" href="css/purchase.css">
	<link href="css/bootstrap.min.css" rel="stylesheet">
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
				<a  class="current" href="#">供应商管理</a>	
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
			<header class="supportInfo">
				<h3>供应商信息</h3>
			</header>
					<table class="table table-striped table-hover table-bordered">
			 		<thead>
			 			<tr>
			 				<td>供应商编号</td>
			 				<td>供应商名称</td>
			 				<td>联系人</td>
			 				<td>联系电话</td>
			 				<td>地址</td>
			 			</tr>
			 		</thead>
			 		
			 		<tbody>
			 				<%
						 		purchase.setSupplier() ;
						 		ArrayList<Supplier> suppliers = purchase.getSupplier();
						 		Iterator<Supplier> iterator = suppliers.iterator() ;
						 		while(iterator.hasNext()){
						 			out.print("<tr>") ;
						 			Supplier order = iterator.next() ;
						 			//out.println(approve.getRequestID()+","+approve.getItemName()+","+approve.getCount()+","+approve.getPrice()+","+approve.getStatus()) ;
						 			out.print("<td>"+order.getSupplierID()+"</td>");
						 			out.print("<td>"+order.getSupplierName()+"</td>");
						 			out.print("<td>"+order.getContact()+"</td>");
						 			out.print("<td>"+order.getTel()+"</td>");
						 			out.print("<td>"+order.getAdd()+"</td>");
						 			out.print("</tr>") ;
						 		}
						 		
			 				 %>
			 		</tbody>
			 	</table>
		</div>
		<div id="side_div1">
			<header id="time">
				<h3>时间</h3>
				<embed src="./images/time.swf" />
			</header>
		</div>

		<div id="side_div2">
			 <header id="supportCount">
			 	<h3>供应商统计</h3>
			 </header>
			 <table class='table table-striped table-hover table-bordered'>
			 	<tr>
			 				<td>省份</td>
			 				<td>数目</td>
			 	</tr>
			 <%
			 	purchase.setCount() ;
			 	HashMap<String,Integer> supplierCount = purchase.getCount()  ;
			 	Set<Map.Entry<String,Integer>> sets = supplierCount.entrySet() ;

			 	for(Map.Entry<String,Integer> msp : sets){
			 		out.print("<tr>") ;
			 		out.print("<td>"+msp.getKey()+"</td>");
			 		out.print("<td>"+msp.getValue()+"</td>");
			 		out.print("</tr>") ;
			 	}
			  %>
			  </table>
		</div>
	</section>
  </body>
</html>
