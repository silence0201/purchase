<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<jsp:useBean id="a" class="data.Admin"></jsp:useBean>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("utf-8");

	//创建sql
    String  userID="";
	String  password="";
	String  userID_sql="";
	String  username_sql="";
	String  password_sql="";
	String  zhiwei_sql="";

 	userID=request.getParameter("userID");
    password=request.getParameter("password");
    
    //out.println(username+password);
    
    a.setLogin_user(userID);
   	a.login();
    userID_sql=a.getUserID();
    username_sql=a.getUserName() ;
    password_sql=a.getPassword();
    zhiwei_sql=a.getPosition();
    
    
 	if(userID_sql.equals(userID)&&password_sql.equals(password)) {
 		session.setAttribute("userID",userID);
 		session.setAttribute("zhiwei",zhiwei_sql);
 		session.setAttribute("userName", username_sql) ;
 		session.setAttribute("status", "login") ;
 		if("总经理".equals(zhiwei_sql)){
 			response.sendRedirect("mangere-notice.jsp");
 		}
 		if("市场部门经理".equals(zhiwei_sql)||"财务部门经理".equals(zhiwei_sql)||"生产部门经理".equals(zhiwei_sql)||"研发部门经理".equals(zhiwei_sql)||"销售部门经理".equals(zhiwei_sql)){
 			response.sendRedirect("divMangere-notice.jsp");
 		}
 		if("采购员".equals(zhiwei_sql)){
 			response.sendRedirect("purchase-notice.jsp");
 		}
 		if("市场部门申请员".equals(zhiwei_sql)||"财务部门申请员".equals(zhiwei_sql)||"生产部门申请员".equals(zhiwei_sql)||"研发部门申请员".equals(zhiwei_sql)||"销售部门申请员".equals(zhiwei_sql)){
 			response.sendRedirect("request-notice.jsp");
 		}
 		if("库管员".equals(zhiwei_sql)){
 			response.sendRedirect("stockman-status.jsp");
 		}
 	}

%>


