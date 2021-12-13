package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.Apointment;
import Model.Bashekim;
import Model.Clinic;
import Model.Whour;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import Helper.*;
import javax.swing.JComboBox;

public class BashekimGUI extends JFrame {
	static Bashekim bashekim = new Bashekim();
	Clinic clinic = new Clinic();

	private JPanel w_pane;
	private JTextField fld_dName;
	private JTextField fld_dTcno;
	private JPasswordField fld_dPass;
	private JTextField fld_doctorID;
	private JTable table_doctor;
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JTable table_clinic;
	private JTextField fld_clinicName;
	private JTable table_worker;
	private DefaultTableModel clinicModel = null;
	private Object[] clinicData = null;
	private JPopupMenu clinicMenu;
	private JComboBox select_doctor = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BashekimGUI frame = new BashekimGUI(bashekim);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public BashekimGUI(Bashekim bashekim) throws SQLException {
		// doctor model
		doctorModel = new DefaultTableModel();
		Object[] colDoctorName = new Object[4];
		colDoctorName[0] = "ID";
		colDoctorName[1] = "Ad Soyad";
		colDoctorName[2] = "TC NO";
		colDoctorName[3] = "Þifre";
		doctorModel.setColumnIdentifiers(colDoctorName);
		doctorData = new Object[4];
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}
		// clinic model
		clinicModel = new DefaultTableModel();
		Object[] colClinic = new Object[2];
		colClinic[0] = "ID";
		colClinic[1] = "Polikinlik Adý";

		clinicModel.setColumnIdentifiers(colClinic);
		clinicData = new Object[2];
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();

