<%@page import="purchase.Item"%>
<%@page import="purchase.Supplier"%>
<%@page import="purchase.Order"%>
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
<jsp:useBean id="statistics" class="purchase.Statistics" scope="page"></jsp:useBean>
<%
	String userID = (String)session.getAttribute("userID") ;
	purchase.setUserID(userID) ;
	statistics.setUserID(userID);
	String supplierID = request.getParameter("supplierID") ;
	Supplier sp = purchase.getBySupplierID(supplierID) ;
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Purchase||Query</title>
    
	
	<link rel="stylesheet" type="text/css" href="css/purchase.css">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/CookieUtil.js"></script>
	<script type="text/javascript">
		var quit = function(){
			CookieUtil.unset("userID") ;
			CookieUtil.unset("password") ;
		}
		function modify(){
			document.getElementById("modify").click() ;
		}
		function clear(){
			document.getElementById("clear").click() ;
		}
	</script>
	<link rel="stylesheet" href="css/style.css" type="text/css" />
	<script src="js/jquery-1.3.min.js" type="text/javascript"></script>
	<script src="js/script.js" type="text/javascript"></script>
	<script src="js/purchase-supplier-item.js" type="text/javascript"></script>
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
				<a href=""><%=session.getAttribute("count") %>个未处理</a>
				）
			</p>
		</div>
		<div class="web_palce">
			<article class="place">
				<a   class="user_status" href="purchase-notice.jsp">采购部门</a>
				<div class="place_driver"></div>
				<a   href="purchase-support.jsp">供应商管理</a>
				<div class="place_driver"></div>
				<a   href="purchase-supplierInfo.jsp?supplierID=<%=supplierID %>">供应商信息</a>
				<div class="place_driver"></div>
				<a   href="#" class="current">修改商品</a>			
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
			<header class="info">
				<h3>供应商信息</h3>
			</header>
			<div class="form">
					<h3 style="text-align: center;">请输入物品信息</h3>
					<hr />
					<table class="table table-bordered">
					<thead>
						<tr>
						<td colspan="4">当前供应商ID<span id="supplierID"><%=supplierID %></span></td>
						</tr>
					</thead>
						<tbody>
							<tr>
								<td class="active">供应商名称</td>
								<td><%=sp.getSupplierName() %></td>
								<td class="active">联系人</td>
								<td><%=sp.getContact() %></td>
							</tr>
							<tr>
								<td class="active">省份</td>
								<td><%=sp.getAdd() %></td>
								<td class="active">联系方式</td>
								<td><%=sp.getTel()%></td>
							</tr>
						</tbody>
					</table>
					<hr />
					<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<label for="itemID">物品ID&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<input type="text" id="itemID" name="itemID" onchange="setInfo()">
					<br />
					<hr />
					<form action="UpdateSupplierItem">
						<input type="text" name="supplierID" value="<%=supplierID %>" style="display: none;" >
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="itemName">物品名称&nbsp;</label>
						<input type="text" id="itemName" name="itemName" readonly="readonly">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="price">物品单价</label>
						<input type="text" id="price" name="price">
						<br />
						<br />
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="quality">物品质量&nbsp;</label>
						<input type="text" id="quality" name="quality">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="test">物品状态&nbsp;</label>
						<input type="text" id="test" name="test">
						<br />
						<input type="submit" id="modify" style="display: none;">
						<input type="reset" id="clear" style="display: none;">
					</form>
					<hr />
					<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<button type="button" class="btn btn-primary" onclick="modify()">修改</button>
					<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
					<button type="button" class="btn btn-primary" onclick="clear()">清空</button>
				</div>
		</div>
		<div id="side_div1">
			<header id="time">
				<h3>时间</h3>
			</header>
			<table cellspacing="0">
			  <thead>
			    <tr>
			      <th>Mon</th>
			      <th>Tue</th>
			      <th>Wed</th>
			      <th>Thu</th>
			      <th>Fri</th>
			      <th>Sat</th>
			      <th>Sun</th>
			    </tr>
			  </thead>
			  <tbody>
			    <tr>
			      <td class="padding" colspan="3"></td>
			      <td> 1</td>
			      <td> 2</td>
			      <td> 3</td>
			      <td> 4</td>
			    </tr>
			    <tr>
			      <td> 5</td>
			      <td> 6</td>
			      <td> 7</td>
			      <td> 8</td>
			      <td class="today"> 9</td>
			      <td>10</td>
			      <td>11</td>
			    </tr>
			    <tr>
			      <td>12</td>
			      <td class="date_has_event"> 13
			        <div class="events">
			          <ul>
			            <li> <span class="title">Event 1</span> <span class="desc">Lorem ipsum dolor sit amet, consectetu adipisicing elit.</span> </li>
			            <li> <span class="title">Event 2</span> <span class="desc">Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</span> </li>
			          </ul>
			        </div></td>
			      <td>14</td>
			      <td>15</td>
			      <td>16</td>
			      <td>17</td>
			      <td>18</td>
			    </tr>
			    <tr>
			      <td>19</td>
			      <td>20</td>
			      <td>21</td>
			      <td class="date_has_event"> 22
			        <div class="events">
			          <ul>
			            <li> <span class="title">Event 1</span> <span class="desc">Lorem ipsum dolor sit amet, consectetu adipisicing elit.</span> </li>
			            <li> <span class="title">Event 2</span> <span class="desc">Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</span> </li>
			          </ul>
			        </div></td>
			      <td>23</td>
			      <td>24</td>
			      <td>25</td>
			    </tr>
			    <tr>
			      <td>26</td>
			      <td>27</td>
			      <td>28</td>
			      <td>29</td>
			      <td>30</td>
			      <td>31</td>
			      <td class="padding"></td>
			    </tr>
			  </tbody>
			</table>
		</div>

		<div id="side_div2">
			 <header id="curCount">
			 	<h3>供应商品</h3>
			 </header>
			 <table class="table table-striped table-hover table-bordered">
			 	<thead>
			 		<tr>
			 			<td>商品ID</td>
			 			<td>商品名</td>
			 			<td>单价</td>
			 			<td>质量</td>
			 			<td>状态</td>
			 		</tr>
			 	</thead>
			 	<tbody>
			 		<%
			 			ArrayList<Item> items = purchase.getItemBySupplierID(supplierID) ;
			 			for(Item item : items ){
			 				out.print("<tr>") ;
			 				out.print("<td>"+item.getItemID()+"</td>") ;
			 				out.print("<td>"+item.getItemName()+"</td>") ;
			 				out.print("<td>"+item.getPrice()+"</td>") ;
			 				out.print("<td>"+item.getQuality()+"</td>") ;
			 				out.print("<td>"+item.getStatus()+"</td>") ;
			 				out.print("</tr>") ;
			 			}
			 		 %>
			 	</tbody>
			 </table>
		</div>
	</section>
  </body>
</html>
