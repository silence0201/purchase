package mangere;

import java.sql.ResultSet;

import data.DBUtil;

public class Handle {
	//统计基本数据
	private DBUtil db ;
	
	public Handle() {
		db = new DBUtil() ;
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
