package View;
import java.awt.EventQueue;
import Helper.*;
import Model.Chief;
import Model.Patient;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_patientID;
	private JTextField fld_patientPass;
	private JTextField fld_doctorID;
	private JPasswordField fld_doctorPass;
	private DBConnection conn=new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hospital Automation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("welcome to hospital management system");
		lblNewLabel.setBounds(79, 77, 266, 16);
		w_pane.add(lblNewLabel);
		

		JLabel lblLogo = new JLabel(new ImageIcon(getClass().getResource("medicinee.png")));
		lblLogo.setBounds(149, 6, 120, 106);
		w_pane.add(lblLogo);
		
		JTabbedPane w_tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		w_tabbedPane.setBounds(6, 109, 438, 157);
		w_pane.add(w_tabbedPane);
		
		JPanel panel = new JPanel();
		w_tabbedPane.addTab("patient", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblTc = new JLabel("ID number(T.C.):");
		lblTc.setBounds(6, 6, 113, 16);
		panel.add(lblTc);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(6, 49, 99, 16);
		panel.add(lblPassword);
		
		fld_patientID = new JTextField();
		fld_patientID.setBounds(145, 1, 130, 26);
		panel.add(fld_patientID);
		fld_patientID.setColumns(10);
		
		fld_patientPass = new JTextField();
		fld_patientPass.setColumns(10);
		fld_patientPass.setBounds(145, 44, 130, 26);
		panel.add(fld_patientPass);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI rGUI=new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
			}
		});
		btnRegister.setBounds(16, 77, 148, 29);
		panel.add(btnRegister);
		
		JButton btnLogin_patient = new JButton("Login");
		btnLogin_patient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (fld_patientID.getText().length() == 0 || fld_patientPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean key = true;

					try {
						Connection con = conn.connDb();
						Statement st = (Statement) con.createStatement();
						ResultSet rs = ((java.sql.Statement) st).executeQuery("SELECT *FROM user");
						while (rs.next()) {
							if (fld_patientID.getText().equals(rs.getString("tc"))
									&& fld_patientPass.getText().equalsIgnoreCase(rs.getString("pasword"))) {
								if (rs.getString("type").equals("hasta")) {
									Patient patient = new Patient();
									patient.setId(rs.getInt("id"));
									patient.setPassword("pasword");
									patient.setTc(rs.getString("tc"));
									patient.setName(rs.getString("name"));
									patient.setType(rs.getString("type"));
									PatientGUI pGUI = new PatientGUI(patient);
									pGUI.setVisible(true);
									dispose();
									key = false;
								}
							}
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					if (key)
						Helper.showMsg("No such patient was found, please register.");

				}	
			}
		});
		btnLogin_patient.setBounds(176, 77, 138, 29);
		panel.add(btnLogin_patient);
		
		JPanel w_doctorLogin = new JPanel();
		w_tabbedPane.addTab("doctor", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(0, 0, 417, 111);
		w_doctorLogin.add(panel_1);
		
		JLabel lblTc_1 = new JLabel("ID number(T.C.):");
		lblTc_1.setBounds(6, 6, 113, 16);
		panel_1.add(lblTc_1);
		
		JLabel lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(6, 49, 99, 16);
		panel_1.add(lblPassword_1);
		
		fld_doctorID = new JTextField();
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(145, 1, 130, 26);
		panel_1.add(fld_doctorID);
		
		JButton btnLoginDoctor = new JButton("Login");
		btnLoginDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_doctorID.getText().length()==0 || fld_doctorPass.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					try {
					Connection con=conn.connDb();
					Statement st=(Statement) con.createStatement();
					ResultSet rs=((java.sql.Statement) st).executeQuery("SELECT * FROM user");
					while(rs.next()) {
						if(fld_doctorID.getText().equals(rs.getString("idno")) && fld_doctorPass.getText().equals(rs.getString("password"))) {
							Chief chief=new Chief();
							chief.setId(rs.getInt("id"));
							chief.setPassword("password");
							chief.setName(rs.getString("name"));
							chief.setType(rs.getString("type"));
							ChiefGUI cGUI =new ChiefGUI(chief);
							cGUI.setVisible(true);
							dispose();
							
						
						}
					}
				}
					catch(SQLException e1) {
						e1.printStackTrace();
					}
			}
			}
		});
		btnLoginDoctor.setBounds(16, 77, 298, 29);
		panel_1.add(btnLoginDoctor);
		
		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBounds(145, 44, 130, 21);
		panel_1.add(fld_doctorPass);
	}
}
