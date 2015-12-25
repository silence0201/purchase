package purchase;

public class RequestItem {
	private int requestID;//申请单id
	private String itemID;//商品id
	private int itemNum;//申请单上的商品数量
	private double itemAccount;//申请单上的商品金额
	public RequestItem(String itemID) {
		super();
		this.itemID = itemID;
	}
	public RequestItem(int itemNum, double itemAccount) {
		super();
		this.itemNum = itemNum;
		this.itemAccount = itemAccount;
	}
	public RequestItem(String itemID, int itemNum, double itemAccount) {
		super();
		this.itemID = itemID;
		this.itemNum = itemNum;
		this.itemAccount = itemAccount;
	}
	
	public RequestItem(int requestID, String itemID, int itemNum,
			double itemAccount) {
		super();
		this.requestID = requestID;
		this.itemID = itemID;
		this.itemNum = itemNum;
		this.itemAccount = itemAccount;
	}
	//setter and getter
	
	public String getItemID() {
		return itemID;
	}
	public int getRequestID() {
		return requestID;
	}
	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public double getItemAccount() {
		return itemAccount;
	}
	public void setItemAccount(double itemAccount) {
		this.itemAccount = itemAccount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
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
		RequestItem other = (RequestItem) obj;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		return true;
	}

}
