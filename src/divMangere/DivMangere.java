package divMangere;

import java.sql.ResultSet;
import java.util.ArrayList;

import data.DBUtil;


//用于获取部门经理所需的数据
public class DivMangere {
	//主要的数据对象
	DBUtil db ; 
	String userID ;
	ArrayList<ReceExamine> receExamines ;
	ArrayList<Request> requests ;
	ArrayList<Examine> examines ;
	
	
	//构造函数初始化
	public DivMangere() {
		db = new DBUtil() ;
		receExamines = new ArrayList<ReceExamine>() ;
		requests = new ArrayList<Request>() ;
		examines = new ArrayList<Examine>() ;
	}

	
	//身份标识
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	//获得对应的部门
	public String getPosition() throws Exception{
		String sql = "select `user`.`Position`  "
				+ "from `user` "
				+ "where `user`.`UserID` = '"+ userID + "'" ;
		String position = "" ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			position = rs.getString(1) ;
		}
		return position ;
	}
	
	//获取最近的审核记录
	public void setReceExamines() throws Exception{
		String sql = "select `request`.`RequestID`,`item`.`Itemname`,`request`.`Number`,`request`.`Totalaccount`,`user`.`Username`,`Audittime` "
				+ "from `request`,`item`,`user` "
				+ "where `request`.`ItemID` = `item`.`ItemID` AND `request`.`RequestmanID` = `user`.`UserID` AND `request`.`AuditorID` = '"+userID+"'"
				+ "order by `Audittime` desc" ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			String requestID = rs.getString(1) ;
			String itemName = rs.getString(2) ;
			int number = rs.getInt(3) ;
			double account = rs.getDouble(4) ;
			String requestName = rs.getString(5) ;
			String requestTime = rs.getString(6) ;
			receExamines.add(new ReceExamine(requestID, itemName, number, account, requestName, requestTime)) ;
		}
	}
	
	public ArrayList<ReceExamine> getReceExamines(){
		return receExamines ;
	}
	
	//获取本部门的需求申请
	public void setRequests() throws Exception{
		String[] s = getPosition().split("经") ;
		String position = s[0] ;
		String sql = "select `request`.`RequestID`,`Itemname`,`request`.`Number`,`request`.`Totalaccount`,`request`.`Requesttime`,`request`.`Requeststatement`,`Username` "
				+ "from `request`,`item`,`user` "
				+ "where `request`.`ItemID` = `item`.`ItemID` AND `request`.`RequestmanID` = `user`.`UserID` "
				+ "AND `user`.`Position` like '"+position+"%' " ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			String requestID = rs.getString(1) ;
			String itemName = rs.getString(2) ;
			int number = rs.getInt(3) ;
			double account = rs.getDouble(4) ;
			String requestTime = rs.getString(5) ;
			String requestStatus = rs.getString(6) ;
			String userName = rs.getString(7) ;
			requests.add(new Request(requestID, itemName, number, account, requestTime, requestStatus, userName)) ;
		}
	}
	
	public ArrayList<Request> getRequests(){
		return requests ;
	}
	
	//获得指定部门的申请审核信息
	public void setExamines() throws Exception{
		String[] s = getPosition().split("经") ;
		String position = s[0] ;
		String sql = "select `request`.`RequestID`,`item`.`Itemname`,`request`.`Number`,`request`.`Totalaccount`,"
				+ "r.`Username`,`request`.`Requeststatement`,d.`Username`,`request`.`Requesttime`,`request`.`Audittime` "
				+ "from `request`,`item`,`user` as r,`user`as d "
				+ "where `request`.`ItemID` = `item`.`ItemID` AND r.`UserID` = `request`.`RequestmanID` AND "
				+ "d.`UserID` = `request`.`AuditorID` AND r.`Position` like '"+position+"%'" 
				+"order by `Audittime` desc" ; 
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			String requestID = rs.getString(1) ;
			String itemName = rs.getString(2) ;
			int number = rs.getInt(3) ;
			double account = rs.getDouble(4) ;
			String requestName = rs.getString(5) ;
			String requestStatus = rs.getString(6) ;
			String auditorName = rs.getString(7) ;
			String requestTime = rs.getString(8) ;
			String auditTime = rs.getString(9) ;
			examines.add(new Examine(requestID, itemName, number, account, requestName, requestTime, requestStatus, auditorName, auditTime)) ;
		}
	}
	
	public ArrayList<Examine> getExamines(){
		return examines ;
	}
	
}
