package mangere;

//这是总经理用来查看所有需求单信息的类
public class Request {
	//基本的需求申请单的信息
	private int requestID;    //申请单的ID号
	private String itemName;   //所申请的物品名称
	private int number;    // 所申请物品的数量
	private double account;   // 所申请物品的总金额
	private String requestTime;   //提出申请的时间
	private String requestManName;  // 申请人的姓名
	private String auditorManName;   //审核人的姓名
	private String requestStatement;  //申请单的状态
	
	//构造函数
	public Request(int requestID, String itemName, int number,
			double account, String requestTime, String requestManName,
			String auditorManName, String requestStatement) {
		super();
		this.requestID = requestID;
		this.itemName = itemName;
		this.number = number;
		this.account = account;
		this.requestTime = requestTime;
		this.requestManName = requestManName;
		this.auditorManName = auditorManName;
		this.requestStatement = requestStatement;
	}
	public Request() {
		super();
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
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public String getRequestManName() {
		return requestManName;
	}
	public void setRequestManName(String requestManName) {
		this.requestManName = requestManName;
	}
	public String getAuditorManName() {
		return auditorManName;
	}
	public void setAuditorManName(String auditorManName) {
		this.auditorManName = auditorManName;
	}
	public String getRequestStatement() {
		return requestStatement;
	}
	public void setRequestStatement(String requestStatement) {
		this.requestStatement = requestStatement;
	}
	
	
	
	
	
}
