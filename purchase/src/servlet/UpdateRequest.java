package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data.DBUtil;
import data.DataTool;

public class UpdateRequest extends HttpServlet {
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
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>这是一个修改申请单的处理页面</TITLE></HEAD>");
		String itemName = new String(request.getParameter("itemName").getBytes("ISO-8859-1"),"UTF-8") ;     //获取商品的名称
		String requestID = request.getParameter("requsetID");
		String number = request.getParameter("number") ;  //获取商品的数量
		HttpSession session = request.getSession() ;
		String userID = (String)session.getAttribute("userID") ;   //获取申请人ID
		String itemID = "" ; 
		String status = "" ;
		String s = "" ;
		//获取物品的ID 
		try {
			itemID = DataTool.getIDByName(itemName) ;
			status = DataTool.getStatusOfRequest(requestID) ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取商品单价
		double unitPrice=0;//单价
		String sql="SELECT item.Unitprice"
				+ " FROM item"
				+ " WHERE item.ItemID='"+itemID+"'";
		try {
			ResultSet rs = db.select(sql);
			while(rs.next()){
				unitPrice=rs.getDouble(1);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		String u = request.getHeader("referer") ;
		//修改申请单信息
		int num=Integer.parseInt(number);//String->int
		double totalAccount = num*unitPrice;
		if("未审核".equals(status)){
			sql="UPDATE request SET ItemID='"+itemID+"',Number='"+num+"',Totalaccount='"+totalAccount+"'"
					+ " WHERE RequestID='"+requestID+"' AND Requeststatement='未审核'";
			try {
				db.update(sql);
				//后续代码
				
				s = " <script type='text/javascript'>alert('修改成功');" ;
				s += "window.setTimeout(window.location='"+u+"',5000); </script></HEAD>" ;
				s += "</html>" ;
				//				out.print(s+"</html>");
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			s = " <script type='text/javascript'>alert('你要修改的订单已经" ;
			s += status+",不能进行修改');" ;
			s += "window.setTimeout(window.location='"+u+"',5000); </script></HEAD>" ;
			s += "</html>" ;
		}
		
		out.print(s);
		
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
