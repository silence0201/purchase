package purchase;

//这里主要采购员需要进行采购的需求单的信息
public class DemandNotice {
	private String demandID ;    //需求单的ID信息
	private String itemID ;  //需求物品的id号
	private String itemName ;  //需求物品的名称
	private int number ;  //需求物品的数量
	private double price ;   //需求物品的单价
	private double account  ;  //需求物品的总价格
	private String demandTime ;  //需求单生成的时间
	
	//基本的构造函数
	public DemandNotice(String demandID, String itemID, String itemName,
			int number, double price, double account, String demandTime) {
		super();
		this.demandID = demandID;
		this.itemID = itemID;
		this.itemName = itemName;
		this.number = number;
		this.price = price;
		this.account = account;
		this.demandTime = demandTime;
	}
	public DemandNotice() {
		super();
		// TODO Auto-generated constructor stub
	}

	//基本的getter 和 setter 方法
	public String getDemandID() {
		return demandID;
	}
	public void setDemandID(String demandID) {
		this.demandID = demandID;
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	public String getDemandTime() {
		return demandTime;
	}
	public void setDemandTime(String demandTime) {
		this.demandTime = demandTime;
	}
	
	
	
}
