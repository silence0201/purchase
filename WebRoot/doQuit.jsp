<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	request.setCharacterEncoding("utf-8");
	
	session.setAttribute("status", "quit") ;
	//退出登录
	session.removeAttribute("userID");
	session.invalidate();
	response.sendRedirect("login.jsp");
%>

