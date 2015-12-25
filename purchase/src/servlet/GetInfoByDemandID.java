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

public class GetInfoByDemandID extends HttpServlet {

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
				String s = "0" ;
				String demandID = request.getParameter("demandID") ;
				String sql = "SELECT * FROM `demand` WHERE `demand`.`DemandID` = "+demandID ;
				ResultSet rs = null ;
				try {
					rs = db.select(sql);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String itemID = "" ;
				int number ;
				double account ;
				String status ="" ;
				
				
				try {
					while(rs.next()){
						itemID = rs.getString(2) ;
						number = rs.getInt(3) ;
						account = rs.getDouble(4) ;
						status = rs.getString(5) ;
						s = itemID+","+number+","+account+","+status ;
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
