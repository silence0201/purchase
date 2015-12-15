<%@page import="stockman.Import"%>
<%@page import="java.text.SimpleDateFormat"%> 
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//未登录跳转
Object obj = session.getAttribute("userID");
String zhi_wei = (String)session.getAttribute("zhiwei");
if(obj == null||!("库管员".equals(zhi_wei))){
response.sendRedirect("login.jsp");
}
String userName = (String)session.getAttribute("userName") ;
%>

<jsp:useBean id="stockman" class="stockman.Stockman"></jsp:useBean>
<%
	String userID = (String)session.getAttribute("userID");
	stockman.setUserID(userID) ;
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Stockman||Import</title>
    
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/stockman.css">
	
	<script type="text/javascript" src="js/CookieUtil.js"></script>
	<script type="text/javascript" src="js/stock-import.js"></script>
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
				<a   class="user_status" href="stockman-status.jsp">库管员</a>
				<div class="place_driver"></div>
				<a  class="current" href="#">入库登记</a>	
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
			<div id="status"><a href="stockman-status.jsp">库存状态</a></div>
			<div id="import"><a href="stockman-import.jsp">入库登记</a></div>
			<div id="export"><a href="stockman-export.jsp">出库登记</a></div>
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
			<header class="import">
				<h3>入库登记</h3>
				<div class="form">
					<h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					请输入入库单的详细信息</h3>
					<br />
					<hr />
					<form action="AddImport">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="orderID">订单ID &nbsp;&nbsp;&nbsp;&nbsp;</label>
						<input type="text" id="orderID" name="orderID" onchange="setInfo()">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="userID">审核人ID&nbsp;&nbsp;</label>
						<input type="text" id="orderManId" name="orderManId" readonly="readonly">
						<br />
						<br />
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="itemID">商品ID &nbsp;&nbsp;&nbsp;&nbsp;</label>
						<input type="text" id="itemID" name="itemID" readonly="readonly">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="price">商品单价&nbsp;&nbsp;</label>
						<input type="text" id="price" name="price" readonly="readonly">
						
						<br />
						<br />
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="number">出库数量&nbsp;&nbsp;</label>
						<input type="text" id="number" name="number" onchange="setAccount()" readonly="readonly">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="account">总金额 &nbsp;&nbsp;&nbsp;&nbsp;</label>
						<input type="text" id="account" name="account" readonly="readonly">
						
						<br />
						<br />
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						备注：
	  					<br />
						<br />
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<textarea rows="8" cols="80%" id="extra" style="margin-left: 39px;"></textarea>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<br />
						<br />
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<input type="submit">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<input type="reset" value="清空">
					</form>
				</div>
			</header>
		</div>
		<div id="side_div1">
			<header id="time">
				<h3>时间</h3>
				<embed src="./images/time.swf" />
			</header>
		</div>

		<div id="side_div2">
			 <header id="curImport">
			 	<h3>最近入库</h3>
			 	
			 </header>
			 <table class="table table-striped table-hover table-bordered">
			 		<thead>
			 			<tr>
			 				<td>入库单号</td>
			 				<td>订单号</td>
			 				<td>物品</td>
			 				<td>数量</td>
			 				<td>时间</td>
			 			</tr>
			 		</thead>
			 		
			 		<tbody>
			 				<%
						 		stockman.setImport() ;
						 		ArrayList<Import> imports = stockman.getImport() ;
						 		Iterator<Import> importIterator = imports.iterator() ;
						 		
						 		while(importIterator.hasNext()){
						 			out.print("<tr>") ;
						 			Import import1 = importIterator.next() ;
						 			out.print("<td>"+import1.getImportID()+"</td>");
						 			out.print("<td>"+import1.getOrderID()+"</td>");
						 			out.print("<td>"+import1.getItemName()+"</td>");
						 			out.print("<td>"+import1.getNumber()+"</td>");
						 			out.print("<td>"+import1.getImportTime()+"</td>");
						 			out.print("</tr>") ;
						 		}
						 		
			 				 %>
			 		</tbody>
			 	</table>
		</div>
	</section>
  </body>
</html>
