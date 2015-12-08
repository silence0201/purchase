package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DBUtil;
import data.DataTool;

public class AddImport extends HttpServlet {

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
		//设计页面的基本信息
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		DBUtil db = new DBUtil() ;
		PrintWriter out = response.getWriter();
		
		String orderID = request.getParameter("orderID") ;  //获取入库的订单ID
		String itemID = request.getParameter("itemID") ;  //获取物品的id
		String numberStr = request.getParameter("number") ;  //获取物品的数量
		int number = Integer.parseInt(numberStr) ;
		HttpSession session = request.getSession() ;
		
		String userID =(String) session.getAttribute("userID") ;  //获取用户的身份ID
		Date dnow = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String importTime = sdf.format(dnow);
		
		//往入库单插入数据
		String sql="INSERT INTO import(OrderID,StockmanID,Importtime)"
				+ " VALUES('"+orderID+"','"+userID+"','"+importTime+"')";
		try {
			db.insert(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		//商品数目进行同步处理
		try {
			DataTool.changeItemCount(itemID, DataTool.getItemCount(itemID)+number);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//对订单的状态进行更新
		try {
			DataTool.changeStatusOfOrder(orderID, "采购完成");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//对需求单的状态进行更新
		String demandID = null;
		try {
			demandID = DataTool.getDemandIDByOrderID(orderID)  ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			DataTool.changeStatusOfDemand(demandID, "有货");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		out.print("插入成功，即将调回调用页面");
		String u = request.getHeader("referer") ;
		out.print("<a href="+u+">");
		out.print("返回");
		out.print("</a>");
		
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
