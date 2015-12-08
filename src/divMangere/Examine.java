package divMangere;

//这是对本部门申请订单申请情况统计变量
public class Examine {
	//基本的数据信息
	private int requestID ;  //订单ID
	private String itemName ;  //商品名称
	private int  number ; //商品数量
	private double account ; //商品的金额
	private String requestName ;// 申请人的姓名
	private String requestTime ; //申请的时间
	private String requestStatus ;  //申请单的状态
	private String auditorName ;  //审核人的姓名
	private String auditTime ;  //审核的时间
	
	//构造方法
	public Examine(int requestID, String itemName, int number,
			double account, String requestName, String requestTime,
			String requestStatus, String auditorName, String auditTime) {
		super();
		this.requestID = requestID;
		this.itemName = itemName;
		this.number = number;
		this.account = account;
		this.requestName = requestName;
		this.requestTime = requestTime;
		this.requestStatus = requestStatus;
		this.auditorName = auditorName;
		this.auditTime = auditTime;
	}

	public Examine() {
		super();
	}

	//gettet 和 setter 方法
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

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	
	
	
	
	
	
}
