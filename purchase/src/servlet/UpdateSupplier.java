package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import purchase.Purchase;

public class UpdateSupplier extends HttpServlet {
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
		request.setCharacterEncoding("utf-8");
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		String supplierid=request.getParameter("supplierID");//获取供应商id
		String supplierName=new String(request.getParameter("supplierName").getBytes("ISO-8859-1"),"UTF-8"); //获取供应商名称
		String contacts=new String(request.getParameter("contacts").getBytes("ISO-8859-1"),"UTF-8") ;//获取联系人
		String telnumber=request.getParameter("telnumber");//获取联系方式
		String address=new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8") ;//获取省份
		String moreAdd=new String(request.getParameter("moreAdd").getBytes("ISO-8859-1"),"UTF-8") ;//获取详细地址
		
		int supplierID=Integer.parseInt(supplierid);
		try {
			//修改供应商信息
			purchase.updateSupplier(supplierID, supplierName, contacts, telnumber, address, moreAdd);
			String u = request.getHeader("referer") ;
			String s = " <script type='text/javascript'>alert('修改成功');" ;
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
