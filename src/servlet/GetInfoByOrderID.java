package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DBUtil;

public class GetInfoByOrderID extends HttpServlet {

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
		//设置生成文件的类型和编码格式
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		DBUtil db = new DBUtil() ;
		PrintWriter out = response.getWriter();
		String output = "";
		String orderID = request.getParameter("orderID") ;
		String sql = "SELECT `demand`.`ItemID`,`demand`.`Number`,`demand`.`Account`,`item`.`Unitprice` "
				+ "FROM `order`,`demand`,`item` WHERE `order`.`DemandID` = `demand`.`DemandID` "
				+ "AND `demand`.`ItemID` = `item`.`ItemID` AND `order`.`OrderID` = '"+orderID+"' " ;
		ResultSet rs = null;
		String itemID = "" ;
		double price = 0 ;
		int number = 0 ;
		double account = 0 ;
		String s = "0" ;
		try {
			rs = db.select(sql) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			while(rs!=null&&rs.next()){
				itemID = rs.getString(1) ;
				price = rs.getDouble(4) ;
				number = rs.getInt(2) ;
				account = rs.getDouble(3) ;
				s=itemID+","+price+","+number+","+account ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		output ="<response>"+s+"</response>";
		out.println(output);
		out.close();
		
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
