package divMangere;

//详细的审核信息
public class Request {
	//主要的审核信息
	private int requestID;   //申请单
	private String itemName;    //申请的物品名称
	private int number;    //申请的数量
	private double account;   //申请的金额
	private String requestTime;  //申请的
	private String requestStatement;  //申请单的状态
	private String requestName ;  //申请人的姓名
	private String reason ; // 申请的理由
	
	//构造函数
	public Request(int requestID, String itemName, int number,
			double account, String requestTime, String requestStatement,
			String requestName) {
		super();
		this.requestID = requestID;
		this.itemName = itemName;
		this.number = number;
		this.account = account;
		this.requestTime = requestTime;
		this.requestStatement = requestStatement;
		this.requestName = requestName;
	}
	public Request(int requestID, String itemName, int number, double account,
			String requestTime, String requestStatement, String requestName,
			String reason) {
		super();
		this.requestID = requestID;
		this.itemName = itemName;
		this.number = number;
		this.account = account;
		this.requestTime = requestTime;
		this.requestStatement = requestStatement;
		this.requestName = requestName;
		this.reason = reason;
	}
	public Request() {
		super();
	}

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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

	public String getRequestStatement() {
		return requestStatement;
	}

	public void setRequestStatement(String requestStatement) {
		this.requestStatement = requestStatement;
	}

	public String getRequestName() {
		return requestName;
	}

	public void setRequestName(String requestName) {
		this.requestName = requestName;
	}
}
