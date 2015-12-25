package purchase;

import java.sql.ResultSet;

import data.DBUtil;

public class Handle {
	DBUtil db ;
	
	public Handle(){
		db = new DBUtil() ;
	}
	
	public DemandNotice selectByDeamndID(String demandID) throws Exception{
		String sql = "SELECT `demand`.`DemandID`,`demand`.`ItemID`,`item`.`Itemname`,`demand`.`Number`,`item`.`Unitprice`,`demand`.`Account`,`DemandTime`"
				+ "FROM `demand`,`item`"
				+ "WHERE `demand`.`ItemID` = `item`.`ItemID` AND `demand`.`DemandID` = "+demandID ;
		DemandNotice d = null ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			String DemandID = rs.getString(1) ;
			String itemID = rs.getString(2) ;
			String itemName = rs.getString(3) ;
			int number = rs.getInt(4) ;
			double price = rs.getDouble(5) ;
			double account = rs.getDouble(6) ;
			String demandTime = rs.getString(7) ;
			d = new DemandNotice(DemandID, itemID, itemName, number, price, account, demandTime) ;
		}
		return d ;
	}
	
	public Request selectByRequestID(String requestID) throws Exception{
		String sql = "SELECT `request`.`RequestID`,`Itemname`,`request`.`Number`,`request`.`Totalaccount`,"
				+ "`request`.`Requesttime`,`Username` ,`request`.`Reason`"
				+ "FROM `request`,`item`,`user`  "
				+ "WHERE `request`.`ItemID` = `item`.`ItemID` AND "
				+ "`request`.`RequestmanID` = `user`.`UserID` AND `request`.`RequestID` = "+requestID ;
		Request rq = new Request() ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			int RequestID = rs.getInt(1) ;
			String itemName = rs.getString(2) ;
			int number = rs.getInt(3) ;
			double account = rs.getDouble(4) ;
			String requestTime = rs.getString(5) ;
			String userName = rs.getString(6) ;
			String reason = rs.getString(7) ;
			rq = new Request(RequestID, itemName, number, account, requestTime, userName,reason) ;
		}
		return rq ;
	}
}
