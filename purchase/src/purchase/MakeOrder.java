package purchase;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import data.DBUtil;

public class MakeOrder {
	private DBUtil db;
	private String orderTime;//订单单生成时间
	private String userID;
	private ArrayList<Demand> demand;//主要用于存放需要生成订单的需求单id
			
	public MakeOrder() {
		super();
		db=new DBUtil();
		this.orderTime="";
		this.userID="";
		demand=new ArrayList<Demand>();
	}
	//获取当前时间
	public String getCurrentTime(){
		Date dnow = new Date() ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentTime=sdf.format(dnow) ;	
		this.orderTime=currentTime;
		return currentTime;
	}
	//生成订单外部调用
	public void setOrder() throws Exception{
		String currentTime=this.getCurrentTime();
		//将需要生成订单的需求单id存入链表
		this.getNeedPurchaseDemandID();
		Iterator<Demand> itDemand=demand.iterator();
		while(itDemand.hasNext()){
			Demand de=itDemand.next();
			String sql="INSERT INTO `order`(Statement,Ordertime,DemandID,orderManID)"
					+" VALUES('采购中','"+currentTime+"','"+de.getDemandID()+"','"+this.userID+"')";
			db.insert(sql);
			//更改需求单状态
			this.updateDemandStatus(de.getDemandID());
		}
	}
	//获取需求单状态为需采购的需求单号
	public void getNeedPurchaseDemandID() throws Exception{
		int needPurchaseDemandID=0;
		String sql="SELECT DemandID"
				+" FROM demand"
				+" WHERE Statement='需采购'";
		ResultSet rs=db.select(sql);
		while(rs.next()){
			needPurchaseDemandID=rs.getInt(1);
			this.demand.add(new Demand(needPurchaseDemandID));		
		}
	}
	//订单生成后更改需求单状态
	public void updateDemandStatus(int demandid) throws Exception{
		String sql="UPDATE demand"
				+" SET Statement='采购中'"
				+" WHERE DemandID="+demandid;
		db.update(sql);
	}
		
	public void setUserID(String userID) {
		this.userID = userID;
	}			
}
