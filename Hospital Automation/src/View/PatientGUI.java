package View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Clinic;
import Model.Patient;
import Model.Whour;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class PatientGUI extends JFrame {

	private JPanel w_pane;
	private static Patient patient=new Patient();
	private Clinic clinic=new Clinic();
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData=null;
	private JTable table_whour;
	private Whour whour=new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private int selectDoctorID=0;
	private String selectDoctorName=null;
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Appointment appoint = new Appointment();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientGUI frame = new PatientGUI(patient);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public PatientGUI(Patient patient) throws SQLException {
		
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Name Surname";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];
		
		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Date";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Doctor";
		colAppoint[2] = "Date";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for (int i = 0; i < appoint.getPatientList(patient.getId()).size(); i++) {
			appointData[0] = appoint.getPatientList(patient.getId()).get(i).getId();
			appointData[1] = appoint.getPatientList(patient.getId()).get(i).getDoctorName();
			appointData[2] = appoint.getPatientList(patient.getId()).get(i).getAppDate();
			appointModel.addRow(appointData);
		}



		setResizable(false);
		setTitle("Hospital Automination");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 348);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JButton btnExit = new JButton("exit");
		btnExit.setBounds(314, 1, 117, 29);
		w_pane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("welcome dear"+patient.getName());
		lblNewLabel.setBounds(6, 6, 200, 16);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(0, 40, 452, 280);
		w_pane.add(w_tab);
		
		JPanel w_appointment = new JPanel();
		w_tab.addTab("appointment system", null, w_appointment, null);
		w_appointment.setLayout(null);
		
		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(6, 25, 161, 203);
		w_appointment.add(w_scrollDoctor);
		
		table_doctor = new JTable();
		w_scrollDoctor.setColumnHeaderView(table_doctor);
		
		JLabel lbl_doctorList = new JLabel("doctor list");
		lbl_doctorList.setBounds(6, 6, 84, 16);
		w_appointment.add(lbl_doctorList);
		
		JLabel lbl_clinicName = new JLabel("clinic name:");
		lbl_clinicName.setBounds(179, 28, 90, 16);
		w_appointment.add(lbl_clinicName);
		
		JComboBox select_Clinic = new JComboBox();
		select_Clinic.setBounds(179, 49, 97, 27);
		for (int i = 0; i < clinic.getList().size(); i++) {
			select_Clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));

		}
		select_Clinic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_Clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0); //reset the table in case there is a value
					try {
						for (int i = 0; i < clinic.getClinicDoctorList(item.getKey()).size(); i++) {
							doctorData[0] = clinic.getClinicDoctorList(item.getKey()).get(i).getId();
							doctorData[1] = clinic.getClinicDoctorList(item.getKey()).get(i).getName();
							doctorModel.addRow(doctorData);

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}
			}

		});
		w_appointment.add(select_Clinic);
		
		JLabel lblSelectDoctor = new JLabel("select doctor:");
		lblSelectDoctor.setBounds(179, 79, 90, 16);
		w_appointment.add(lblSelectDoctor);
		
		JButton btn_selDoctor = new JButton("select");
		btn_selDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doctor.getSelectedRow();
				if (row >= 0) {
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < whour.getWhourList(id).size(); i++) {
							whourData[0] = whour.getWhourList(id).get(i).getId();
							whourData[1] = whour.getWhourList(id).get(i).getWdate();
							whourModel.addRow(whourData);

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_whour.setModel(whourModel);
					selectDoctorID = id;
					selectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();
				} else {
					Helper.showMsg("Please choose a doctor!");
				}
				
			}
		});
		btn_selDoctor.setBounds(179, 97, 97, 29);
		w_appointment.add(btn_selDoctor);
		
		JLabel lbl_doctorList_1 = new JLabel("doctor list");
		lbl_doctorList_1.setBounds(281, 6, 144, 16);
		w_appointment.add(lbl_doctorList_1);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(291, 25, 144, 203);
		w_appointment.add(w_scrollWhour);
		
		table_whour = new JTable();
		w_scrollWhour.setViewportView(table_whour);
		
		JLabel lblMakeAnAppointment = new JLabel("appointment:");
		lblMakeAnAppointment.setBounds(179, 138, 90, 16);
		w_appointment.add(lblMakeAnAppointment);
		
		JButton btn_addAppoint = new JButton("take it");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if (selRow >= 0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
						boolean control = patient.addAppointment(selectDoctorID, patient.getId(), selectDoctorName,
								patient.getName(), date);
						if (control) {
							Helper.showMsg("success");
							patient.updateWhourStatus(selectDoctorID, date);
							updateWhourModel(selectDoctorID);
							updateAppointModel(patient.getId());

						} else {
							Helper.showMsg("error");
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Please enter a valid date!");
				}
			}
		});
		btn_addAppoint.setBounds(179, 156, 97, 29);
		w_appointment.add(btn_addAppoint);
		
		JPanel w_appoint = new JPanel();
		w_tab.addTab("my appointments", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(6, 6, 419, 222);
		w_appoint.add(w_scrollAppoint);
		
		table_appoint = new JTable(appointModel);
		w_scrollAppoint.setViewportView(table_appoint);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5);
	}
	public void updateWhourModel(int doctor_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < whour.getWhourList(doctor_id).size(); i++) {
			whourData[0] = whour.getWhourList(doctor_id).get(i).getId();
			whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
			whourModel.addRow(whourData);
		}
	}
	public void updateAppointModel(int patient_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);
		try {
			for (int i = 0; i < appoint.getPatientList(patient_id).size(); i++) {
				appointData[0] = appoint.getPatientList(patient_id).get(i).getId();
				appointData[1] = appoint.getPatientList(patient_id).get(i).getDoctorName();
				appointData[2] = appoint.getPatientList(patient_id).get(i).getAppDate();
				appointModel.addRow(appointData);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
