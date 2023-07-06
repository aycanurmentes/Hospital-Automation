package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Icon;

import Helper.Helper;

public class Patient extends User{
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Patient () {
		super();
	}

	public Patient(int id, String tc, String name, String password, String type) {
		super(id, tc, name, password, type);
	}

	public boolean register(String tc, String password, String name) {
		int key = 0;
		boolean duplicate = false;
		String query = "INSERT INTO user" + "(tc,pasword,name,type) VALUES" + " (?,?,?,?)";
		try {
			st =con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tc='" + tc + "'");
			while (rs.next()) {
				duplicate = true;
				Helper.showMsg("there is a record of this id number");
				break;
			}
			if (!duplicate) {

				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tc);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, "patient");

				preparedStatement.executeUpdate();
				key = 1;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addAppointment(int doctor_id, int patient_id, String doctor_name, String patient_name, String appDate) {
		int key = 0;
		String query = "INSERT INTO appointment" + "(doctor_id,doctor_name,patient_id,patient_name,app_date) VALUES"
				+ " (?,?,?,?,?)";
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doctor_id);
			preparedStatement.setString(2, doctor_name);
			preparedStatement.setInt(3, patient_id);
			preparedStatement.setString(4, patient_name);
			preparedStatement.setString(5, appDate);

			preparedStatement.executeUpdate();
			key = 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateWhourStatus(int doctor_id, String wdate) {
		int key = 0;
		String query = "UPDATE whour SET status = ? WHERE doctor_id= ? AND wdate= ?";
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doctor_id);
			preparedStatement.setString(3, wdate);
			preparedStatement.executeUpdate();
			key = 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	
}
