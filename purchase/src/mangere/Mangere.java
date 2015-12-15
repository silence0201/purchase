package mangere;

import java.sql.ResultSet;
import java.util.ArrayList;

import data.DBUtil;

//这是总经理功能管理界面
public class Mangere {
	//定义基本的数据
	private DBUtil db ;   //数据库管理对象
	private String userID ;    //定义总经理的ID
	private ArrayList<Demand> demands ;   //记录最近订单信息
	private ArrayList<Order> orders ;   //统计公司的所有订单信息
	private ArrayList<Request> requests ;   ///统计公司的所有需求申请信息
	private ArrayList<RequestNotice> requestNotice ; //保存总经理的通知
	public Mangere() {
		db = new DBUtil() ;
		demands = new ArrayList<Demand>() ;
		orders = new ArrayList<Order>() ;
		requests = new ArrayList<Request>() ;
		requestNotice = new ArrayList<RequestNotice>() ;
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
//		this.userID = "D00001" ;
	}

	//对申请单信息进行获取
	public void setDemands() throws Exception{
		String sql =  "select `demand`.`DemandID`,`demand`.`Number`,`demand`.`Account`,`demand`.`Statement`,`demand`.`DemandTime`,`item`.`Itemname`,`request`.`AuditorID`"
				+ "From `demand`,`item`,`request` "
				+ "WHERE `demand`.`ItemID` = `item`.`ItemID` AND `demand`.`DemandID`=`request`.`DemandlistID` AND `request`.`AuditorID`='"+userID+"'"
						+ "order by `demand`.`DemandTime` desc";
		ResultSet rs = db.select(sql);
		while(rs.next()){
			int demandID=rs.getInt(1);
			int number=rs.getInt(2);
			double account=rs.getDouble(3);
			String statement=rs.getString(4);
			String demandTime=rs.getString(5);
			String itemName=rs.getString(6);
			demands.add(new Demand(demandID, itemName, number, account, statement, demandTime)) ;
		}
	}
	
	public ArrayList<Demand> getDemands(){
		return demands ;
	}
	
	//获取公司所有的订单信息
	public void setOrders() throws Exception{
		String sql = "select `order`.`OrderID`,`order`.`Ordertime`,`order`.`Statement`,`demand`.`Number`,`demand`.`Account`,`item`.`Itemname`"
				+ "From `order`,`demand`,`item` "
				+ "WHERE `order`.`DemandID` = `demand`.`DemandID` and `demand`.`ItemID` = `item`.`ItemID`";
		ResultSet rs = db.select(sql);
		
		while(rs.next()){
			int orderID=rs.getInt(1);
			String orderTime=rs.getString(2);
			String orderStatement=rs.getString(3);
			int number=rs.getInt(4);
			double account=rs.getDouble(5);
			String itemName=rs.getString(6);
			
			orders.add(new Order(orderID, itemName, number, account, orderTime, orderStatement)) ;
		}	
	}
	
	public ArrayList<Order> getOrders(){
		return orders ;
	}
	
	//获取公司的所有申请单的信息
	public void setRequests() throws Exception{
		String sql = "select `request`.`RequestID`,`request`.`Number`,`request`.`Totalaccount`,`request`.`Requesttime`,"
				+ "`request`.`Requeststatement`,`item`.`Itemname`,`u1`.`Username`,`u2`.`Username`"
				+ "From `request`,`item`,`user` AS `u1`,`user` AS `u2` "
				+ "WHERE `request`.`ItemID` = `item`.`ItemID` and `request`.`RequestmanID` = `u1`.`UserID` and  `request`.`AuditorID` = `u2`.`UserID` "
				+ "order by `request`.`Requesttime` desc";
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			int requestID=rs.getInt(1);
			int number=rs.getInt(2);
			double account=rs.getDouble(3);
			String requestTime=rs.getString(4);
			String requestStatement=rs.getString(5);
			String itemName=rs.getString(6);
			String requestManName=rs.getString(7);
			String auditorManName=rs.getString(8);
			
			requests.add(new Request(requestID, itemName, number, account, requestTime, requestManName, auditorManName, requestStatement)) ;
		}	
	}
	
	public  ArrayList<Request> getRequests(){
		return requests ;
	}
	
	//对需要审核的统计进行获取
	public void setRequestNotice() throws Exception{
		String sql = "SELECT `request`.`RequestID`,`item`.`Itemname`,`request`.`Number`,`request`.`Totalaccount`,"
				+ "`request`.`Requeststatement`,`request`.`RequestmanID`,`request`.`Requesttime`  "
				+ "FROM `request`,`item` "
				+ "WHERE `request`.`Requeststatement` = '未审核' AND `request`.`ItemID` = `item`.`ItemID` AND `request`.`Totalaccount` > 5000 "
				+ "ORDER BY `request`.`Requesttime`" ;
		
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			int requestID = rs.getInt(1) ;
			String itemName = rs.getString(2) ;
			int number = rs.getInt(3) ;
			double account = rs.getDouble(4) ;
			String status = rs.getString(5) ;
			String requestMan = rs.getString(6) ;
			String requestTime = rs.getString(7) ;
			
			requestNotice.add(new RequestNotice(requestID, itemName, number, account, status, requestMan, requestTime)) ;
		}
	}
	
	public ArrayList<RequestNotice> getRequestNotice(){
		return requestNotice ;
	}
}
