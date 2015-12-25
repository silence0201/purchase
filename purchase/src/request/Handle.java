package request;

import java.sql.ResultSet;

import data.DBUtil;

public class Handle {
	DBUtil db  ;   //获得数据库操纵类
	
	
	public Handle() {
		db = new DBUtil() ;
	}


	public Request selectByRequestID(String requestID) throws Exception{
		String sql = "SELECT `request`.`RequestID`,`Itemname`,`request`.`Number`,`request`.`Totalaccount`,"
				+ "`request`.`Requesttime`,`request`.`Requeststatement`,`Username` ,`request`.`Reason`"
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
			String requestStatus = rs.getString(6) ;
			String userName = rs.getString(7) ;
			String reason = rs.getString(8) ;
			rq =new Request(RequestID, itemName, number, account, requestTime, requestStatus, userName,reason)  ;
		}
		return rq ;
	}
}
