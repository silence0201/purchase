package stockman;
//最近审批记录条
public class Examine {
	//基本的数据量
	private String ID ;   //入库单货出库单ID
	private String itemName ;  //货品名称
	private int number ;   //  数量  正代表入库 负表示出库
	private String time  ;  //出库或入库时间
	
	//构造函数
	public Examine(String iD, String itemName, int number, String time) {
		super();
		ID = iD;
		this.itemName = itemName;
		this.number = number;
		this.time = time;
	}

	public Examine() {
		super();
	}

	//getter 和 setter 方法
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	
	
	
}
