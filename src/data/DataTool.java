package data;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.exceptions.DeadlockTimeoutRollbackMarker;

public class DataTool {
	//获取仓库的物品的列表
	public static  ArrayList<String> getAllItem() throws Exception{
		ArrayList<String> itemNames = new ArrayList<String>() ;
		DBUtil db = new DBUtil() ;
		String sql = "select Itemname from item " ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			String name = rs.getString(1) ;
			itemNames.add(name) ;
		}
		
		return itemNames ;
	}
	
	//通过名字获取对应商品的价格
	public static double getPriceByName(String name) throws Exception{
		double price = 0 ;
		DBUtil db = new DBUtil() ;
		String sql = "SELECT `item`.`Unitprice` FROM `item` WHERE `item`.`Itemname` = '"+name+"'" ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			price = rs.getDouble(1) ;
		}
		return price;
	}
	
	//通过物品id获取商品的价格
	public static double getPriceByID(String id) throws Exception{
		double price = 0 ;
		DBUtil db = new DBUtil() ;
		String sql = "SELECT `item`.`Unitprice` FROM `item` WHERE `item`.`ItemID` = '"+id+"'" ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			price = rs.getDouble(1) ;
		}
		return price;
	}
	
	//通过物品名称获取物品ID
	public static String getIDByName(String name) throws Exception{
		String id = "" ;
		DBUtil db = new DBUtil() ;
		String sql = "SELECT ItemID FROM item WHERE Itemname='"+name+"'";
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			id = rs.getString(1) ;
		}
		return id ;
	}
	//通过ID获取指定商品的数目
	public static  int getItemCount(String id) throws Exception{
		DBUtil db = new DBUtil() ;
		int count = 0 ;    //物品的数目
		String sql = "SELECT `item`.`ItemsInventory`  FROM `item` WHERE `item`.`ItemID` = '"+id+"' " ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			count = rs.getInt(1) ;
		}
		return count;
	}
	
	//通过物品ID设置物品的数量
	public static void changeItemCount(String id,int count) throws Exception{
		DBUtil db = new DBUtil() ;
		String sql = "UPDATE `item` SET `ItemsInventory` = "+count
				+ " WHERE `ItemID` = '"+id+"'" ;
		db.update(sql);		
	}
	
	//获取申请单的状态
	public static String getStatusOfRequest(String id) throws Exception{
		String status = "" ;
		DBUtil db = new DBUtil() ;
		String sql = "SELECT `request`.`Requeststatement` FROM `request` WHERE `request`.`RequestID` = '"+id+"' " ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			status = rs.getString(1) ;
		}
		return status;
	}
	//修改指定申请单的状态
	public static void changeStatusOfRequest(String id,String status) throws Exception{
		DBUtil db = new DBUtil() ;
		String sql = "UPDATE `request` SET `Requeststatement` = '"+status+"' "
				+ "WHERE `request`.`RequestID` = '"+id+"'" ;
		db.update(sql);
	}
	
	//获取需求单的状态
	public static String getStatusOfDemand(String id) throws Exception{
		String status = "" ;
		DBUtil db = new DBUtil() ;
		String sql = "SELECT `demand`.`Statement` FROM `demand` "
				+ "WHERE `demand`.`DemandID` = '"+id+"' " ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			status = rs.getString(1) ;
		}
		return status;
	}
	
	//更改指定需求单的的状态
	public static void changeStatusOfDemand(String id,String status) throws Exception{
		DBUtil db = new DBUtil() ;
		String sql = "UPDATE `demand` SET `Statement` = '"+status+"' "
				+ "WHERE `demand`.`DemandID` = '"+id+"' " ;
		db.update(sql); 
	}
	
	//获取订单的状态信息
	public static String getStatusOfOrder(String id) throws Exception{
		String status="" ;
		DBUtil db = new DBUtil() ;
		String sql = "SELECT `order`.`Statement` FROM `order` WHERE `order`.`OrderID` = '"+id+"' " ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			status = rs.getString(1) ;
		}
		return status;
	}
	
	//更改指定订单的状态
	public static void changeStatusOfOrder(String id,String status) throws Exception{
		DBUtil db = new DBUtil() ;
		String sql = "UPDATE `order` SET `Statement` = '"+status+"' "
				+ "WHERE `order`.`OrderID` = '"+id+"' " ;
		db.update(sql);
	}
	
	//通过订单id获取指定的需求单id
	public static String getDemandIDByOrderID(String id) throws Exception{
		String demandID = "" ;
		DBUtil db = new DBUtil() ;
		String sql = "SELECT `order`.`DemandID` FROM `order` WHERE `order`.`OrderID` = '"+id+"'" ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			demandID = rs.getString(1) ;
		}
		return demandID ;
	}
	
	//通过需求单ID获取指定的申请单ID
	public static ArrayList<String> getRequestIDByDemandID(String id) throws Exception{
		ArrayList<String> requestIDs = new ArrayList<String>() ;
		DBUtil db = new DBUtil() ;
		String sql = "SELECT `request`.`RequestID` FROM `request` WHERE `request`.`DemandlistID` = '"+id+"' " ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			requestIDs.add(rs.getString(1)) ;
		}
		return requestIDs ;
	}
}
