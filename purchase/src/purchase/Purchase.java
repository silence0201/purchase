package purchase;
// 本类用来管理所有purchase的查询的数据

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import data.DBUtil;

public class Purchase {
	private DBUtil db ;   //数据库操作对象
	private String userID ; //用户身份
	private ArrayList<Approve> approves ;   //保存最近申请信息的记录的集合
	private ArrayList<Order> orders ;   //保存最近的订单信息
	private ArrayList<Supplier> suppliers ;  //保存供应商的信息
	private HashMap<String, Integer> supplierCount ;   //记录供应商的数目
	private ArrayList<DemandNotice> demandNotices  ;  //计入需求单的通知
	private ArrayList<RequestNotice> requestNotices ; 
	
	//无参构造，初始化变量
	public Purchase() {
		db = new DBUtil() ;
		approves = new  ArrayList<Approve>() ;
		orders = new ArrayList<Order>() ;
		suppliers = new ArrayList<Supplier>() ;
		supplierCount = new HashMap<String, Integer>() ;
		demandNotices = new ArrayList<DemandNotice>() ;
		requestNotices = new ArrayList<RequestNotice>() ;
	}

	//用户身份进行填充以及获取
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
	//得到approve的结果
	public void setApprove() throws Exception{
		String sql = "select RequestID,Itemname,Number,Totalaccount,Requeststatement "
				+ "from request,item "
				+ "where request.ItemID = item.ItemID AND request.AuditorID = '"+ userID +"'" ; 
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			int requestID = rs.getInt(1) ;  //申请ID
			String itemName = rs.getString(2) ;  //物品的名称
			int count = rs.getInt(3); //物品的数量
			double price = rs.getDouble(4);   //物品的价格
			String status = rs.getString(5);  //订单的状态
			approves.add(new Approve(requestID,itemName,count,price,status)) ;   //将记录添加到集合
		}
	}
	
	public ArrayList<Approve> getApprove(){
		return approves ;
	}

	//得到订单的基本信息
	public void setOrder() throws Exception{
		String sql = "SELECT `OrderID`,`Itemname`,`Number`,`Unitprice`,`Account`,`Ordertime`,`order`.`Statement`"
				+ "from `order`,`demand`,`item` "
				+ "where `order`.`DemandID` = `demand`.`DemandID` AND `demand`.`ItemID` = `item`.`ItemID` "
				+ "AND `orderManID`='"+userID+"'"
				+ "order by `Ordertime`" ;
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			int orderID = rs.getInt(1) ;
			String itemName = rs.getString(2) ;
			int number = rs.getInt(3) ;
			double utilPrice = rs.getDouble(4) ;
			double account = rs.getDouble(5) ;
			String orderTime = rs.getString(6) ;
			String statue = rs.getString(7) ;
			Order order = new Order(orderID, itemName, number, utilPrice, account, orderTime, statue) ;
			orders.add(order) ;
		}
	}
	
	public ArrayList<Order> getOrder(){
		return orders ;
	}

	
	//供应商信息的处理
	public void setSupplier() throws Exception{
		String sql = "select * from supplier" ;
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			String supplierID = rs.getString(1) ;
			String supplierName = rs.getString(2) ;
			String contact = rs.getString(3) ;
			String tel = rs.getString(4) ;
			String add = rs.getString(5) ;
			suppliers.add(new Supplier(supplierID, supplierName, contact, tel, add)) ;
		}
	}
	
	public ArrayList<Supplier> getSupplier(){
		return suppliers ;
	}
	//通过id获取供应商
	public Supplier getBySupplierID(String id) throws Exception{
		String sql = "SELECT * FROM `supplier` WHERE `supplier`.`SupplierID` = '"+id+"' " ;
		ResultSet rs = db.select(sql) ;
		Supplier supplier = null ;
		
		while(rs.next()){
			String supplierID = rs.getString(1) ;
			String supplierName = rs.getString(2) ;
			String contact = rs.getString(3) ;
			String tel = rs.getString(4) ;
			String add = rs.getString(5) ;
			String moreAdd = rs.getString(6) ;
			supplier = new Supplier(supplierID, supplierName, contact, tel, add,moreAdd) ;
		}
		return supplier ;
	}
	//获取指定供应商的供应的商品
	public ArrayList<Item> getItemBySupplierID(String id) throws Exception{
		String sql = "SELECT `relationship`.`ItemID`,`item`.`Itemname`,`item`.`Unitprice`,`relationship`.`Quality`,`relationship`.`Statement`  "
				+ "FROM `relationship`,`item` "
				+ "WHERE `relationship`.`ItemID` = `item`.`ItemID` AND `relationship`.`SupplierID` = '"+id+"'" ;
		ArrayList<Item> items = new ArrayList<Item>() ;
		ResultSet rs = db.select(sql) ;
		while(rs.next()){
			String itemID = rs.getString(1) ;
			String itemName = rs.getString(2) ;
			double price = rs.getDouble(3) ;
			String quantiy = rs.getString(4) ;
			String status = rs.getString(5) ;
			items.add(new Item(itemID, itemName, price, quantiy, status)) ;
		}
		return items ;
	}
	
	//获取供应商的总数
	public void setCount() throws Exception{
		String sql = "SELECT `supplier`.`Address`,count(*) FROM `supplier` "
				+ "GROUP BY `supplier`.`Address`" ;
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			String add = rs.getString(1) ;
			int num = rs.getInt(2) ;
			supplierCount.put(add, num) ;
		}
	}
	
	public HashMap<String, Integer> getCount(){
		return supplierCount ;
	}
	
	//获取通知信息
	public void setDemandNotices() throws Exception{
		String sql = "SELECT `demand`.`DemandID`,`demand`.`ItemID`,`item`.`Itemname`,`demand`.`Number`,`item`.`Unitprice`,`demand`.`Account`,`DemandTime`"
				+ "FROM `demand`,`item`"
				+ "WHERE `demand`.`ItemID` = `item`.`ItemID` AND `demand`.`Statement` = '需采购'"
				+ "ORDER BY `demand`.`DemandTime` " ;
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			String demandID = rs.getString(1) ;
			String itemID = rs.getString(2) ;
			String itemName = rs.getString(3) ;
			int number = rs.getInt(4) ;
			double price = rs.getDouble(5) ;
			double account = rs.getDouble(6) ;
			String demandTime = rs.getString(7) ;
			demandNotices.add(new DemandNotice(demandID, itemID, itemName, number, price, account, demandTime));
		}
	}
	
	public ArrayList<DemandNotice> getDemandNotices(){
		return demandNotices ;
	}
	
	public void setRequestNotices() throws Exception{
		String sql = "SELECT `request`.`RequestID`,`item`.`Itemname`,`request`.`Number`,`request`.`Totalaccount`,"
				+ "`request`.`Requeststatement`,`request`.`RequestmanID`,`request`.`Requesttime`  "
				+ "FROM `request`,`item` "
				+ "WHERE `request`.`Requeststatement` = '未审核' AND `request`.`ItemID` = `item`.`ItemID` AND `request`.`Totalaccount` < 1000 "
				+ "ORDER BY `request`.`Requesttime`" ;
		
		ResultSet rs = db.select(sql) ;
		
		while(rs.next()){
			int requestID = rs.getInt(1) ;
			String itemName = rs.getString(2) ;
			int number = rs.getInt(3) ;
			double account = rs.getDouble(4) ;
			String status = rs.getString(5) ;
			String requestMan = rs.getString(6) ;
			String requestTime = rs.getString(7) ;
			
			requestNotices.add(new RequestNotice(requestID, itemName, number, account, status, requestMan, requestTime)) ;
		}
	}
	
	public ArrayList<RequestNotice> getRequestNotices(){
		return requestNotices ;
	}
	
	//获取商品id
		public String getItemID(String itemname,double account) throws Exception{
			String itemid="";
			String sql="SELECT item.ItemID"
					+" FROM item"
					+" WHERE item.Itemname='"+itemname+"'";
			ResultSet rs = db.select(sql) ;
			while(rs.next()){
				itemid=rs.getString(1);
			}
			//不存在改商品则初始化一个
			if(itemid==""&&account!=-1){
				itemid=this.insertItem(itemname,account);
				
			}
			return itemid;
		}

		//添加商品
		public String insertItem(String itemname,double account) throws Exception{
			String itemid="";
			String sql="SELECT MAX(ItemID)"
					+" FROM item";
			ResultSet rs = db.select(sql) ;
			while(rs.next()){
				itemid=rs.getString(1);
			}
			int newitemid=Integer.parseInt(itemid.substring(1));
			newitemid++;
			itemid=newitemid+"";
			if(itemid.length()==1){
				itemid="B00"+itemid;
			}
			else if(itemid.length()==2){
				itemid="B0"+itemid;
			}
			else {
				itemid="B"+itemid;
			}
			String Sql="INSERT INTO item(ItemID,ItemName,Unitprice,ItemsInventory)"
					+" VALUES('"+itemid+"','"+itemname+"',"+account+",0)";
			db.insert(Sql);
			return itemid;
		}
		
		//添加新供应商
		public void insertSupplier(String suppliername,String contacts,String telnumber,String address,String moreAdd) throws Exception{
			String sql="INSERT INTO supplier(Suppliername,Contacts,Telnumber,Address,moreAdd)"
					+" VALUES('"+suppliername+"','"+contacts+"','"+telnumber+"','"+address+"','"+moreAdd+"')";
			db.insert(sql);
		}
		//现有供应商添加提供商品
		public void insertSupplierItem(int supplierid,String itemname,String quality,String statement,double account) throws Exception{
			String itemid=this.getItemID(itemname,account);
			String sql="INSERT INTO relationship(SupplierID,ItemID,Quality,Statement)"
					+" VALUES("+supplierid+",'"+itemid+"','"+quality+"','"+statement+"')";
			db.insert(sql);
		}
		
		//修改供应商信息
		public void updateSupplier(int supplierid,String suppliername,String contacts,String telnumber,String address,String moreAdd) throws Exception{
			String sql="UPDATE supplier set Suppliername='"+suppliername+"',Contacts='"+contacts+"',Telnumber='"+telnumber+"',Address='"+address+"',moreAdd='"+moreAdd+"'"
					+" WHERE SupplierID="+supplierid;
			db.update(sql);
		}
		//修改供应商商品信息
		public void updateSupplierItem(int supplierid,String itemname,String quality,String statement,double account) throws Exception{
			String itemid=this.getItemID(itemname,account);
			String sql="UPDATE relationship SET Quality='"+quality+"',Statement='"+statement+"'"
					+" WHERE SupplierID="+supplierid+" AND ItemID='"+itemid+"'";
			db.update(sql);
			sql = "UPDATE `item` SET `item`.`Unitprice` = "+account
					+ " WHERE `item`.`ItemID` = '"+itemid+"' " ;
			db.update(sql);
		}
		//删除供应商以及供应商所有商品信息
		public void deleteSupplier(int supplierid) throws Exception{
			String sql="DELETE FROM supplier"
					+" WHERE SupplierID="+supplierid;
			db.delete(sql);
			sql="DELETE FROM relationship"
					+" WHERE SupplierID="+supplierid;
			db.delete(sql);
		}
		//删除供应商某一商品信息
		public void deleteSupplierItem(int supplierid,String itemname) throws Exception{
			String itemid=this.getItemID(itemname,-1);
			String sql="DELETE FROM relationship"
					+" WHERE SupplierID='"+supplierid+"' AND ItemID='"+itemid+"'";
			db.delete(sql);
			//sql="DELETE FROM item"
			//+" WHERE ItemID='"+itemid+"' AND Unitprice=-1";
			//db.delete(sql);
		}

}
