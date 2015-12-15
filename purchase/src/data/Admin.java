package data;

import java.sql.ResultSet;

public class Admin {
	private String login_user;
	private String userID;
	private String userName ;
	private String password;
	private String position;
	private DBUtil db;
	public Admin(){
		db = new DBUtil();
		login_user="";
		userName="";
		userID="";
		password="";
		position="";
	}
	
	public void setLogin_user(String login_user) {
		this.login_user = login_user;
	}

	

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void login() throws Exception{
		String sql = "SELECT UserID,Username,Password,Position FROM user WHERE UserID='"+login_user+"'";
		ResultSet rs = db.select(sql);
		while(rs.next()){
			String myUserID=rs.getString("UserID");
			String myName=rs.getString("Username") ;
			String myWord=rs.getString("Password");
			String myPosition=rs.getString("Position");
			this.setUserID(myUserID);
			this.setUserName(myName);
			this.setPassword(myWord);
			this.setPosition(myPosition);
		}
	}
}
