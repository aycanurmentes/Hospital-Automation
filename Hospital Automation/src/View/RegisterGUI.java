package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Patient;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tc;
	private JPasswordField fld_pass;
	private Patient patient=new Patient();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	public RegisterGUI() {
		setResizable(false);
		setTitle("Hospital Automination");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 261, 270);
		w_pane = new JPanel();
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lbl_doctorName = new JLabel("name:");
		lbl_doctorName.setBounds(13, 6, 61, 16);
		w_pane.add(lbl_doctorName);
		
		fld_name = new JTextField();
		fld_name.setColumns(10);
		fld_name.setBounds(6, 22, 218, 26);
		w_pane.add(fld_name);
		
		JLabel ID = new JLabel("ID:");
		ID.setBounds(13, 54, 61, 16);
		w_pane.add(ID);
		
		fld_tc = new JTextField();
		fld_tc.setColumns(10);
		fld_tc.setBounds(6, 70, 218, 26);
		w_pane.add(fld_tc);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setBounds(13, 108, 106, 16);
		w_pane.add(lblPassword);
		
		fld_pass = new JPasswordField();
		fld_pass.setBounds(6, 126, 218, 26);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("register");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (fld_tc.getText().length() == 0 || fld_pass.getText().length() == 0
						|| fld_name.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					boolean control = patient.register(fld_tc.getText(), fld_pass.getText(), fld_name.getText());
					if (control) {
						Helper.showMsg("success");
						LoginGUI login = new LoginGUI();
						login.setVisible(true);
						dispose();
					} else {
						Helper.showMsg("error");
					}
				}
			}
		});
		btn_register.setBounds(28, 159, 184, 29);
		w_pane.add(btn_register);
		
		JButton btn_backTo = new JButton("go back");
		btn_backTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backTo.setBounds(28, 200, 184, 29);
		w_pane.add(btn_backTo);
	}
}
