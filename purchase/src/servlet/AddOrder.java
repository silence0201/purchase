package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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

public class AddOrder extends HttpServlet {

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
		
//		String orderID = request.getParameter("orderID") ;
		String demandID = request.getParameter("demandID") ;
		
		String status = "" ;
		try {
			status = DataTool.getStatusOfDemand(demandID);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if("需采购".equals(status)){
			Date dnow = new Date() ;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String currentTime=sdf.format(dnow) ;	
			HttpSession session = request.getSession() ;
			String orderManID = (String)session.getAttribute("userID") ;
			
			String sql1 = "INSERT INTO `order`(`DemandID`,`Ordertime`,`orderManID`)  "
					+ "VALUE('"+demandID+"','"+currentTime+"','"+orderManID+"') " ;
			String sql2 = "UPDATE `demand` SET `Statement` = '采购中' "
					+ "WHERE `demand`.`DemandID` = '"+demandID+"'" ;
			
			try {
				db.insert(sql1);
				db.update(sql2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sql3 = "SELECT `OrderID` FROM `order` WHERE `DemandID` = '"+demandID+"'" ;
			ResultSet rs = null ;
			try {
				rs = db.select(sql3) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				while(rs!=null&&rs.next()){
					String orderID = rs.getString(1) ;
					String u = request.getHeader("referer") ;
					String s = " <script type='text/javascript'>alert('生成的订单号为"+orderID+"');" ;
					s += "window.setTimeout(window.location='"+u+"',5000); </script></HEAD>" ;
					out.print(s+"</html>");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}else{
			String u = request.getHeader("referer") ;
			String s = " <script type='text/javascript'>alert('请检查对应订单的状态！');" ;
			s += "window.setTimeout(window.location='"+u+"',5000); </script></HEAD>" ;
			out.print(s+"</html>");
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
