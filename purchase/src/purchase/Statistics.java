package purchase;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import data.DBUtil;

public class Statistics {
	private DBUtil db ;   //数据库操作类
	
	private String userID;//当前使用者ID
	private int count;//累计申请次数
	private double account;//累计申请金额
	private boolean isNull;//用于判断是否存在添加进统计的订单
	private ArrayList<Demand> demand;
	
	public Statistics(){
		db = new DBUtil() ;
		this.userID="";
		this.count=0;
		this.account=0;
		this.isNull=false;
		this.demand=new ArrayList<Demand>();
	}
	
	//初始化count account demand
	public void initialize(){
		this.count=0;
		this.account=0;
		this.isNull=false;
		this.demand=new ArrayList<Demand>();
	}
	
	//获取时间
	public String getTime(){
		String time="";
		Date dnow = new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		time=format.format(dnow);
		return time;
	}
	
	//获取并未统计的订单中的需求单id
	public void getDemandID() throws Exception{
		String time=this.getTime();
		String yearMonth=time.substring(0, 7);
		
		String sql="SELECT DemandID,IsStatistics"
				+" FROM `order`"
				+" WHERE orderManID='"+this.userID+"' AND IsStatistics=0 AND Ordertime LIKE '"+yearMonth+"-%'";
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			if(rs.getInt(2)==0){
				this.isNull=true;
				this.demand.add(new Demand(rs.getInt(1)));
				//更改订单中IsStatistics参数为1 表示该订单已进入采购统计
				this.updateIsStatistics(rs.getInt(1));
			}
			else{
				this.isNull=false;
			}
		}
	}
	//更改订单中IsStatistics参数为1 表示该订单已进入采购统计
	public void updateIsStatistics(int demandid) throws Exception{
		String sql="UPDATE `order`"
				+" SET IsStatistics=1"
				+" WHERE DemandID='"+demandid+"'";
		db.update(sql);
	}
	
	//获取需求单的金额,并计数
	public void getDemandAccount() throws Exception{
		//获取需求单id
		this.getDemandID();
		if(isNull){
			//获取采购统计
			int myCount=this.getPurchasecountCount();
			double myAccount=0;
			Iterator<Demand> itDemand=demand.iterator();
			while(itDemand.hasNext()){
				Demand demandid=itDemand.next();
				String sql="SELECT Account"
						+" FROM demand"
						+" WHERE DemandID='"+demandid.getDemandID()+"'";
				ResultSet rs = db.select(sql) ;
				while(rs.next()){
					myAccount+=rs.getDouble(1);
					myCount++;
				}
			}
			//更新purchasecount表
			this.updatePurchasecount(myCount,myAccount);
			this.isNull=false;
		}
	}
	//获取采购统计
	public int getPurchasecountCount() throws Exception{
		int myCount=0;
		String sql="SELECT Count"
				+" FROM purchasecount"
				+" WHERE UserID='"+this.userID+"'";
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			myCount=rs.getInt(1);
		}
		return myCount;
	}
	
	//更新purchasecount表
	public void updatePurchasecount(int myCount,double myAccount) throws Exception{
		String sql="UPDATE purchasecount"
				+" SET purchasecount.Count="+myCount+",purchasecount.Account="+myAccount
				+" WHERE purchasecount.UserID='"+this.userID+"'";
		db.update(sql);
	}
				
	//从purchasecount读取(外部调用)
	public void getStatistics() throws Exception{
		//判断是否需要统计清零
		String myday=this.getTime().substring(8);
		int day=Integer.parseInt(myday);
		if(this.getClear(day)){
			this.clearStatistics();
		}
		//初始化
		this.initialize();
		
		String sql = "SELECT purchasecount.Count,purchasecount.Account"
				+" FROM purchasecount"
				+" WHERE purchasecount.UserID='"+userID+"'";
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
				this.setCount(rs.getInt(1));
				this.setAccount(rs.getDouble(2));
		}
		//获取需求单的金额,并计数
		this.getDemandAccount();
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
		this.account += account;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
