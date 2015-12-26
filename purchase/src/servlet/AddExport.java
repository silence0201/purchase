package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DBUtil;
import data.DataTool;

public class AddExport extends HttpServlet {

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
		PrintWriter out = response.getWriter();
		
		String requestID = request.getParameter("requestID") ;   //或去订单的
		String itemID = request.getParameter("itemID") ;  //获取物品的id
		String numberStr = request.getParameter("number") ;  //获取物品的数量
		int number = Integer.parseInt(numberStr) ;  
		String requestManID = request.getParameter("requestManID") ;  //获取负责人的id号
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>这是一个添加成员的处理页面</TITLE>");
		HttpSession session = request.getSession() ;
		String userID = (String)session.getAttribute("userID") ;
		
		Date dnow = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String exportTime = sdf.format(dnow);  //入库时间
		int count = 0;
		try {
			count = DataTool.getItemCount(itemID)-number;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String sql1 ="INSERT INTO export(RequestID,StockmanID,RequestmanID,Exporttime)"
				+ " VALUES('"+requestID+"','"+userID+"','"+requestManID+"','"+exportTime+"')";
		String sql2 = "UPDATE `item` SET `ItemsInventory` = "+count
				+ " WHERE `ItemID` = '"+itemID+"'" ;
		String sql3 = "UPDATE `request` SET `Requeststatement` = '"+"完成"+"' "
				+ "WHERE `request`.`RequestID` = '"+requestID+"'" ;
		
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			ps1 = conn.prepareStatement(sql1);
			ps2 = conn.prepareStatement(sql2);
			ps3 = conn.prepareStatement(sql3);
			ps1.executeUpdate();
			ps2.executeUpdate();
			ps3.executeUpdate();
			conn.commit();
			String u = request.getHeader("referer") ;
			String s = " <script type='text/javascript'>alert('插入成功');" ;
			s += "window.setTimeout(window.location='"+u+"',5000); </script></HEAD>" ;
			out.print(s+"</html>");
		} catch (SQLException sqle) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps1 != null) {
					ps1.close();
				}				
				if (ps2 != null) {
					ps2.close();
				}
				if (ps3 != null) {
					ps3.close();
				}
			} catch (Exception e) {
				
			}
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			
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
