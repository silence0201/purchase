<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@page import="java.text.SimpleDateFormat"%> 
<%@page import="data.DataTool"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//未登录跳转
Object obj = session.getAttribute("userID");
if(obj == null){
response.sendRedirect("login.jsp");
}
String userName = (String)session.getAttribute("userName") ;
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Request||Request</title>
    
	
	<link rel="stylesheet" type="text/css" href="css/request.css">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/CookieUtil.js"></script>
	<script type="text/javascript" src="js/price.js"></script>
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
				<a   class="user_status" href="request-notice.jsp">需求部门</a>
				<div class="place_driver"></div>
				<a  class="current" href="#">采购申请</a>	
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
			<div id="notify"><a href="request-notice.jsp">待办事项</a></div>
			<div id="request"><a href="request-request.jsp">采购申请</a></div>
			<div id="query"><a href="request-query.jsp">表单查看</a></div>
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
			<header class="buyInfo">
				<h3>采购信息</h3>
			</header>
			<div class="form">
					
					<h3>请输入申请单的详细信息</h3>
					<hr />
					<form action="AddRequest">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="itemName">商品名称:</label>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<!-- <input type="text" id="itemName"> -->
						<select id="itemName" name="itemName" style="width:160px;" onchange="getPrice()">
							<%
								ArrayList<String> itemNames = DataTool.getAllItem() ;
								for(String s : itemNames){
									out.print("<option value="+s+">") ;
									out.print(s) ;
									out.print("</option>") ;
								}
							 %>
						</select>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="number">商品单价：</label>
						<input type="text" id="price" name="price" readonly="readonly">
						
						<br />
						<br />
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="number" >数量 :</label>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<input type="text" id="number" name="number" onchange="setAccount()">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<label for="Account">总金额 :</label>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<input type="text" id="Account" name="Account" readonly="readonly">
						
						<br />
						<br />
						<br />
						<br />
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						申请理由：
	  					<br />
						<br />
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<textarea rows="12" cols="80%" id="extra" style="margin-left: 39px;" name="reason" id="reason"></textarea>
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<br />
						<br />
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<input type="submit">
						<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<input type="reset" value="清空">
					</form>
				</div>
		</div>
		<div id="side_div1">
			<header id="time">
				<h3>时间</h3>
				<embed src="./images/time.swf" />
			</header>
		</div>

		<div id="side_div2">
			 <header id="count">
			 	<h3>统计</h3>
			 </header>
		</div>
	</section>
  </body>
  <script type="text/javascript">
  	getPrice() ;
  </script>
</html>
