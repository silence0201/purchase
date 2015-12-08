package stockman;

import java.sql.ResultSet;

import data.DBUtil;

public class AddImport {
	DBUtil db=new DBUtil();
	//基本的数据信息
		private String itemID ;   //物品ID
		private int orderID ;   // 订单ID
		private int number ;     //商品的数量
		private String importTime ; // 导入的时间
		
		private String userID;//使用者id
		
		public AddImport() {
			super();
			this.itemID = "";
			this.orderID = 0;
			this.number = 0;
			this.importTime = "";
			this.userID="";
		}
		
		//getter 和 setter方法
		public String getItemID() {
			return itemID;
		}
		public void setItemID(String importID) {
			this.itemID = importID;
		}
		public int getOrderID() {
			return orderID;
		}
		public void setOrderID(int orderID) {
			this.orderID = orderID;
		}
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public String getImportTime() {
			return importTime;
		}
		public void setImportTime(String importTime) {
			this.importTime = importTime;
		}
		
		//
		public void setUserID(String userID) {
			this.userID = userID;
		}
		public void setImportSql() throws Exception{
			//库存数量变化(未使用)
			String itemsInventorySql="SELECT ItemsInventory FROM item WHERE ItemID='"+this.itemID+"'";
			ResultSet rs = db.select(itemsInventorySql) ;
			int itemsInventory=0;
			while(rs.next()){
				itemsInventory = rs.getInt(1) ;	
			}
			this.number=this.number+itemsInventory;
			//往入库单插入数据
			String sql="INSERT INTO import(OrderID,StockmanID,Importtime)"
					+ " VALUES('"+this.orderID+"','"+this.userID+"','"+this.importTime+"')";
			db.insert(sql);	
		}
}
