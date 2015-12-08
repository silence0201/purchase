package stockman;

import java.sql.ResultSet;

import data.DBUtil;

public class AddExport {
	DBUtil db=new DBUtil();
	//基本的数据信息
		private String itemID ;   //物品ID
		private int requestID ;   // 申请单ID
		private int number ;     //商品的数量
		private String exportTime ; // 导入的时间
		
		private String requestManName;//申请人姓名
		private String userID;//使用者id
		
		public AddExport() {
			super();
			this.itemID = "";
			this.requestID = 0;
			this.number = 0;
			this.exportTime = "";
			this.userID="";
		}
		
		//getter 和 setter方法
		public String getItemID() {
			return itemID;
		}
		public void setItemID(String importID) {
			this.itemID = importID;
		}
		public int getRequestID() {
			return requestID;
		}
		public void setRequestID(int requestID) {
			this.requestID = requestID;
		}
		public int getNumber() {
			return number;
		}
		public void setNumber(int number) {
			this.number = number;
		}
		public String getExportTime() {
			return exportTime;
		}
		public void setExportTime(String exportTime) {
			this.exportTime = exportTime;
		}
		
		//
		public void setUserID(String userID) {
			this.userID = userID;
		}
		//
		public void setImportSql() throws Exception{
			//库存数量变化(未使用)
			String itemsInventorySql="SELECT ItemsInventory FROM item WHERE ItemID='"+this.itemID+"'";
			ResultSet rs = db.select(itemsInventorySql) ;
			int itemsInventory=0;
			while(rs.next()){
				itemsInventory = rs.getInt(1) ;	
			}
			this.number=itemsInventory-this.number;
			//根据申请人姓名查询申请人id
			String requestManIDSql="SELECT UserID FROM user WHERE Username='"+this.requestManName+"'";
			ResultSet rs2 = db.select(requestManIDSql) ;
			String requestManID="R00042";
			while(rs2.next()){
				requestManID = rs.getString(1) ;	
			}
			//往出库单插入数据
			String sql="INSERT INTO export(RequestID,StockmanID,RequestmanID,Exporttime)"
					+ " VALUES('"+this.requestID+"','"+this.userID+"','"+requestManID+"','"+this.exportTime+"')";
			db.insert(sql);	
		}
}
