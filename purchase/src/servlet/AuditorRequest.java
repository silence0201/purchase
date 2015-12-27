package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import request.Statistics;
import data.DBUtil;

public class AuditorRequest extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		DBUtil db = new DBUtil() ;
		Statistics statistics=new Statistics();
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>审核处理</TITLE></HEAD>");
		String requestID = request.getParameter("requestID") ; //获取申请单id
		String status = new String(request.getParameter("status").getBytes("ISO-8859-1"),"UTF-8")  ; //获取申请单审核状态
		HttpSession session = request.getSession() ;
		String userID = (String)session.getAttribute("userID") ; //获取当前用户id
		
		Date dnow = new Date() ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String auditorTime=sdf.format(dnow) ;			  //获取审核时间
		
		String sql="SELECT RequestmanID"
				+" FROM request"
				+"　WHERE RequestID='"+requestID+"' AND AuditorID is NULL";
		String RequestmanID="";		
		ResultSet rs;

		if("拒绝".equals(status)){
			double account=0;
			sql="SELECT Totalaccount,RequestmanID"
					+" FROM request"
					+" WHERE RequestID="+requestID;
			try {
				rs = db.select(sql) ;
				while(rs.next()){
					account=rs.getDouble(1);
					RequestmanID=rs.getString(2);
				}
				statistics.setUserID(RequestmanID);
				statistics.UpdateRefuseStatistics(account);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		try {
			sql="UPDATE request SET AuditorID='"+userID+"',Requeststatement='"+status+"',Audittime='"+auditorTime+"'"
					+" WHERE RequestID="+requestID;
			db.update(sql);
			String u = request.getHeader("referer") ;
			String s = " <script type='text/javascript'>alert('您的审核已经提交成功');" ;
			s += "window.setTimeout(window.location='"+u+"',5000); </script></HEAD>" ;
			out.print(s+"</html>");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
