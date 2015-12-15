package divMangere;

//记录部门经理审核订单的信息
public class RequestNotice {
	//记录申请单基本的信息
	private int requestID ;   //记录申请的id号
	private String itemName;    // 申请物品
	private int number;    //申请的数目
	private double account ;  //申请物品的价格
	private String requestStatement;   //申请单状态
	private String requestManID ; //申请人的ID
	private String requestTime ;  //申请的时间
	
	//构造函数
	public RequestNotice(int requestID, String itemName, int number,
			double account, String requestStatement, String requestManID,
			String requestTime) {
		this.requestID = requestID;
		this.itemName = itemName;
		this.number = number;
		this.account = account;
		this.requestStatement = requestStatement;
		this.requestManID = requestManID;
		this.requestTime = requestTime;
	}
	public RequestNotice() {
	}
	
	//getter 和 setter 方法
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
	public String getRequestStatement() {
		return requestStatement;
	}
	public void setRequestStatement(String requestStatement) {
		this.requestStatement = requestStatement;
	}
	public String getRequestManID() {
		return requestManID;
	}
	public void setRequestManID(String requestManID) {
		this.requestManID = requestManID;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	
	
	
	
}
