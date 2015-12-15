package divMangere;


//这里主要保存最近审核需求单的信息
public class ReceExamine {
	//基本信息
	int requestID ;   //记录申请表的ID
	String itemName ;   //申请物品的名称
	int number ;   //申请物品的数量
	double account ;  //申请物品的金额
	String requestName ; //申请人
	String requestTime ;   //申请时间
	
	//构造函数
	public ReceExamine(int requestID, String itemName, int number,
			double account, String requestName, String requestTime) {
		super();
		this.requestID = requestID;
		this.itemName = itemName;
		this.number = number;
		this.account = account;
		this.requestName = requestName;
		this.requestTime = requestTime;
	}
	public ReceExamine() {
		super();
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	public String getRequestName() {
		return requestName;
	}
	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	
	
	
	
}
