<%@page import="divMangere.Request"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//未登录跳转
Object obj = session.getAttribute("userID");
String zhi_wei = (String)session.getAttribute("zhiwei");
if(obj == null||!("市场部门经理".equals(zhi_wei)||"财务部门经理".equals(zhi_wei)||"生产部门经理".equals(zhi_wei)||"研发部门经理".equals(zhi_wei)||"销售部门经理".equals(zhi_wei))){
response.sendRedirect("login.jsp");
}
String userName = (String)session.getAttribute("userName") ;
%>

<jsp:useBean id="divMangere" class="divMangere.DivMangere" scope="page"></jsp:useBean>
<%
	String userID = (String)session.getAttribute("userID") ;
	divMangere.setUserID(userID) ;
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>DivMangere||Work</title>
  
	
	<link rel="stylesheet" type="text/css" href="css/divMangere.css">
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
				<a   class="user_status" href="divMangere-notice.jsp">部门经理</a>
				<div class="place_driver"></div>
				<a  class="current" href="#">业务查看</a>	
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
			<div id="notify"><a href="divMangere-notice.jsp">待办事项</a></div>
			<div id="query"><a href="divMangere-query.jsp">订单查看</a></div>
			<div id="work"><a href="divMangere-work.jsp">业务查看</a></div>
			<div id="exit"><a href="doQuit.jsp">退出登录</a></div>
		</div>
		<footer>
			<hr />
			<p>Copyright &copy; 2015 Silence</p>
			<p>Design by <a href="http://www.medialoot.com">Silence</a></p>
		</footer>
	</aside>
	<!-- end sidebar -->
	<section id="main">
		<div id = "main_div">
			<header class="divInfo">
				<h3>部门申请信息</h3>
			</header>
			<table class="table table-striped table-hover table-bordered">
			 		<thead>
			 			<tr>
			 				<td>申请单ID</td>
			 				<td>物品名称</td>
			 				<td>数量</td>
			 				<td>总金额</td>
			 				<td>申请人</td>
			 				<td>订单状态</td>
			 				<td>订单生成时间</td>
			 			</tr>
			 		</thead>
			 		
			 		<tbody>
			 				<%
			 					divMangere.setRequests() ;
			 					ArrayList<Request> requests = divMangere.getRequests() ;
			 					
			 					Iterator<Request> requestIterator = requests.iterator() ;
			 					
			 					while(requestIterator.hasNext()){
			 						Request reque = requestIterator.next() ;
			 						out.print("<tr>") ;
			 						out.print("<td>"+reque.getRequestID()+"</td>") ;
			 						out.print("<td>"+reque.getItemName()+"</td>") ;
			 						out.print("<td>"+reque.getNumber()+"</td>") ;
			 						out.print("<td>"+reque.getAccount()+"</td>") ;
			 						out.print("<td>"+reque.getRequestName()+"</td>") ;
			 						out.print("<td>"+reque.getRequestTime()+"</td>") ;
			 						out.print("<td>"+reque.getRequestStatement()+"</td>") ;
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
			 <header id="count">
			 	<h3>采购统计</h3>
			 </header>
		</div>
	</section>
  </body>
</html>
