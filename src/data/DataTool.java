package data;

import java.sql.ResultSet;
import java.util.ArrayList;

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
}
