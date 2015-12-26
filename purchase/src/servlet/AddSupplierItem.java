package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import purchase.Purchase;

public class AddSupplierItem extends HttpServlet {
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
		Purchase purchase=new Purchase();
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		String supplierid=request.getParameter("supplierID");//获取供应商id
		String itemName=new String(request.getParameter("itemName").getBytes("ISO-8859-1"),"UTF-8");//获取商品名称
		String quality=new String(request.getParameter("quality").getBytes("ISO-8859-1"),"UTF-8");//获取商品质量
		String price=request.getParameter("price");//获取商品单价
		String test=new String(request.getParameter("test").getBytes("ISO-8859-1"),"UTF-8");//获取物品状态
		
		int supplierID=Integer.parseInt(supplierid);
		double account=Double.parseDouble(price);
		try {
			//现有供应商添加提供商品
			purchase.insertSupplierItem(supplierID, itemName, quality, test,account);

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
