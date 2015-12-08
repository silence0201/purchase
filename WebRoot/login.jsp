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

	<script language="javascript">
        //创建XMLHttpReques对象
       function createXMLHttpRequest() {
          if (window.XMLHttpRequest) {
               //Mozilla 浏览器 
               XMLHttpReq = new XMLHttpRequest();
            } else{
                // IE浏览器
                if (window.ActiveXObject) {
                  try {
                         XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
                      }catch (e) {
                         try {
                                XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
                             }catch (e) { }
                      }
                 }
            }
        } 
       //处理服务器响应结果
       function handleResponse() {
        // 判断对象状态
        if (XMLHttpReq.readyState == 4) { 
            // 信息已经成功返回，开始处理信息
           if (XMLHttpReq.status == 200) { 
                var out = "";
                var res = XMLHttpReq.responseXML;
                var response= res.getElementsByTagName("response")[0].firstChild.nodeValue;
                alert(response) ;
                if(response == "登录成功"){
                	document.forms[0].submit();
                }
               }
         }
       }
      //发送客户端的请求
      function sendRequest(url) {
            createXMLHttpRequest();
            XMLHttpReq.open("GET", url, true);
            //指定响应函数
            XMLHttpReq.onreadystatechange = handleResponse;
            // 发送请求
            XMLHttpReq.send(null); 
       }
       //开始调用Ajax的功能
      function userCheck()
      {
         var name = document.getElementById("userID").value;
         var password = document.getElementById("password").value;
         //发送请求
         sendRequest("UserCheck?userID="+name+"&password="+password); 
         
	         if(document.getElementById("remember_me").checked == true){
	        // 默认保留七天的用户信息
	        var now = new Date();
	        CookieUtil.set("userID", name , new Date(now.getTime()+7*24*3600*1000) );   
	        CookieUtil.set("password", password , new Date(now.getTime()+7*24*3600*1000) );    
	      }else{
	        CookieUtil.set("userID",name);
	        CookieUtil.set("password", password); 
	      }
      }
    </script>
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
					<input id="remember_me" type="checkbox" checked="checked" />
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
	          document.forms[0].submit()
	    }      
  </script>	



</html>

