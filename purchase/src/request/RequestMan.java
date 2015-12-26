package request;

import java.sql.ResultSet;
import java.util.ArrayList;

import data.DBUtil;
import data.DataTool;

//这是申请员的功能处理界面
public class RequestMan {
	//基本的数据
	private DBUtil db ;   //数据库操作类
	private String userID ; //用户的ID号
	private ArrayList<RequestNotice> requestNotices ;
	private ArrayList<Request> recRequests ; 
	private ArrayList<Request> requests ;
	
	//身份标识
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	//构造函数变量初始化
	public RequestMan() {
		db = new DBUtil() ;
		requestNotices = new ArrayList<RequestNotice>() ;
		recRequests = new ArrayList<Request>() ;
		requests = new ArrayList<Request>() ;
	}
	//获取通知
	public void setRequestNotices() throws Exception{
		String sql = "select `request`.`RequestID`,`item`.`Itemname`,`request`.`Number`,`request`.`Requeststatement`,`Requesttime` "
				+ "from `request`,`item`  "
				+ "where `request`.`ItemID` = `item`.`ItemID` AND `request`.`Requeststatement` in ('通过','完成','拒绝','到货') AND `request`.`RequestmanID` = '"+userID+"' "
				+ "order by `Requesttime` desc " ;
		
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			int requestID = rs.getInt(1) ;
			String itemName = rs.getString(2) ;
			int number = rs.getInt(3) ;
			String requestStatement = rs.getString(4) ;
			String requestTime = rs.getString(5) ;
			requestNotices.add(new RequestNotice(requestID, itemName, number, requestStatement, requestTime)) ;
		}
	}
	
	public ArrayList<RequestNotice> getRequestNotices(){
		return requestNotices ;
	}
	
	//获取最近的申请
	public void setRecRequests() throws Exception{
		String sql  = "select `request`.`RequestID` , `item`.`Itemname`,`Number`,`request`.`Requeststatement` "
				+ "from `request`,`item` "
				+ "where `request`.`ItemID` = `item`.`ItemID` AND `request`.`Requeststatement` <> '未审核' AND `request`.`RequestmanID` = '"+userID+"' "
				+ "order by `request`.`Requesttime` DESC" ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			int requestID = rs.getInt(1) ;
			String itemName = rs.getString(2) ;
			int number = rs.getInt(3) ;
			String requestStatement = rs.getString(4) ;
			recRequests.add(new Request(requestID, itemName, number, requestStatement)) ;
		}
	}
	
	public ArrayList<Request> getRecRequests(){
		return recRequests ;
	}
	
	//获取申请的详细信息
	public void setRequests() throws Exception{
		String sql = "select `request`.`RequestID`,`item`.`Itemname`,`request`.`Number`,"
				+ "`request`.`Totalaccount`,`user`.`Username`,`request`.`Requeststatement`,`request`.`Requesttime` "
				+ "from `request`,`item` ,`user` "
				+ "where `request`.`ItemID` = `item`.`ItemID` AND `request`.`RequestmanID` = `user`.`UserID` AND `request`.`RequestmanID` = '"+userID+"' "
				+ "order by `request`.`Requesttime` DESC" ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			int requestID = rs.getInt(1) ;
			String itemName = rs.getString(2) ;
			int number = rs.getInt(3) ;
			double account = rs.getDouble(4) ;
			String requestManName = rs.getString(5) ;
			String requestStatement = rs.getString(6) ;
			String requestTime = rs.getString(7) ;
			requests.add(new Request(requestID, itemName, number, account, requestTime, requestManName, requestStatement)) ;
		}
	}
	
	public ArrayList<Request> getRequests(){
		return requests ;
	}
	
	public void setStatus() throws Exception{
		String sql = "SELECT `RequestID`,`DemandlistID` FROM `request` "
				+ "WHERE `request`.`DemandlistID` IS NOT NULL " ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			String requestID = rs.getString(1) ;
			String demandID = rs.getString(2) ;
			String status = DataTool.getStatusOfDemand(demandID) ;
			if("有货".equals(status)){
				String change = "UPDATE `request` SET `request`.`Requeststatement` = '到货' "
						+ "WHERE `request`.`RequestID` = '"+requestID+"' " ;
				db.update(change);
			}
			
		}
	}
	
}
