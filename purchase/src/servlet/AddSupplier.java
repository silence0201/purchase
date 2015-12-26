package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import purchase.Purchase;
import data.DBUtil;

public class AddSupplier extends HttpServlet {
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
		//DBUtil db = new DBUtil() ;
		Purchase purchase=new Purchase();
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		String supplierName=request.getParameter("supplierName");//获取供应商名称
		String contacts=request.getParameter("contacts");//获取联系人
		String telnumber=request.getParameter("telnumber");//获取联系方式
		String address=request.getParameter("address");//获取省份
		String moreAdd=request.getParameter("extra");//获取详细地址
		
		try {
			//添加新供应商
			purchase.insertSupplier(supplierName, contacts, telnumber, address, moreAdd);
			String u = request.getHeader("referer") ;
			String s = " <script type='text/javascript'>alert('添加成功');" ;
			s += "window.setTimeout(window.location='"+u+"',5000); </script></HEAD>" ;
			out.print(s+"</html>");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
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
