<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>欢迎登录采购管理系统</title>
	<link href="css/login.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/CookieUtil.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
</head>

<body>
	<div class="main-login">
		<div class="login-logo"></div>
		<div class="login-content">
			<input type="hidden" id="path" value="<%=path %>
			" />
			<form action="doLogin.jsp" method="post" id="login-form" name="login-form">
				<div class="login-info">
					<span class="user">&nbsp;</span>
					<input name="userID" id="userID" type="text" value="" class="login-input"/>
				</div>
				<div class="login-info">
					<span class="pwd">&nbsp;</span>
					<input name="password" id="password" type="password"  value="" class="login-input"/>
				</div>
				<div class="login-oper">
					<input id="remember_me" type="checkbox"/>
					记住密码
				</div>
				<div class="login-oper">
					<input id="login" type="button" value="登 录" class="login-btn" onclick="userCheck()"/>
					<input name="" type="reset" value="重 置" class="login-reset"/>
				</div>
			</form>
		</div>
		<div class="bottom">技术支持：********有限公司&nbsp;&nbsp;</div>
	</div>
</body>

	 <script type="text/javascript">
	    var username = CookieUtil.get("userID") ;
	    var password = CookieUtil.get("password") ;
	    if(username == null){
	    }else{
	    	  document.getElementById("userID").value = username ;
	          document.getElementById("password").value = password ;
	          //document.forms[0].submit() ;
	    }      
  </script>	



</html>

