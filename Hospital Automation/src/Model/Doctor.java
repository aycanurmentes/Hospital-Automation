package Model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Doctor extends User {
	Connection con =conn.connDb();
	Statement st=null;
	ResultSet rs=null;
	PreparedStatement preparedStatement = null;
	
	public Doctor(int id,String name, String tc, String password, String type) {
		super(id,name, tc, password, type);
		// TODO Auto-generated constructor stub
	}
	public Doctor() {
		super();
	}
	public ArrayList<Whour> getWhourList(int doctor_id) throws Exception {
		ArrayList<Whour> list = new ArrayList<>();

		Whour obj;
		try {
			st = (Statement) con.createStatement();
			rs = ((java.sql.Statement) st).executeQuery("SELECT * FROM whour WHERE status ='a' AND doctor_id=" + doctor_id);

			while (rs.next()) {
				obj = new Whour();
				obj.setId(rs.getInt("id"));
				obj.setDoctor_id(rs.getInt("doctor_id"));
				obj.setDoctor_name(rs.getString("doctor_name"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public boolean addWhour(int doctor_id,String doctor_name,String wdate) throws Exception {
		int key=0;
		int count=0;
		String query="INSERT INTO whour"+"(doctor_id,doctor_name,wdate) VALUES"+"(?,?,?)";//data tables
		
		try {
			st = (Statement) con.createStatement();
			rs = ((java.sql.Statement) st).executeQuery(
					"SELECT * FROM whour WHERE status='a' AND doctor_id=" + doctor_id + " AND wdate='" + wdate + "'");
			while (rs.next()) {
				count++;
				break;
			}
			if (count == 0) {

				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, doctor_id);
				preparedStatement.setString(2, doctor_name);
				preparedStatement.setString(3, wdate);
				preparedStatement.executeUpdate();
			}
			key = 1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}
	public boolean deleteWhour(int id) throws Exception {
		String query = "DELETE FROM whour WHERE id=?";
		boolean key = false;
		try {
			st = (Statement) con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
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
}

	

