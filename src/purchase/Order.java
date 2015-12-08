package purchase;

//记录订单信息
public class Order {
	//基本属性
	private String orderID ;   //订单号
 	private String itemName ; //商品的名称
 	private int number ;  //商品的数量
 	private double unitPrice ;   //商品单价
 	private double Account ;   //商品的总价
 	private String orderTime ; //商品生成的时间
 	private String status ; //商品的状态
 	
 	//构造方法
	public Order(String orderID, String itemName, int number, double unitPrice,
			double account, String orderTime, String status) {
		super();
		this.orderID = orderID;
		this.itemName = itemName;
		this.number = number;
		this.unitPrice = unitPrice;
		Account = account;
		this.orderTime = orderTime;
		this.status = status;
	}

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

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getAccount() {
		return Account;
	}

	public void setAccount(double account) {
		Account = account;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Account);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + number;
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		result = prime * result
				+ ((orderTime == null) ? 0 : orderTime.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		temp = Double.doubleToLongBits(unitPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (Double.doubleToLongBits(Account) != Double
				.doubleToLongBits(other.Account))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (number != other.number)
			return false;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (orderTime == null) {
			if (other.orderTime != null)
				return false;
		} else if (!orderTime.equals(other.orderTime))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Double.doubleToLongBits(unitPrice) != Double
				.doubleToLongBits(other.unitPrice))
			return false;
		return true;
	}
	
	
 	
	
 	
}
