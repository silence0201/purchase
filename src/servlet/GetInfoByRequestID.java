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

public class GetInfoByRequestID extends HttpServlet {

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
				String output = "0";
				//处理接收到的参数，生成响应的XML文档
				String requestID = request.getParameter("requestID") ;
				String sql = "SELECT `request`.`ItemID`,`item`.`Unitprice`,`request`.`Number`,`request`.`Totalaccount` , `request`.`RequestmanID`"
						+ "FROM `request`,`item` "
						+ "WHERE `item`.`ItemID` = `request`.`ItemID` AND `request`.`RequestID` = '"+requestID+"' " ;
				ResultSet rs = null;
				String itemID = "" ;
				double price = 0 ;
				int number = 0 ;
				double account = 0 ;
				String s = "0" ;
				String requestmanID = "" ;
				try {
					rs = db.select(sql) ;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				try {
					while(rs!=null&&rs.next()){
						itemID = rs.getString(1) ;
						price = rs.getDouble(2) ;
						number = rs.getInt(3) ;
						account = rs.getDouble(4) ;
						requestmanID = rs.getString(5) ;
						s=itemID+","+price+","+number+","+account +","+requestmanID;
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
		doPost(request, response);
	}

}
