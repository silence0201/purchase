package mangere;

//这个类主要记录需求单的ID
public class Demand {
	//需求单的基本属性
	private String demandID;   //需求单ID
	private String itemName;   //物品名称
	private int number;       //物品数量
	private double account;    //物品总金额
	private String statement;  // 申请单状态
	private String demandTime;   //申请单生成时间
	
	//构造函数
	public Demand(String demandID, String itemName, int number, double account,
			String statement, String demandTime) {
		super();
		this.demandID = demandID;
		this.itemName = itemName;
		this.number = number;
		this.account = account;
		this.statement = statement;
		this.demandTime = demandTime;
	}

	public Demand() {
	}

	public String getDemandID() {
		return demandID;
	}

	public void setDemandID(String demandID) {
		this.demandID = demandID;
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

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getDemandTime() {
		return demandTime;
	}

	public void setDemandTime(String demandTime) {
		this.demandTime = demandTime;
	}
	
	
	
	
	
}
