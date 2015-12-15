package stockman;

public class Export {
	//基本的数据信息
	private int exportID ; //出库单号
	private int requestID ;  //申请单号
	private String itemName ;  //商品的名称
	private int number ;  //商品的数量
	private String requestUserName ;  //申请人的名称
	private String exportTime ; //出库时间
	
	//构造函数
	public Export(int exportID, int requestID, String itemName,
			int number, String requestUserName, String exportTime) {
		super();
		this.exportID = exportID;
		this.requestID = requestID;
		this.itemName = itemName;
		this.number = number;
		this.requestUserName = requestUserName;
		this.exportTime = exportTime;
	}
	public Export() {
		super();
	}
	
	//getter和setter方法
	public int getExportID() {
		return exportID;
	}
	public void setExportID(int exportID) {
		this.exportID = exportID;
	}
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
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getRequestUserName() {
		return requestUserName;
	}
	public void setRequestUserName(String requestUserName) {
		this.requestUserName = requestUserName;
	}
	public String getExportTime() {
		return exportTime;
	}
	public void setExportTime(String exportTime) {
		this.exportTime = exportTime;
	}

	
	
	
	
}
