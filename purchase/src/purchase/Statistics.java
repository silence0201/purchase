package purchase;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.DBUtil;

public class Statistics {
	private DBUtil db ;   //数据库操作类
	
	private String userID;//当前使用者ID
	private int count;//累计申请次数
	private double account;//累计申请金额
	
	public Statistics(){
		db = new DBUtil() ;
		this.userID="";
		this.count=0;
		this.account=0;

	}
	
	//获取时间
	public String getTime(){
		String time="";
		Date dnow = new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		time=format.format(dnow);
		return time;
	}
	
	//从申请单得到审核金额
	public void getRequest() throws Exception{
		String time=this.getTime();
		String yearMonth=time.substring(0, 7);
		String sql = "SELECT request.Totalaccount"
				+" FROM request"
				+" WHERE request.AuditorID='"+userID+"' AND request.Audittime LIKE '"+yearMonth+"-%'";
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
				this.setAccount(rs.getDouble(1));
				//更新purchasecount
				int mycount=0;
				String sql2 = "SELECT purchasecount.Count"
						+" FROM purchasecount"
						+" WHERE purchasecount.UserID='"+userID+"'";
				ResultSet rs2 = db.select(sql2) ;
				while(rs2.next()){mycount=rs2.getInt(1);}
				mycount++;
				String sql3 = "UPDATE purchasecount"
						+" SET purchasecount.Count="+mycount+",purchasecount.Account="+rs.getDouble(1)
						+" WHERE purchasecount.UserID='"+userID+"'";
				db.update(sql3);
		}
	}
	
	//从purchasecount读取
	public void getStatistics() throws Exception{
		String sql = "SELECT purchasecount.Count,purchasecount.Account"
				+" FROM purchasecount"
				+" WHERE purchasecount.UserID='"+userID+"'";
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
				this.setCount(rs.getInt(1));
				this.setAccount(rs.getDouble(2));
		}
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
		String sql = "UPDATE purchasecount"
				+ " SET purchasecount.Count=0,purchasecount.Account=0";
		db.update(sql);	
	}
	
	//setter getter 部分set方法内有加法
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count += count;
	}
		
	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	
}
