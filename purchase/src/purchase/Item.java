package purchase;

public class Item {
	//物品信息
	private String itemID ;
	private String itemName ;
	private double price ;
	private String quality ;
	private String status ;
	
	public Item(String itemID, String itemName, double price, String quality,
			String status) {
		super();
		this.itemID = itemID;
		this.itemName = itemName;
		this.price = price;
		this.quality = quality;
		this.status = status;
	}
	public Item() {
		super();
	}
	
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
