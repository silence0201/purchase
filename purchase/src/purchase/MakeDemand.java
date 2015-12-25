package purchase;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import data.DBUtil;

public class MakeDemand {
	private DBUtil db;
	//
	private String myItemID;//商品id
	private int number;//同一个需求单上的商品总数量
	private double account;//同一个需求单上的商品总金额
	
	private String demandTime;//需求单生成时间
	private String startTime;//生成需求单时查询申请单内的审核时间的开始时间段
	private String endTime;//生成需求单时查询申请单内的审核时间的结束时间段
	private ArrayList<RequestItem> requestItem;
	
	public MakeDemand(){
		db = new DBUtil() ;

		this.demandTime="";
		this.startTime="";
		this.endTime="";
		this.requestItem=new ArrayList<RequestItem>();
	}
	
	//获取当前时间
	public String getCurrentTime(){
		Date dnow = new Date() ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String currentTime=sdf.format(dnow) ;	
		this.demandTime=currentTime;
		return currentTime;
	}
	
	//设置开始时间与结束时间的值
	public void setStartTimeEndTime(){
		String currentTime=this.getCurrentTime();
		String myYear=currentTime.substring(0, 4);
		String myMonth=currentTime.substring(5, 7);
		String myDay=currentTime.substring(8);
		int year=Integer.parseInt(myYear);
		int month=Integer.parseInt(myMonth);
		int day=Integer.parseInt(myDay);
		
		if(day<=10){
			this.startTime=year+"-"+month+"-01";
			this.endTime=year+"-"+month+"-10";
		}
		else if(day>10&&day<=20){
			this.startTime=year+"-"+month+"-11";
			this.endTime=year+"-"+month+"-20";
		}else if(day>20){ 
		
		boolean isLeap=false;//判断闰年标识		
		if((year%400)==0||(year%100!=0&&year%4==0))isLeap=true;
		
			switch(month){
				case 1:
				case 3:
				case 5:
				case 7:
				case 8:
				case 10:
				case 12:
					this.startTime=year+"-"+month+"-21";
					this.endTime=year+"-"+month+"-31";
					break;
				case 4:
				case 6:
				case 9:
				case 11:
					this.startTime=year+"-"+month+"-21";
					this.endTime=year+"-"+month+"-30";
					break;
				case 2:
					this.startTime=year+"-"+month+"-21";
					if(isLeap){
						this.endTime=year+"-"+month+"-29";
					}
					else{
						this.endTime=year+"-"+month+"-28";
					}break;
			}
		}
	}
	//获取申请单中同一时间段相同的商品id，以及商品对应的数量 金额
	public void getItemIDNumAccount() throws Exception{
		this.setStartTimeEndTime();
		
		boolean onlyOneTime=true;
		String sql="SELECT request.RequestID,request.ItemID,request.Number,request.Totalaccount"
				+" FROM request"
				+" WHERE Requeststatement='通过' AND DemandlistID is NULL"
				+" AND Audittime BETWEEN '"+startTime+"' AND '"+endTime+"'"
				+" ORDER BY ItemID";
		ResultSet rs=db.select(sql);
		while(rs.next()){
			int requestID=rs.getInt(1);
			String itemID=rs.getString(2);
			int itemNum=rs.getInt(3);
			double itemAccount=rs.getDouble(4);
			System.out.println(requestID+" "+itemID+" "+itemNum+" "+itemAccount);
			this.requestItem.add(new RequestItem(requestID,itemID,itemNum,itemAccount));
			if(onlyOneTime){
				this.myItemID=itemID;
				//this.number=itemNum;
				//this.account=itemAccount;
				//System.out.println(requestID+" "+itemID+" "+itemNum+" "+itemAccount);
				onlyOneTime=false;
			}
		}		
	}
	
	//生成需求单 只需调用该函数
	public void setDemand() throws Exception{
		this.getItemIDNumAccount();
		boolean isNotSameItem=false;
		Iterator<RequestItem> itRequestItem=requestItem.iterator();
		
		while(itRequestItem.hasNext()){
			RequestItem rItem=itRequestItem.next();
			if(myItemID.equals(rItem.getItemID())){
				this.number+=rItem.getItemNum();
				this.account+=rItem.getItemAccount();
				//System.out.println(number+" "+account);
				if(!itRequestItem.hasNext()){
					isNotSameItem=true;
				}
			}
			else{
				isNotSameItem=false;
				//System.out.println(number+" "+account);
				
				String sql="INSERT INTO demand(ItemID,Number,Account,DemandTime)"
						+" VALUES('"+this.myItemID+"',"+this.number+","+this.account+",'"+this.demandTime+"')";//添加需求单
				db.insert(sql);
				//调用添加申请单中需求单号函数
				this.updateRequestDemandID(this.getDemandID(),rItem.getRequestID());
				//调用需求单状态改变函数
				this.updateDemandStatus(this.getDemandID(),this.myItemID,this.number);
				
				if(!itRequestItem.hasNext()){
					//this.myItemID=rItem.getItemID();
					this.number=rItem.getItemNum();
					this.account=rItem.getItemAccount();
					isNotSameItem=true;
				}
				
				this.myItemID=rItem.getItemID();
			}
			if(isNotSameItem){
				String sql="INSERT INTO demand(ItemID,Number,Account,DemandTime)"
						+" VALUES('"+this.myItemID+"',"+this.number+","+this.account+",'"+this.demandTime+"')";//添加需求单
				db.insert(sql);
				//调用添加申请单中需求单号函数
				this.updateRequestDemandID(this.getDemandID(),rItem.getRequestID());
				//调用需求单状态改变函数
				this.updateDemandStatus(this.getDemandID(),this.myItemID,this.number);
			}
		}
	}
	//获取 生成需求单后该需求单的单号
	public int getDemandID() throws Exception{
		int myDemandID=0;
		String sql="SELECT MAX(DemandID)"
				+" FROM demand"
				+" WHERE DemandTime='"+this.demandTime+"'";
		ResultSet rs=db.select(sql);
		while(rs.next()){
			myDemandID=rs.getInt(1);
		}
		return myDemandID;
	}
	//添加申请单中需求单号函数
	public void updateRequestDemandID(int demandid,int requestid) throws Exception{
		String sql="UPDATE request SET DemandlistID="+demandid
				+" WHERE RequestID="+requestid;
		db.update(sql);
	}
	//需求单状态改变函数
	public void updateDemandStatus(int demandid,String itemid,int itemnum) throws Exception{
		String demandStatus="有货";
		int itemsInventory=this.getItemInventory(itemid);
		if(itemnum>itemsInventory){
			demandStatus="需采购";
		}
		String sql="UPDATE demand SET Statement='"+demandStatus+"'"
				+" WHERE DemandID="+demandid;
		db.update(sql);
	}
	//获取商品库存
	public int getItemInventory(String itemid) throws Exception{
		int itemsInventory=0;
		String sql="SELECT item.ItemsInventory"
				+" FROM item"
				+" WHERE ItemID='"+itemid+"'";
		ResultSet rs=db.select(sql);
		while(rs.next()){
			itemsInventory=rs.getInt(1);
		}
		return itemsInventory;
	}
	
	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}	
}
