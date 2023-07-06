package View;

import java.awt.EventQueue;

import java.awt.Point;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Chief;
import Model.Clinic;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JComboBox;

public class ChiefGUI extends JFrame {

	static Chief chief=new Chief();;
	Clinic clinic = new Clinic();
	private JPanel w_pane;
	private JTable table_doctor;
	private JTextField textDoctorName;
	private JTextField textDoctorID;
	private JPasswordField textDoctorPassword;
	private JTextField textUserID;
	private DefaultTableModel doctorModel=null;
	private Object[] doctorData=null;
	private JTable table_clinic;
	private JTextField textClinicName;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JTable table_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiefGUI frame = new ChiefGUI(chief);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}
	public ChiefGUI(Chief chief) {
		setTitle("Hospital system");
		//doctor model
		doctorModel=new DefaultTableModel();
		Object[] colDoctorName=new Object[4];
		colDoctorName[0]="ID"; 
		colDoctorName[1]="name"; 
		colDoctorName[2]="tc"; 
		colDoctorName[3]="password"; 
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData=new Object[4];
		for(int i=0;i<chief.getDoctorlist().size();i++) {
			doctorData[0]=chief.getDoctorlist().get(i).getId();
			doctorData[1]=chief.getDoctorlist().get(i).getName();
			doctorData[2]=chief.getDoctorlist().get(i).getTc();
			doctorData[3]=chief.getDoctorlist().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		// WorkerModel
				DefaultTableModel workerModel = new DefaultTableModel();
				Object[] colWorker = new Object[2];
				colWorker[0] = "ID";
				colWorker[1] = "Name Surname";
				workerModel.setColumnIdentifiers(colWorker);
				Object[] workerData = new Object[2];
			// Clinic model
			clinicModel = new DefaultTableModel();
			Object[] colClinic = new Object[2];
			colClinic[0] = "ID";
			colClinic[1] = "Clinic name";
			clinicModel.setColumnIdentifiers(colClinic);
			clinicData = new Object[2];
			for (int i = 0; i < clinic.getList().size(); i++) {
				clinicData[0] = clinic.getList().get(i).getId();
				clinicData[1] = clinic.getList().get(i).getName();
				clinicModel.addRow(clinicData);
			}
			
		
	
	/**
	 * Create the frame.
	 */
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 348);
		JComponent contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("welcome dear<dynamic>");
		lblNewLabel.setBounds(6, 6, 200, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnExit = new JButton("exit");
		btnExit.setBounds(314, 1, 117, 29);
		contentPane.add(btnExit);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(6, 34, 452, 280);
		contentPane.add(w_tab);
		
		JPanel w_doctors = new JPanel();
		w_tab.addTab("doctors", null, w_doctors, null);
		w_doctors.setLayout(null);
		
		JScrollPane w_ScrollDoctor = new JScrollPane();
		w_ScrollDoctor.setBounds(6, 6, 265, 222);
		w_doctors.add(w_ScrollDoctor);
		
		JScrollBar w_scrollDoctor = new JScrollBar();
		w_ScrollDoctor.setRowHeaderView(w_scrollDoctor);
		
		table_doctor = new JTable(doctorModel);
		w_ScrollDoctor.setColumnHeaderView(table_doctor);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				try {
					textUserID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}
			}
		});
		table_doctor.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTc = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPass = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();

					try {
						boolean control = chief.updateDoctor(selectID, selectTc, selectPass,selectName);
						if (control) {
							// Helper.showMsg("success");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		
		JButton btnDelete = new JButton("delete");
		btnDelete.setBounds(293, 199, 117, 29);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textDoctorID.getText().length() == 0) {
					Helper.showMsg("Please select a valid doctor\n");

				} else {
					if (Helper.confirm("sure")) {

						int selectID = Integer.parseInt(textDoctorID.getText());
						try {
							boolean control = chief.deleteDoctor(selectID);
							if (control) {
								Helper.showMsg("success");
								textDoctorID.setText(null);
								updateDoctorModel();
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
				
			}
		});
		w_doctors.add(btnDelete);
		
		textUserID = new JTextField();
		textUserID.setColumns(10);
		textUserID.setBounds(276, 177, 130, 26);
		w_doctors.add(textUserID);
		
		JLabel lbl_UserID = new JLabel("user ID:");
		lbl_UserID.setBounds(283, 159, 61, 16);
		w_doctors.add(lbl_UserID);
		
		JButton btnAdd = new JButton("add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					if(textDoctorName.getText().length()==0 || textDoctorPassword.getText().length()==0 || textDoctorID.getText().length()==0) {
						Helper.showMsg("fill");
						
					
				}else {
						try {
						boolean control=chief.addDoctor(textDoctorName.getText(),textDoctorPassword.getText() , textDoctorID.getText());
								if (control) {
									Helper.showMsg("success");
									textDoctorName.setText(null);
									textDoctorID.setText(null);
									textDoctorPassword.setText(null);
									updateDoctorModel();
								}
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					}
					
				}
			
		});
		
		btnAdd.setBounds(289, 133, 117, 29);
		w_doctors.add(btnAdd);
		
		textDoctorPassword = new JPasswordField();
		textDoctorPassword.setBounds(276, 110, 134, 26);
		w_doctors.add(textDoctorPassword);
		
		JLabel lbl_DoctorPass = new JLabel("password:");
		lbl_DoctorPass.setBounds(283, 86, 99, 16);
		w_doctors.add(lbl_DoctorPass);
		
		textDoctorID = new JTextField();
		textDoctorID.setColumns(10);
		textDoctorID.setBounds(276, 60, 135, 26);
		w_doctors.add(textDoctorID);
		
		textDoctorName = new JTextField();
		textDoctorName.setColumns(10);
		textDoctorName.setBounds(276, 22, 130, 26);
		w_doctors.add(textDoctorName);
		
		JLabel lbl_doctorName = new JLabel("name:");
		lbl_doctorName.setBounds(283, 6, 61, 16);
		w_doctors.add(lbl_doctorName);
		
		JLabel lbl_DoctorID = new JLabel("ID:");
		lbl_DoctorID.setBounds(283, 46, 61, 16);
		w_doctors.add(lbl_DoctorID);
		
		JPanel w_clinics = new JPanel();
		w_tab.addTab("clinics", null, w_clinics, null);
		w_clinics.setLayout(null);
		
		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(6, 6, 162, 222);
		w_clinics.add(w_scrollClinic);
		
		clinicMenu=new JPopupMenu();
		JMenuItem updateMenu=new JMenuItem("update");
		JMenuItem deleteMenu=new JMenuItem("delete");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);
		
		updateMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFech(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
			}

		});
		
		deleteMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("sure")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();

						} else {
							Helper.showMsg("error");
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		table_clinic = new JTable(clinicModel);
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectedRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectedRow, selectedRow);
			}
		});
		w_scrollClinic.setColumnHeaderView(table_clinic);
		
		JLabel lbl_clinicName = new JLabel("clinic name:");
		lbl_clinicName.setBounds(175, 18, 90, 16);
		w_clinics.add(lbl_clinicName);
		
		textClinicName = new JTextField();
		textClinicName.setColumns(10);
		textClinicName.setBounds(168, 34, 97, 26);
		w_clinics.add(textClinicName);
		
		JButton btn_AddClinic = new JButton("add");
		btn_AddClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (lbl_clinicName.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						if (clinic.addClinic(lbl_clinicName.getText())) {
							Helper.showMsg("success");
							lbl_clinicName.setText(null);
							updateClinicModel();
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btn_AddClinic.setBounds(168, 59, 97, 29);
		w_clinics.add(btn_AddClinic);
		
		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(265, 6, 160, 222);
		w_clinics.add(w_scrollWorker);
		
		table_worker = new JTable();
		w_scrollWorker.setViewportView(table_worker);
		
		JComboBox select_doctor = new JComboBox();
		for(int i=0;i<chief.getDoctorlist().size();i++) {
			select_doctor.addItem(new Item(chief.getDoctorlist().get(i).getId(),chief.getDoctorlist().get(i).getName()));
		}
		
		select_doctor.addActionListener(e-> {
		JComboBox c= (JComboBox) e.getSource();
		Item item=(Item) c.getSelectedItem();
		System.out.println(item.getKey()+":"+item.getValue());
		
		});
		select_doctor.setBounds(168, 165, 97, 27);
		w_clinics.add(select_doctor);
		
		JButton btn_AddWorker = new JButton("add");
		btn_AddWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow=table_clinic.getSelectedRow();
				if(selRow>=0) {
					String selClinic=table_clinic.getModel().getValueAt(selRow,0).toString();
					int selClinicID=Integer.parseInt(selClinic);
					Item doctorItem=(Item) select_doctor.getSelectedItem();
					try {
					boolean control=chief.addWorker(doctorItem.getKey(), selClinicID);
							if(control) {
								Helper.showMsg("success");
								DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
								clearModel.setRowCount(0);
								for (int i = 0; i < chief.getClinicDoctorlist(selClinicID).size(); i++) {
									workerData[0] = chief.getClinicDoctorlist(selClinicID).get(i).getId();
									workerData[1] = chief.getClinicDoctorlist(selClinicID).get(i).getName();
									workerModel.addRow(workerData);
								}
								table_worker.setModel(workerModel);
							}
							else {
								Helper.showMsg("error");
							}
				}
					catch(Exception e1) {
						e1.printStackTrace();
					}
				}else {
						Helper.showMsg("please choose any clinic");
					}
					
					
			}
		});
		
		btn_AddWorker.setBounds(168, 187, 97, 29);
		w_clinics.add(btn_AddWorker);
		
		JLabel lbl_clinicName_1 = new JLabel("clinic name:");
		lbl_clinicName_1.setBounds(175, 96, 90, 16);
		w_clinics.add(lbl_clinicName_1);
		
		JButton btn_workerSelect = new JButton("select");
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);
					try {
						for (int i = 0; i < chief.getClinicDoctorlist(selClinicID).size(); i++) {
							workerData[0] = chief.getClinicDoctorlist(selClinicID).get(i).getId();
							workerData[1] = chief.getClinicDoctorlist(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);

				} else {
					Helper.showMsg("please select any clinic");

				}
			}
		});
		btn_workerSelect.setBounds(168, 113, 97, 29);
		w_clinics.add(btn_workerSelect);
	}
	public void updateDoctorModel() throws Exception {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for(int i=0;i<chief.getDoctorlist().size();i++) {
			doctorData[0]=chief.getDoctorlist().get(i).getId();
			doctorData[1]=chief.getDoctorlist().get(i).getName();
			doctorData[2]=chief.getDoctorlist().get(i).getTc();
			doctorData[3]=chief.getDoctorlist().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		
	}
	public void updateClinicModel() throws Exception {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();
			clinicModel.addRow(clinicData);
		}
	}
}
