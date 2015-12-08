package mangere;

//总经理对订单的查看
public class Order {
	//订单的基本信息
	private String orderID;   //订单的ID
	private String itemName;  //  订单物品的名称
	private int number;   //订单物品的数量
	private double account;  // 订单的总额
	private String orderTime;  // 订单的生成时间
	private String orderStatement;  // 订单的状态
	
	//含参数的构造函数
	public Order(String orderID, String itemName, int number, double account,
			String orderTime, String orderStatement) {
		super();
		this.orderID = orderID;
		this.itemName = itemName;
		this.number = number;
		this.account = account;
		this.orderTime = orderTime;
		this.orderStatement = orderStatement;
	}
	
	//无参构造函数
	public Order() {
		super();
	}

	//getter和setter方法
	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
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

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderStatement() {
		return orderStatement;
	}

	public void setOrderStatement(String orderStatement) {
		this.orderStatement = orderStatement;
	}
	
}
