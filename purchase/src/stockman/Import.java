package stockman;


//记录入库信息
public class Import {	
	//基本的数据信息
	private int importID ;   //入库单ID
	private int orderID ;   // 订单ID
	private String itemName ; //货品名称
	private int number ;     //商品的数量
	private String importTime ; // 导入的时间
	
	
	//构造函数
	public Import() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Import(int importID, int orderID, String itemName, int number,
			String importTime) {
		super();
		this.importID = importID;
		this.orderID = orderID;
		this.itemName = itemName;
		this.number = number;
		this.importTime = importTime;
	}
	
	//getter 和 setter方法
	public int getImportID() {
		return importID;
	}
	public void setImportID(int importID) {
		this.importID = importID;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
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
	public String getImportTime() {
		return importTime;
	}
	public void setImportTime(String importTime) {
		this.importTime = importTime;
	}
	
}
