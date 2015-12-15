package purchase;

public class Approve {
	//基本的信息
	private int requestID ;  //申请ID
	private String itemName ;  //物品的名称
	private int count ; //物品的数量
	private double price ;   //物品的价格
	private String status ;  //订单的状态
	
	//构造方法
	public Approve(int requestID, String itemName, int count, double price,
			String status) {
		super();
		this.requestID = requestID;
		this.itemName = itemName;
		this.count = count;
		this.price = price;
		this.status = status;
	}
	public Approve() {
		super();
		// TODO Auto-generated constructor stub
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	//hashCode 和 equal方法
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result
				+ ((itemName == null) ? 0 : itemName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + requestID;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Approve other = (Approve) obj;
		if (count != other.count)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (requestID != other.requestID)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	

	
	
}
