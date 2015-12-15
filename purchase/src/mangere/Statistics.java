package mangere;

import java.sql.ResultSet;

import data.DBUtil;

public class Statistics {
	private DBUtil db ;   //数据库操作类
	
	private String userID;//当前使用者ID
	private int count;//累计申请次数
	private int realCount;//累计申请次数(仅审核通过)
	private double requestAccount;//累计申请金额(包括审核与未审核的)
	private double realAccount;//仅申请单审核通过的累计金额
	
	public Statistics(){
		db = new DBUtil() ;
		this.userID="";
		this.count=0;
		this.realCount=0;
		this.requestAccount=0;
		this.realAccount=0;
	}
	
	//从数据库读取
	public void getStatistics() throws Exception{
		String sql = "SELECT requestcount.Count,requestcount.RealCount,requestcount.RequestAccount,requestcount.RealAccount"
				+" FROM requestcount";
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
				this.setCount(rs.getInt(1));
				this.setRealCount(rs.getInt(2));
				this.setRequestAccount(rs.getDouble(3));
				this.setRealAccount(rs.getDouble(4));
		}
	}
	//setter getter 部分set方法内有加法
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count += count;
	}

	public double getRequestAccount() {
		return requestAccount;
	}

	public void setRequestAccount(double requestAccount) {
		this.requestAccount += requestAccount;
	}

	public double getRealAccount() {
		return realAccount;
	}

	public void setRealAccount(double realAccount) {
		this.realAccount += realAccount;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getRealCount() {
		return realCount;
	}

	public void setRealCount(int realCount) {
		this.realCount += realCount;
	}
			
}
