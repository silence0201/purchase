package stockman;

import java.sql.ResultSet;
import java.util.ArrayList;

import data.DBUtil;

public class Stockman {
	private DBUtil db ;   //数据库操作对象
	private String userID ;   //用户身份标示
	private ArrayList<Stock> stocks ;  // 保存库存状态信息
	private ArrayList<Examine> examines ;  //保存最近记录
	private ArrayList<Import> imports ;   //保存最近的入库信息
	private ArrayList<Export> exports ; //保存最近的出库信息 
	public Stockman() {
		db = new DBUtil() ;
		stocks = new ArrayList<Stock>() ;
		examines = new ArrayList<Examine>() ;
		imports = new ArrayList<Import>() ;
		exports = new ArrayList<Export>() ;
	}
	
	//对外函数
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}

	//查询库存状态，并写入对应的集合
	public void setStock() throws Exception{
		String sql = "select * from item" ;
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			String itemID = rs.getString(1) ;
			String itemName = rs.getString(2) ;
			String price = rs.getString(3) ;
			String reserve = rs.getString(4) ;
			
			stocks.add(new Stock(itemID, itemName, price, reserve)) ;
		}
	}
	
	public ArrayList<Stock> getStock(){
		return stocks ;
	}
	
	//查询最近状态，并存入集合
	public void setExamine() throws Exception{
		String sql = "select `ExportID` as id ,`Itemname` as name ,`Number`*(-1) as count , `Exporttime` as Stime"
				+ " from `export`,`request`,`item` "
				+ "where `item`.`ItemID` = `request`.`ItemID` AND `export`.`RequestID` = `request`.`RequestID` "
				+ "AND `export`.`StockmanID` = '"+userID+"'"
				+ "union "
				+ "select `ImportID` as id , `Itemname` as name ,`Number` as count ,`Importtime` as Stime"
				+ " from `import`,`demand`,`order`,`item`where `import`.`OrderID` = `order`.`OrderID` AND `order`.`DemandID` = `demand`.`DemandID` AND `demand`.`ItemID` = `item`.`ItemID` "
				+ "AND `StockmanID` = '"+userID+"'"
				+ "order by Stime DESC" ;
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			int ID = rs.getInt(1) ;
			String itemName = rs.getString(2) ;
			int number = rs.getInt(3) ;
			String time = rs.getString(4) ;
			examines.add(new Examine(ID, itemName, number, time)) ;
		}
	}
	
	public ArrayList<Examine> getExamine(){
		return examines ;
	}
	
	//获取对应的数据信息
	public void setImport() throws Exception{
		String sql = "select `ImportID`,`import`.`OrderID`,`Itemname`,`Number`,`Importtime` "
				+ "from `import` ,`order`, `demand` ,`item` "
				+ "where `import`.`OrderID` = `order`.`OrderID` AND `order`.`DemandID` = `demand`.`DemandID` "
				+ "AND `demand`.`ItemID` = `item`.`ItemID` AND `StockmanID` = '"+userID+"'"
						+ " order by 'Importtime' DESC" ;
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			int importID = rs.getInt(1) ;
			int orderID = rs.getInt(2) ;
			String itemName = rs.getString(3) ;
			int number = rs.getInt(4) ;
			String importTime = rs.getString(5) ;
			imports.add(new Import(importID, orderID, itemName, number, importTime)) ;
		}
	}
	
	public ArrayList<Import> getImport(){
		return imports ;
	}
	
	//获取出库信息对应的数据
	public void setExport() throws Exception {
		String sql = "select `ExportID`,`export`.`RequestID`,`Itemname`,`Number`,`Username`,`Exporttime` "
				+ "from `export`,`request`,`item`,`user` "
				+ "where `export`.`RequestID` = `request`.`RequestID` AND `export`.`RequestmanID` = `user`.`UserID` "
				+ "AND `request`.`ItemID` = `item`.`ItemID` AND `StockmanID`='"+userID+"' "
				+ "order by `Exporttime` DESC" ;
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			int exportID = rs.getInt(1) ;
			int requestID = rs.getInt(2) ;
			String itemName = rs.getString(3) ;
			int number = rs.getInt(4) ;
			String requestUserName = rs.getString(5) ;
			String exportTime = rs.getString(6) ;
			
			exports.add(new Export(exportID, requestID, itemName, number, requestUserName, exportTime)) ;
		}
	}
	
	public ArrayList<Export> getExport(){
		return exports ;
	}
}
