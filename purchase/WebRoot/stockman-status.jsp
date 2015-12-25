<%@page import="stockman.Examine"%>
<%@page import="stockman.Stock"%>
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
    
    <title>Stockman||Status</title>
    

	<link rel="stylesheet" type="text/css" href="css/stockman.css">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/CookieUtil.js"></script>
	<script type="text/javascript">
		var quit = function(){
			CookieUtil.unset("userID") ;
			CookieUtil.unset("password") ;
		}
	</script>
	<link rel="stylesheet" href="css/style.css" type="text/css" />
	<script src="js/jquery-1.3.min.js" type="text/javascript"></script>
	<script src="js/script.js" type="text/javascript"></script>
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
				<a  class="current" href="#">库存状态</a>	
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
			<header class="storeStatus">
				<h3>库存状态</h3>

			</header>
							<table class="table table-striped table-hover table-bordered">
			 		<thead>
			 			<tr>
			 				<td>物品编号</td>
			 				<td>物品名称</td>
			 				<td>物品价格</td>
			 				<td>库存</td>
			 			</tr>
			 		</thead>
			 		
			 		<tbody>
			 				<%
						 		stockman.setStock() ;
						 		ArrayList<Stock> stocks = stockman.getStock() ;
						 		Iterator<Stock> iterator = stocks.iterator() ;
						 		
						 		while(iterator.hasNext()){
						 			out.print("<tr>") ;
						 			Stock stock = iterator.next() ;
						 			out.print("<td>"+stock.getItemID()+"</td>");
						 			out.print("<td>"+stock.getItemName()+"</td>");
						 			out.print("<td>"+stock.getPrice()+"</td>");
						 			out.print("<td>"+stock.getReserve()+"</td>");
						 			out.print("</tr>") ;
						 		}
						 		
			 				 %>
			 		</tbody>
			 	</table>
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
			 <header id="curInfo">
			 	<h3>审批记录</h3>
			 </header>
			 			 	<table class="table table-striped table-hover table-bordered">
			 		<thead>
			 			<tr>
			 				<td>单号</td>
			 				<td>物品</td>
			 				<td>数量</td>
			 				<td>时间</td>
			 			</tr>
			 		</thead>
			 		
			 		<tbody>
			 				<%
						 		stockman.setExamine() ;
						 		ArrayList<Examine> examines = stockman.getExamine() ; 
								Iterator<Examine> examineIterator = examines.iterator() ;
						 		
						 		while(examineIterator.hasNext()){
						 			out.print("<tr>") ;
						 			Examine examine = examineIterator.next() ;
						 			out.print("<td>"+examine.getID()+"</td>");
						 			out.print("<td>"+examine.getItemName()+"</td>");
						 			out.print("<td>"+examine.getNumber()+"</td>");
						 			out.print("<td>"+examine.getTime()+"</td>");
						 			out.print("</tr>") ;
						 		}
						 		
			 				 %>
			 		</tbody>
			 	</table>
		</div>
	</section>
  </body>
</html>
