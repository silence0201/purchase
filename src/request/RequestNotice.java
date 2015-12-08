package request;

//记录通知变量
public class RequestNotice {
	//记录基本的信息
	private String requestID;   //订单id
	private String itemName;    // 订单物品
	private int number;    //订单名称
	private String requestStatement;   //订单状态
	private String requestTime ;  //订单时间
	
	//构造函数
	public RequestNotice() {
		super();
	}
	public RequestNotice(String requestID, String itemName, int number,
			String requestStatement, String requestTime) {
		super();
		this.requestID = requestID;
		this.itemName = itemName;
		this.number = number;
		this.requestStatement = requestStatement;
		this.requestTime = requestTime;
	}
	
	
	//getter 和 settet 方法
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getRequestStatement() {
		return requestStatement;
	}
	public void setRequestStatement(String requestStatement) {
		this.requestStatement = requestStatement;
	}
	
}
