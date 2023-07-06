package Model;

import Helper.DBConnection;

public class User {
	private int id;
	String name,tc,password,type;
	DBConnection conn=new DBConnection();
	
	public User(int id,String name, String tc, String password, String type) {
		super();
		this.name=name;
		this.id = id;
		this.tc = tc;
		this.password = password;
		this.type = type;
	}
	public User() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTc() {
		return tc;
	}
	public void setTc(String tc) {
		this.tc = tc;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
