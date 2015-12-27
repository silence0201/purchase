package request;

import java.sql.ResultSet;

import data.DBUtil;

public class Statistics {
	
	private DBUtil db ;   //数据库操作类
	private String userID;//当前使用者ID
	private int count;//累计申请次数
	private int realCount;//累计申请次数(仅审核通过)
	private double requestAccount;//累计申请金额(包括审核与未审核的)
	private double realAccount;//仅申请单审核通过的累计金额
	private double remainAccount;//预算剩余
	
	public Statistics(){
		db = new DBUtil() ;
		this.userID="";
		this.count=0;
		this.realCount=0;
		this.remainAccount=30000;
		this.requestAccount=0;
		this.realAccount=0;
	}
	//从数据库读取
	public void getStatistics() throws Exception{
		String sql = "SELECT requestcount.Count,requestcount.realCount,requestcount.RequestAccount,requestcount.RealAccount,requestcount.RemainAccount"
				+" FROM requestcount"
				+" WHERE requestcount.UserID='"+userID+"'";
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			this.setCount(rs.getInt(1));
			this.setRealCount(rs.getInt(2));
			this.setRequestAccount(rs.getDouble(3));
			this.setRealAccount(rs.getDouble(4));
			this.setRemainAccount(rs.getDouble(5));
		}
	}
	//更新数据库数据
	public void setStatistics(double account) throws Exception{
		this.getStatistics();
		this.count++;
		this.realCount++;
		this.requestAccount+=account;
		this.realAccount+=account;
		this.remainAccount-=account;
		String sql = "UPDATE requestcount"
				+ " SET requestcount.Count="+this.count+",requestcount.RealCount="+this.realCount+",requestcount.RequestAccount="+this.requestAccount+",requestcount.RealAccount="+this.realAccount+",requestcount.RemainAccount="+this.remainAccount
				+ " WHERE UserID='"+userID+"'";
		db.update(sql);	
	}
	//更改申请单数量修改后的统计
	public void UpdateStatistics(double oldAccount,double newAccount) throws Exception{
		this.getStatistics();
		double account=newAccount-oldAccount;
		this.realAccount+=account;
		this.remainAccount-=account;
		String sql = "UPDATE requestcount"
				+ " SET requestcount.RealAccount="+this.realAccount+",requestcount.RemainAccount="+this.remainAccount
				+ " WHERE UserID='"+userID+"'";
		db.update(sql);	
	}
	
	//更改申请单被拒后的统计
	public void UpdateRefuseStatistics(double account) throws Exception{
		this.getStatistics();
		this.changeRealAccount(account);
		this.changeRealCount();
	}
	
	//realAccount默认为申请单通过  此函数用于修改不通过的情况(jsp并未调用该函数)
	public void changeRealAccount(double account) throws Exception{
		//存realaccount
		this.realAccount-=account;
		this.remainAccount+=account;
		String sql = "UPDATE requestcount"
				+ " SET requestcount.RealAccount="+this.realAccount+",requestcount.RemainAccount="+this.remainAccount
				+ " WHERE UserID='"+userID+"'";
		db.update(sql);	
	}
		
	//realcount默认为申请单通过  此函数用于修改不通过的情况(jsp并未调用该函数)
	public void changeRealCount() throws Exception{
		//存realcount
		this.realCount-=1;
		String sql = "UPDATE requestcount"
				+ " SET requestcount.RealCount="+this.realCount
				+ " WHERE UserID='"+userID+"'";
		db.update(sql);	
	}
	
	//判断是否需要重置
	public boolean getClear(int date) throws Exception{
		int clear=0;//1代表需要重置 0为不需要重置
		String sql = "SELECT clear.PurchaseClear"
				+ " FROM clear";
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			clear=rs.getInt(1);
		}
		if(clear==1&&date==1){
			String sql2 = "UPDATE clear"
					+ " SET clear.PurchaseClear=0";
			db.update(sql2);
			return true;
		}
		if(clear==0&&date!=1){
			String sql2 = "UPDATE clear"
					+ " SET clear.PurchaseClear=1";
			db.update(sql2);
			return false;
		}
		return false;
	}
	
	//每月一号重置数据
	public void clearStatistics() throws Exception{
		
		String sql = "UPDATE requestcount"
				+ " SET requestcount.Count=0,requestcount.realCount=0,requestcount.RequestAccount=0,requestcount.RealAccount=0,requestcount.RemainAccount=30000";
		db.update(sql);	
	}
	//getter setter
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getRequestAccount() {
		return requestAccount;
	}
	public void setRequestAccount(double requestAccount) {
		this.requestAccount = requestAccount;
	}
	public double getRemainAccount() {
		return remainAccount;
	}
	public void setRemainAccount(double remainAccount) {
		this.remainAccount = remainAccount;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public void setRealAccount(double realAccount) {
		this.realAccount = realAccount;
	}
	public double getRealAccount() {
		return realAccount;
	}
	public int getRealCount() {
		return realCount;
	}
	public void setRealCount(int realCount) {
		this.realCount = realCount;
	}				
	
}
