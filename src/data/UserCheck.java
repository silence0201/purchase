package data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserCheck extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置生成文件的类型和编码格式
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		String output = "";
		//处理接收到的参数，生成响应的XML文档
		String name = request.getParameter("userID");
		String password = request.getParameter("password");
		
		if(name.length()>0&&password.length()>0)
		{
			//下面对用户的身份进行判断
			DBUtil db = new DBUtil() ;
			String sql = "SELECT UserID,Password,Position FROM user WHERE UserID='"+name+"'";
			ResultSet rs=null;
			try {
				rs = db.select(sql);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(rs!=null&&rs.next()){
					String userID=rs.getString("UserID");
					String passWord=rs.getString("Password");
					String Position=rs.getString("Position");
					if(name.equals(userID)&&password.equals(passWord)){
						output ="<response>登录成功</response>";
//						HttpSession se =  request.getSession();
//				 		se.setAttribute("userID",userID);
//				 		se.setAttribute("passWord",passWord);
//				 		
//				 		//跳转判定
//				 		if(userID.startsWith("M")) {
//				 			response.sendRedirect("mangere-notice.jsp");
//				 		}
//				 		if(userID.startsWith("D")){
//				 			response.sendRedirect("divMangere-notice.jsp");
//				 		}
//				 		if(userID.startsWith("P")){
//				 			response.sendRedirect("purchase-notice.jsp");
//				 		}
//				 		if(userID.startsWith("R")){
//				 			response.sendRedirect("request-notice.jsp");
//				 		}
//				 		if(userID.startsWith("S")){
//				 			response.sendRedirect("stockman-status.jsp");
//				 		}
					}else{
						output ="<response>用户名或者密码错误。</response>";
					}
				}else{
					output ="<response>用户名或者密码错误。</response>";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		out.println(output);
		out.close();
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
