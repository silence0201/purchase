package purchase;
// 本类用来管理所有purchase的查询的数据

import java.sql.ResultSet;
import java.util.ArrayList;

import data.DBUtil;

public class Purchase {
	private DBUtil db ;   //数据库操作对象
	private String userID ; //用户身份
	private ArrayList<Approve> approves ;   //保存最近申请信息的记录的集合
	private ArrayList<Order> orders ;   //保存最近的订单信息
	private ArrayList<Supplier> suppliers ;  //保存供应商的信息
	
	//无参构造，初始化变量
	public Purchase() {
		db = new DBUtil() ;
		approves = new  ArrayList<Approve>() ;
		orders = new ArrayList<Order>() ;
		suppliers = new ArrayList<Supplier>() ;
	}

	//用户身份进行填充以及获取
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	//得到approve的结果
	public void setApprove() throws Exception{
		String sql = "select RequestID,Itemname,Number,Totalaccount,Requeststatement "
				+ "from request,item "
				+ "where request.ItemID = item.ItemID AND request.AuditorID = '"+ userID +"'" ; 
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			int requestID = rs.getInt(1) ;  //申请ID
			String itemName = rs.getString(2) ;  //物品的名称
			int count = rs.getInt(3); //物品的数量
			double price = rs.getDouble(4);   //物品的价格
			String status = rs.getString(5);  //订单的状态
			approves.add(new Approve(requestID,itemName,count,price,status)) ;   //将记录添加到集合
		}
	}
	
	public ArrayList<Approve> getApprove(){
		return approves ;
	}

	//得到订单的基本信息
	public void setOrder() throws Exception{
		String sql = "SELECT `OrderID`,`Itemname`,`Number`,`Unitprice`,`Account`,`Ordertime`,`order`.`Statement`"
				+ "from `order`,`demand`,`item` "
				+ "where `order`.`DemandID` = `demand`.`DemandID` AND `demand`.`ItemID` = `item`.`ItemID` "
				+ "AND `orderManID`='"+userID+"'"
				+ "order by `Ordertime`" ;
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			int orderID = rs.getInt(1) ;
			String itemName = rs.getString(2) ;
			int number = rs.getInt(3) ;
			double utilPrice = rs.getDouble(4) ;
			double account = rs.getDouble(5) ;
			String orderTime = rs.getString(6) ;
			String statue = rs.getString(7) ;
			Order order = new Order(orderID, itemName, number, utilPrice, account, orderTime, statue) ;
			orders.add(order) ;
		}
	}
	
	public ArrayList<Order> getOrder(){
		return orders ;
	}

	
	//供应商信息的处理
	public void setSupplier() throws Exception{
		String sql = "select * from supplier" ;
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			String supplierID = rs.getString(1) ;
			String supplierName = rs.getString(2) ;
			String contact = rs.getString(3) ;
			String tel = rs.getString(4) ;
			String add = rs.getString(5) ;
			suppliers.add(new Supplier(supplierID, supplierName, contact, tel, add)) ;
		}
	}
	
	public ArrayList<Supplier> getSupplier(){
		return suppliers ;
	}
}
