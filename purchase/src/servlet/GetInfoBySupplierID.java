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
import purchase.Purchase;

public class GetInfoBySupplierID extends HttpServlet {

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
		PrintWriter out = response.getWriter();
		String output = "0";
		DBUtil db = new DBUtil() ;
		//处理接收到的参数，生成响应的XML文档
		String supplierID = request.getParameter("supplierID") ;
		String sql = "SELECT * FROM `supplier` WHERE `supplier`.`SupplierID` = '"+supplierID+"' " ;
		ResultSet rs = null;
		String supplierName = "" ;
		String contact = "" ;
		String tel = "" ;
		String add = "" ;
		String s = "0" ;
		String moreAdd = "" ;
		try {
			rs = db.select(sql) ;
		} catch (Exception e) {
			e.printStackTrace();
		} 
		try {
			while(rs!=null&&rs.next()){
				supplierName = rs.getString(2) ;
				contact = rs.getString(3) ;
				tel = rs.getString(4) ;
				add = rs.getString(5) ;
				moreAdd = rs.getString(6) ;
				s=supplierName+","+contact+","+tel+","+add +","+moreAdd;
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