			clinicModel.addRow(clinicData);
		}
		// worker model
		DefaultTableModel workerModel = new DefaultTableModel();
		Object[] colWorker = new Object[2];
		colWorker[0] = "ID";
		colWorker[1] = "Ad Soyad";
		workerModel.setColumnIdentifiers(colWorker);
		Object[] workerData = new Object[2];

		setResizable(false);
		setTitle("Hastene Y\u00F6netim Sistemi Ba\u015Fhekim Giri\u015Fi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ho\u015Fgeldiniz, Say\u0131n " + bashekim.getName());
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setBackground(new Color(224, 255, 255));
		lblNewLabel.setFont(new Font("Impact", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 422, 27);
		w_pane.add(lblNewLabel);

		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.setBackground(new Color(245, 245, 245));
		btnNewButton.setForeground(new Color(220, 20, 60));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Impact", Font.PLAIN, 16));
		btnNewButton.setBounds(580, 16, 132, 33);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setForeground(new Color(220, 20, 60));
		w_tab.setFont(new Font("Impact", Font.PLAIN, 11));
		w_tab.setBackground(new Color(220, 220, 220));
		w_tab.setBounds(36, 107, 662, 355);
		w_pane.add(w_tab);

		JPanel w_doctor = new JPanel();
		w_doctor.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Doktor Yönetimi", null, w_doctor, null);
		w_doctor.setLayout(null);

		JLabel label = new JLabel("Ad Soyad :");
		label.setForeground(new Color(220, 20, 60));
		label.setFont(new Font("Impact", Font.PLAIN, 14));
		label.setBounds(403, 32, 153, 20);
		w_doctor.add(label);

		fld_dName = new JTextField();
		fld_dName.setBackground(new Color(245, 245, 245));
		fld_dName.setBounds(403, 52, 168, 20);
		w_doctor.add(fld_dName);
		fld_dName.setColumns(10);

		JLabel label_2 = new JLabel("T.C. No:");
		label_2.setBackground(new Color(255, 255, 255));
		label_2.setForeground(new Color(220, 20, 60));
		label_2.setFont(new Font("Impact", Font.PLAIN, 14));
		label_2.setBounds(403, 83, 153, 20);
		w_doctor.add(label_2);

		fld_dTcno = new JTextField();
		fld_dTcno.setBackground(new Color(245, 245, 245));
		fld_dTcno.setColumns(10);
		fld_dTcno.setBounds(403, 106, 168, 20);
		w_doctor.add(fld_dTcno);

		JLabel label_3 = new JLabel("\u015Eifre:");
		label_3.setForeground(new Color(220, 20, 60));
		label_3.setBackground(new Color(224, 255, 255));
		label_3.setFont(new Font("Impact", Font.PLAIN, 14));
		label_3.setBounds(403, 140, 153, 20);
		w_doctor.add(label_3);

		fld_dPass = new JPasswordField();
		fld_dPass.setBackground(new Color(245, 245, 245));
		fld_dPass.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		fld_dPass.setBounds(403, 160, 168, 20);
		w_doctor.add(fld_dPass);

		JLabel label_4 = new JLabel("Kullan\u0131c\u0131 ID:");
		label_4.setForeground(new Color(220, 20, 60));
		label_4.setFont(new Font("Impact", Font.PLAIN, 14));
		label_4.setBounds(403, 230, 153, 20);
		w_doctor.add(label_4);

		JButton btn_addDoctor = new JButton("EKLE");
		btn_addDoctor.setBackground(new Color(245, 245, 245));
		btn_addDoctor.setForeground(new Color(220, 20, 60));
		btn_addDoctor.setFont(new Font("Impact", Font.PLAIN, 13));
		btn_addDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_dName.getText().length() == 0 || fld_dPass.getText().length() == 0
						|| fld_dTcno.getText().length() == 0) {
					Helper.showMsg("fill");

				} else {
					try {
						boolean control = bashekim.addDoctor(fld_dTcno.getText(), fld_dPass.getText(),
								fld_dName.getText());
						if (control) {
							Helper.showMsg("success");
							fld_dName.setText(null);
							fld_dTcno.setText(null);
							fld_dPass.setText(null);
							updateDoctorModel();
							updatecb();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addDoctor.setBounds(403, 191, 168, 28);
		w_doctor.add(btn_addDoctor);

		fld_doctorID = new JTextField();
		fld_doctorID.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 11));
		fld_doctorID.setForeground(new Color(0, 0, 139));
		fld_doctorID.setBackground(new Color(245, 245, 245));
		fld_doctorID.setColumns(10);
		fld_doctorID.setBounds(403, 255, 168, 20);
		w_doctor.add(fld_doctorID);

		JButton btn_delDoctor = new JButton("S\u0130L");
		btn_delDoctor.setForeground(new Color(220, 20, 60));
		btn_delDoctor.setFont(new Font("Impact", Font.PLAIN, 11));
		btn_delDoctor.setBackground(new Color(245, 245, 245));
		btn_delDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorID.getText().length() == 0) {
					Helper.showMsg("Lütfen geçerli bir doktor seçiniz!");
				} else {
					if (Helper.confirm("sure")) {
						int selectedID = Integer.parseInt(fld_doctorID.getText());
                        
						try {
							boolean control = bashekim.deleteDoctor(selectedID);
							if (control) {
								Helper.showMsg("success");
								fld_doctorID.setText(null);
								updateDoctorModel();
								Whour wh = new Whour();
								wh.deletewhour(selectedID);
								Apointment app = new Apointment();
								app.deleteAppointt(selectedID);
								updatecb();
								bashekim.deleteworker(selectedID);
							}

						} catch (SQLException ex) {

						}
					}
				}
			}
		});
		btn_delDoctor.setBounds(403, 287, 168, 28);
		w_doctor.add(btn_delDoctor);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(10, 11, 383, 305);
		w_doctor.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		table_doctor.setForeground(new Color(0, 0, 0));
		table_doctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		table_doctor.setBackground(new Color(245, 245, 245));
		w_scrollDoctor.setViewportView(table_doctor);

		JPanel w_clinic = new JPanel();
		w_clinic.setForeground(new Color(245, 245, 245));
		w_clinic.setBackground(new Color(245, 245, 245));
		w_tab.addTab("Polikinlikler", null, w_clinic, null);
		w_clinic.setLayout(null);

		JScrollPane w_scrollClinic = new JScrollPane();
		w_scrollClinic.setBounds(10, 11, 259, 305);
		w_clinic.add(w_scrollClinic);

		clinicMenu = new JPopupMenu();
		JMenuItem updateMenu = new JMenuItem("Güncelle");
		JMenuItem deleteMenu = new JMenuItem("Sil");
		clinicMenu.add(updateMenu);
		clinicMenu.add(deleteMenu);

		updateMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
				Clinic selectClinic = clinic.getFetch(selID);
				UpdateClinicGUI updateGUI = new UpdateClinicGUI(selectClinic);
				updateGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				updateGUI.setVisible(true);
				updateGUI.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosed(WindowEvent e) {
						try {
							updateClinicModel();
						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
						}
					}
				});
			}
		});
		deleteMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("del")) {
					int selID = Integer.parseInt(table_clinic.getValueAt(table_clinic.getSelectedRow(), 0).toString());
					try {
						if (clinic.deleteClinic(selID)) {
							Helper.showMsg("success");
							updateClinicModel();

						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						// e1.printStackTrace();
					}
				}
			}

		});

		table_clinic = new JTable(clinicModel);
		table_clinic.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		table_clinic.setBackground(new Color(245, 245, 245));
		table_clinic.setComponentPopupMenu(clinicMenu);
		table_clinic.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Point point = e.getPoint();
				int selectRow = table_clinic.rowAtPoint(point);
				table_clinic.setRowSelectionInterval(selectRow, selectRow);
			}
		});
		w_scrollClinic.setViewportView(table_clinic);

		fld_clinicName = new JTextField();
		fld_clinicName.setBackground(new Color(245, 245, 245));
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(279, 31, 143, 20);
		w_clinic.add(fld_clinicName);

		JLabel lblPolikinlikEkle = new JLabel("Polikinlik Ekle:");
		lblPolikinlikEkle.setForeground(new Color(220, 20, 60));
		lblPolikinlikEkle.setFont(new Font("Impact", Font.PLAIN, 14));
		lblPolikinlikEkle.setBounds(279, 11, 153, 20);
		w_clinic.add(lblPolikinlikEkle);

		JButton btn_addClinic = new JButton("EKLE");
		btn_addClinic.setBackground(new Color(245, 245, 245));
		btn_addClinic.setForeground(new Color(220, 20, 60));
		btn_addClinic.setFont(new Font("Impact", Font.PLAIN, 11));
		btn_addClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_clinicName.getText().length() == 0) {
					Helper.showMsg("fill");

				} else {
					try {
						if (clinic.addClinic(fld_clinicName.getText())) {
							Helper.showMsg("success");
							fld_clinicName.setText(null);
							updateClinicModel();

						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btn_addClinic.setBounds(279, 67, 143, 28);
		w_clinic.add(btn_addClinic);

		JScrollPane w_scrollWorker = new JScrollPane();
		w_scrollWorker.setBounds(442, 11, 205, 305);
		w_clinic.add(w_scrollWorker);

		table_worker = new JTable();
		table_worker.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		table_worker.setBackground(new Color(245, 245, 245));
		w_scrollWorker.setViewportView(table_worker);

		select_doctor = new JComboBox();

		select_doctor.setBackground(new Color(245, 245, 245));
		select_doctor.setFont(new Font("Impact", Font.PLAIN, 11));
		select_doctor.setForeground(new Color(0, 0, 0));
		select_doctor.setBounds(279, 249, 143, 28);

		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			select_doctor.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}

		/*
		 * select_doctor.addActionListener(e -> {
		 * 
		 * JComboBox c = (JComboBox) e.getSource(); Item item = (Item)
		 * c.getSelectedItem(); System.out.println(item.getKey() + ":" +
		 * item.getValue()); });
		 */
		w_clinic.add(select_doctor);

		JButton btn_addWorker = new JButton("EKLE");
		btn_addWorker.setBackground(new Color(245, 245, 245));
		btn_addWorker.setForeground(new Color(220, 20, 60));
		btn_addWorker.setFont(new Font("Impact", Font.PLAIN, 11));
		btn_addWorker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					Item doctorItem = (Item) select_doctor.getSelectedItem();
					try {
						boolean control = bashekim.addWorker(doctorItem.getKey(), selClinicID);
						if (control) {
							Helper.showMsg("success");
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					Helper.showMsg("Lütfen bir polikinlik seçiniz!");
				}

			}
		});
		btn_addWorker.setBounds(279, 288, 143, 28);
		w_clinic.add(btn_addWorker);

		JLabel lblPolikinlikAd = new JLabel("Polikinlik Ad\u0131:");
		lblPolikinlikAd.setForeground(new Color(220, 20, 60));
		lblPolikinlikAd.setFont(new Font("Impact", Font.PLAIN, 14));
		lblPolikinlikAd.setBounds(279, 125, 153, 20);
		w_clinic.add(lblPolikinlikAd);

		JButton btn_workerSelect = new JButton("SE\u00C7");
		btn_workerSelect.setBackground(new Color(245, 245, 245));
		btn_workerSelect.setForeground(new Color(220, 20, 60));
		btn_workerSelect.setFont(new Font("Impact", Font.PLAIN, 11));
		btn_workerSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_clinic.getSelectedRow();
				if (selRow >= 0) {
					String selClinic = table_clinic.getModel().getValueAt(selRow, 0).toString();
					int selClinicID = Integer.parseInt(selClinic);
					DefaultTableModel clearModel = (DefaultTableModel) table_worker.getModel();
					clearModel.setRowCount(0);

					try {
						for (int i = 0; i < bashekim.getClinicDoctorList(selClinicID).size(); i++) {
							workerData[0] = bashekim.getClinicDoctorList(selClinicID).get(i).getId();
							workerData[1] = bashekim.getClinicDoctorList(selClinicID).get(i).getName();
							workerModel.addRow(workerData);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					table_worker.setModel(workerModel);
				} else {
					Helper.showMsg("Lütfen bir poliklinik seçiniz!");
				}
			}
		});
		btn_workerSelect.setBounds(279, 151, 143, 28);
		w_clinic.add(btn_workerSelect);
		table_doctor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					fld_doctorID.setText(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
				} catch (Exception ex) {
					// e1.printStackTrace();
				}
			}

		});
		table_doctor.getModel().addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer
							.parseInt(table_doctor.getValueAt(table_doctor.getSelectedRow(), 0).toString());
					String selectName = table_doctor.getValueAt(table_doctor.getSelectedRow(), 1).toString();
					String selectTcno = table_doctor.getValueAt(table_doctor.getSelectedRow(), 2).toString();
					String selectPass = table_doctor.getValueAt(table_doctor.getSelectedRow(), 3).toString();

					try {
						boolean control = bashekim.updateDoctor(selectID, selectTcno, selectPass, selectName);

					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}

		});
	}

	public void updateDoctorModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			doctorData[0] = bashekim.getDoctorList().get(i).getId();
			doctorData[1] = bashekim.getDoctorList().get(i).getName();
			doctorData[2] = bashekim.getDoctorList().get(i).getTcno();
			doctorData[3] = bashekim.getDoctorList().get(i).getPassword();
			doctorModel.addRow(doctorData);
		}

	}

	public void updateClinicModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_clinic.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < clinic.getList().size(); i++) {
			clinicData[0] = clinic.getList().get(i).getId();
			clinicData[1] = clinic.getList().get(i).getName();

			clinicModel.addRow(clinicData);
		}

	}

	public void updatecb() throws SQLException {
		select_doctor.removeAllItems();
		for (int i = 0; i < bashekim.getDoctorList().size(); i++) {
			select_doctor.addItem(
					new Item(bashekim.getDoctorList().get(i).getId(), bashekim.getDoctorList().get(i).getName()));
		}

	}
}
