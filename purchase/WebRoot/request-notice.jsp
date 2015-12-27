<%@page import="request.Request"%>
<%@page import="request.RequestNotice"%>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//未登录跳转
Object obj = session.getAttribute("userID");
String zhi_wei = (String)session.getAttribute("zhiwei");
if(obj == null||!("市场部门申请员".equals(zhi_wei)||"财务部门申请员".equals(zhi_wei)||"生产部门申请员".equals(zhi_wei)||"研发部门申请员".equals(zhi_wei)||"销售部门申请员".equals(zhi_wei))){
response.sendRedirect("login.jsp");
}
String userName = (String)session.getAttribute("userName") ;
%>

<jsp:useBean id="requestMan" class="request.RequestMan" scope="page"></jsp:useBean>
<%
	String userID = (String)session.getAttribute("userID") ;
	requestMan.setUserID(userID) ;
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Request||Notice</title>
    
	<link rel="stylesheet" type="text/css" href="css/request.css">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<script type="text/javascript" src="js/CookieUtil.js"></script>
	<script type="text/javascript">
		var quit = function(){
			CookieUtil.unset("userID") ;
			CookieUtil.unset("password") ;
		}
	</script>
		 <script type="text/javascript">
 	function handle(val){
		window.location.href = "request-handle.jsp?requestID="+val ;
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
				<%
					int count = 0 ;
					String[] s = new String[100] ;
					requestMan.setRequestNotices() ;
					ArrayList<RequestNotice> requestNotices = requestMan.getRequestNotices() ;
					
					Iterator<RequestNotice> requestNoticeIterator = requestNotices.iterator() ;
					
					while(requestNoticeIterator.hasNext()){
						RequestNotice rn = requestNoticeIterator.next() ;
						if(rn.getRequestStatement().equals("拒绝")){
							s[count] = "<p class='bg-danger noticeCon'>" ;
						}else{
							s[count] = "<p class='bg-info noticeCon'>" ;
						}
						s[count] += "你有订单号为<a onclick='handle(this.innerHTML)'>"+rn.getRequestID()+"</a>的订单已经"+rn.getRequestStatement() ;
						s[count] += "</p>" ;
						count ++ ;
					}
					session.setAttribute("count", count) ;
				 %>
			<p><%=userName  %>（
				<a href=""><%=session.getAttribute("count") %>个未处理</a>
				）
			</p>
		</div>
		<div class="web_palce">
			<article class="place">
				<a   class="user_status" href="request-notice.jsp">需求部门</a>
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
			<div id="notify"><a href="request-notice.jsp">待办事项</a></div>
			<div id="notify"><a href="request-request.jsp">采购申请</a></div>
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
			<header class="notice">
				<h3>通知</h3>
			</header>
			<div class="noticeContext">
				<%
					for(int i = 0 ;i < count ;i++){
						out.print(s[i]) ;
					}
				 %>
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
			 <header id="record">
			 	<h3>申请审核记录</h3>
			 </header>
			 <table class="table table-striped table-hover table-bordered">
			 		<thead style="font-size: 0.9em">
			 			<tr>
			 				<td>需求单ID</td>
			 				<td>物品名称</td>
			 				<td>数量</td>
			 				<td>状态</td>
			 			</tr>
			 		</thead>
			 		
			 		<tbody>
			 				<%
			 					requestMan.setRecRequests() ;
			 					ArrayList<Request> recRequests =requestMan.getRecRequests() ;
			 					
			 					Iterator<Request> recRequestIterator = recRequests.iterator() ;
			 					while(recRequestIterator.hasNext()){
			 						out.print("<tr>") ;
			 						Request recRequest = recRequestIterator.next() ;
			 						out.print("<td>"+recRequest.getRequestID()+"</td>") ;
			 						out.print("<td>"+recRequest.getItemName()+"</td>") ;
			 						out.print("<td>"+recRequest.getNumber()+"</td>") ;
			 						out.print("<td>"+recRequest.getRequestStatement()+"</td>") ;
			 						out.print("</tr>") ;
			 					} 
			 				 %>
			 		</tbody>
			 	</table>
		</div>
	</section>
  </body>
</html>
