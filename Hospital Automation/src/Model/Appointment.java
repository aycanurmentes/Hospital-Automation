package Model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Helper.DBConnection;

public class Appointment {
	private int id, doctorID, patientID;
	private String doctorName, patientName, appDate;
	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Appointment() {
	}

	public Appointment(int id, int doctorID, int patientID, String doctorName, String patientName, String appDate) {
		super();
		this.id = id;
		this.doctorID = doctorID;
		this.patientID = patientID;
		this.doctorName = doctorName;
		this.patientName = patientName;
		this.appDate = appDate;
		
	}

	public void deleteAppoint(String date, String name) {
		Connection con = conn.connDb();
		try {
			st = (Statement) con.createStatement();
			String query1 = "DELETE FROM appointment WHERE app_date='" + date + "'";
			String query2 = "UPDATE whour SET status='a' WHERE doctor_name='" + name + "' AND wdate='" + date + "' ";

			preparedStatement = con.prepareStatement(query1);
			preparedStatement.executeUpdate();

			preparedStatement = con.prepareStatement(query2);
			preparedStatement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Appointment> getPatientList(int patient_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();

		try {
			st = (Statement) con.createStatement();
			rs = ((java.sql.Statement) st).executeQuery("SELECT * FROM appointment WHERE patient_id =" + patient_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setPatientID(rs.getInt("patient_id"));
				obj.setPatientName(rs.getString("patient_name"));
				obj.setAppDate(rs.getString("app_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			((Connection) st).close();
			rs.close();
			con.close();
		}

		return list;
	}

	public ArrayList<Appointment> getAppointmentList(int doctor_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();

		try {
			st = (Statement) con.createStatement();
			rs = ((java.sql.Statement) st).executeQuery("SELECT * FROM appointment WHERE doctor_id =" + doctor_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setPatientID(rs.getInt("patient_id"));
				obj.setPatientName(rs.getString("patient_name"));
				obj.setAppDate(rs.getString("app_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			((Connection) st).close();
			rs.close();
			con.close();
		}

		return list;
	}

	public ArrayList<Appointment> getDoctorList(int doctor_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();

		try {
			st = (Statement) con.createStatement();
			rs = ((java.sql.Statement) st).executeQuery("SELECT * FROM appointment WHERE hasta_id =" + doctor_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setPatientID(rs.getInt("patient_id"));
				obj.setPatientName(rs.getString("patient_name"));
				obj.setAppDate(rs.getString("app_date"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			((Connection) st).close();
			rs.close();
			con.close();
		}

		return list;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorID() {
		return doctorID;
	}

	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}
	
	
	
}
