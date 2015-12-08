package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
		
		HttpSession session = request.getSession() ;
		String userID = (String)session.getAttribute("userID") ;
		
		Date dnow = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String exportTime = sdf.format(dnow);  //入库时间
		
		//往出库单插入数据
		String sql="INSERT INTO export(RequestID,StockmanID,RequestmanID,Exporttime)"
				+ " VALUES('"+requestID+"','"+userID+"','"+requestManID+"','"+exportTime+"')";
		try {
			db.insert(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//商品数目进行同步处理
		try {
			DataTool.changeItemCount(itemID, DataTool.getItemCount(itemID)-number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//对订单的状态进行跟新
		try {
			DataTool.changeStatusOfRequest(requestID, "完成");
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
