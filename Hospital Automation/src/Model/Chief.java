package Model;

import java.beans.Statement;
import java.io.ObjectInputFilter.Config;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Chief extends User {

	Connection con =conn.connDb();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement = null;
	
	public Chief(int id,String name, String tc, String password, String type) {
		super(id,name, tc, password, type);
		// TODO Auto-generated constructor stub
	}

	public Chief() {
		
	}
	public ArrayList<User> getDoctorlist(){
		ArrayList<User> list =new ArrayList<>();
		User obj;
		try {
			st = (Statement) con.createStatement();
			rs = ((java.sql.Statement) st).executeQuery("SELECT * FROM user WHERE type='doktor' ");
while(rs.next()) {
			obj= new User(rs.getInt("id"),rs.getString("name"),rs.getString("tc"),rs.getString("password"),rs.getString("type"));
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean addDoctor(String tc, String password, String name) throws Exception {
		String query = "INSERT INTO user " + "(tc,pasword,name,type) VALUES" + "(?,?,?,?)";
		boolean key = false;
		try {
			st = (Statement) con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tc);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, "doctor");
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}
	public boolean deleteDoctor(int id) throws Exception {
		String query = "DELETE FROM user WHERE id= ?";
		boolean key = false;
		try {
			st = (Statement) con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}
	
	public boolean updateDoctor(int id,String tc,String password,String name) throws Exception {
		String query = "UPDATE user SET name= ?,tc=?,password=? WHERE id=?";
		boolean key = false;
		try {
			st = (Statement) con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,name);
			preparedStatement.setString(2,tc);
			preparedStatement.setString(3,password);
			preparedStatement.setInt(4,id);
			preparedStatement.executeUpdate();
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}
	public boolean addWorker(int user_id, int clinic_id) throws Exception {
		String query = "INSERT INTO worker " + "(user_id,clinic_id) VALUES" + "(?,?)";
		boolean key = false;
		int count = 0;

		try {
			st = (Statement) con.createStatement();
			rs = ((java.sql.Statement) st).executeQuery("SELECT * FROM worker WHERE clinic_id= " + clinic_id + " AND user_id= " + user_id);
			while (rs.next()) {
				count++;
			}
			if (count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, user_id);
				preparedStatement.setInt(2, clinic_id);
				preparedStatement.executeUpdate();
			}
			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (key)
			return true;
		else
			return false;
	}
	public ArrayList<User> getClinicDoctorlist(int clinic_id){
		ArrayList<User> list =new ArrayList<>();
		User obj;
		try {
			st = (Statement) con.createStatement();
			rs = ((java.sql.Statement) st).executeQuery("SELECT u.id,u.tc,u.type,u.name,u.password FROM worker w LEFT JOIN user u ON w.user_id=u.id WHERE clinic_id=" +clinic_id);
while(rs.next()) {
			obj= new User(rs.getInt("u.id"),rs.getString("u.name"),rs.getString("u.tc"),rs.getString("u.pasword"),rs.getString("u.type"));
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	}
	


