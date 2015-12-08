package stockman;
//库存信息标志对象
public class Stock {
	//基本数据信息
	private String itemID ;  //商品的id
	private String itemName ;   //商品的名称
	private String price ;    //商品的单间
	private String reserve ;  //商品的库存
	
	//构造方法
	public Stock() {
		super();
	}
	public Stock(String itemID, String itemName, String price, String reserve) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.price = price;
		this.reserve = reserve;
	}
	
	//getter 与 setter 方法
	public String getItemID() {
		return itemID;
	}
	public void setItemID(String itemID) {
		this.itemID = itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemID == null) ? 0 : itemID.hashCode());
		result = prime * result
				+ ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((reserve == null) ? 0 : reserve.hashCode());
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
		Stock other = (Stock) obj;
		if (itemID == null) {
			if (other.itemID != null)
				return false;
		} else if (!itemID.equals(other.itemID))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (reserve == null) {
			if (other.reserve != null)
				return false;
		} else if (!reserve.equals(other.reserve))
			return false;
		return true;
	}
	
	
}
