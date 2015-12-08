<%@page import="mangere.Demand"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//未登录跳转
Object obj = session.getAttribute("userID");
//String user_name = (String)session.getAttribute("username");
String zhi_wei = (String)session.getAttribute("zhiwei");
if(obj == null||!("总经理".equals(zhi_wei))){
response.sendRedirect("login.jsp");
}
String userName = (String)session.getAttribute("userName") ;
%>

<jsp:useBean id="mangere" class="mangere.Mangere" scope="page"></jsp:useBean>
<%
	String userID = (String)session.getAttribute("userID") ;
	mangere.setUserID(userID) ;
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Mangere||Notice</title>
	<link rel="stylesheet" type="text/css" href="css/mangere.css">
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
				<a   class="user_status" href="#">总经理</a>
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
			<div id="notify"><a href="mangere-notice.jsp">待办事项</a></div>
			<div id="query"><a href="mangere-query.jsp">订单查看</a></div>
			<div id="work"><a href="mangere-work.jsp">业务查看</a></div>
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
			 	<h3>公司需求记录</h3>
			 </header>
			 <table class="table table-striped table-hover table-bordered">
			 		<thead style="font-size: 0.9em">
			 			<tr>
			 				<td>需求单ID</td>
			 				<td>物品名称</td>
			 				<td>数量</td>
			 				<td>总金额</td>
			 				<td>状态</td>
			 				<td>生成时间</td>
			 			</tr>
			 		</thead>
			 		
			 		<tbody style="font-size: 0.9em">
			 				<%
			 					mangere.setDemands() ;
			 				
			 					ArrayList<Demand> demands = mangere.getDemands() ;
			 					
			 					Iterator<Demand> demandsIterator = demands.iterator() ;
			 					while(demandsIterator.hasNext()){
			 						out.print("<tr>") ;
						 			Demand demand = demandsIterator.next() ;
						 			out.print("<td>"+demand.getDemandID()+"</td>");
						 			out.print("<td>"+demand.getItemName()+"</td>");
						 			out.print("<td>"+demand.getNumber()+"</td>");
						 			out.print("<td>"+demand.getAccount()+"</td>");
						 			out.print("<td>"+demand.getStatement()+"</td>") ;
						 			out.print("<td>"+demand.getDemandTime()+"</td>") ;
						 			out.print("</tr>") ;
			 					}
			 				 %>
			 		</tbody>
			 	</table>
		</div>
	</section>
  </body>
</html>