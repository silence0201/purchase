package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DBUtil;
import data.DataTool;

public class AddRequest extends HttpServlet {

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
	public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		DBUtil db = new DBUtil() ;
		PrintWriter out = response.getWriter();
		String itemName = new String(request.getParameter("itemName").getBytes("ISO-8859-1"),"UTF-8") ;     //获取商品的名称
		String price = request.getParameter("price") ;   //获取商品的单价
		String number = request.getParameter("number") ;  //获取商品的数量
		String account = request.getParameter("Account") ;  //获取商品的总金额
		String reason = request.getParameter("reason") ;   //获取申请的原因
		HttpSession session = request.getSession() ;
		String userID = (String)session.getAttribute("userID") ;   //获取申请人ID
		String itemID = "" ;                                       //获取物品的ID 
		try {
			itemID = DataTool.getIDByName(itemName) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date dnow = new Date() ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String requestTime=sdf.format(dnow) ;			  //获取申请时间
		
		
		String sql="INSERT INTO request(ItemID,Number,Totalaccount,RequestmanID,Requesttime,Requeststatement,Reason)"
				+ " VALUES('"+itemID+"','"+number+"','"+account+"','"+userID+"','"+requestTime+"','未审核','"+reason+"')";
		
		try {
			db.insert(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print("插入成功，即将调回调用页面");
//		response.sendRedirect(request.getHeader("referer"));
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
