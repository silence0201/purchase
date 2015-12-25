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

public class GetInfoByItemID extends HttpServlet {

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
				String itemID = request.getParameter("itemID") ;
				String supplierID = request.getParameter("supplierID") ;
				String sql = "SELECT `item`.`Itemname`,`item`.`Unitprice`,`relationship`.`Quality`,`relationship`.`Statement`  "
						+ "FROM `relationship`,`item` WHERE `item`.`ItemID` = `relationship`.`ItemID` AND "
						+ "`relationship`.`SupplierID` = '"+supplierID+"' AND `relationship`.`ItemID` = '"+itemID+"' " ;
				ResultSet rs = null;
				String itemName = "" ;
				double price = 0 ;
				String quality = "" ;
				String s = "0" ;
				String status = "" ;
				try {
					rs = db.select(sql) ;
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
				try {
					while(rs!=null&&rs.next()){
						itemName = rs.getString(1) ;
						price = rs.getDouble(2) ;
						quality = rs.getString(3) ;
						status = rs.getString(4) ;
						s=itemName+","+price+","+quality+","+status;
					}
				} catch (SQLException e) {
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
