package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Apointment;
import Model.Clinic;
import Model.Hasta;
import Model.Whour;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class HastaGUI extends JFrame {

	private JPanel contentPane;
	private static Hasta hasta = new Hasta();
	private Clinic clinic = new Clinic();
	private DefaultTableModel doctorModel;
	private Object[] doctorData = null;
	private JPopupMenu hastaMenu;
	private JTable table_doctor;
	private Whour whour = new Whour();
	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private JTable table_whour;
	private int selectDoctorID = 0;
	private String selectDoctorName = null;
	private JTable table_appoint;
	private DefaultTableModel appointModel;
	private Object[] appointData = null;
	private Apointment appoint = new Apointment();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HastaGUI frame = new HastaGUI(hasta);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. @throws
	 */
	public HastaGUI(Hasta hasta) throws SQLException {
		doctorModel = new DefaultTableModel();
		Object[] colDoctor = new Object[2];
		colDoctor[0] = "ID";
		colDoctor[1] = "Ad Soyad";
		doctorModel.setColumnIdentifiers(colDoctor);
		doctorData = new Object[2];
		
		
		whourModel = new DefaultTableModel();
		Object[] colWhour= new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];

		appointModel = new DefaultTableModel();
		Object[] colAppoint= new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Doktor";
		colAppoint[2]= "Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for(int i = 0; i< appoint.getHastaList(hasta.getId()).size();i++) {
			appointData[0]= appoint.getHastaList(hasta.getId()).get(i).getId();
			appointData[1]= appoint.getHastaList(hasta.getId()).get(i).getDoctorName();
			appointData[2]= appoint.getHastaList(hasta.getId()).get(i).getAppDate();
			 	appointModel.addRow(appointData);
		}

		setTitle("Hastane Y\u00F6netim Sistemi Hasta Giri\u015Fi");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Ho\u015Fgeldiniz, Say\u0131n " + hasta.getName());
		label.setForeground(new Color(220, 20, 60));
		label.setBackground(new Color(224, 255, 255));
		label.setFont(new Font("Impact", Font.PLAIN, 16));
		label.setBounds(10, 11, 422, 27);
		contentPane.add(label);

		JButton button = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		button.setForeground(new Color(220, 20, 60));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login =new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		button.setBackground(new Color(245, 245, 245));
		button.setFont(new Font("Impact", Font.PLAIN, 16));
		button.setBounds(580, 16, 132, 33);
		contentPane.add(button);
		
		

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setForeground(new Color(220, 20, 60));
		w_tab.setFont(new Font("Impact", Font.PLAIN, 11));
		w_tab.setBackground(new Color(220, 220, 220));
		w_tab.setBounds(23, 84, 662, 355);
		contentPane.add(w_tab);

		JPanel w_appointmen = new JPanel();
		w_appointmen.setForeground(new Color(0, 0, 255));
		w_appointmen.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Randevu Sistemi", null, w_appointmen, null);
		w_appointmen.setLayout(null);

		JScrollPane w_scrollDoctor = new JScrollPane();
		w_scrollDoctor.setBounds(21, 54, 230, 262);
		w_appointmen.add(w_scrollDoctor);

		table_doctor = new JTable(doctorModel);
		table_doctor.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		table_doctor.setBackground(new Color(245, 245, 245));
		w_scrollDoctor.setViewportView(table_doctor);

		JLabel label_1 = new JLabel("Doktor Listesi");
		label_1.setBackground(new Color(255, 255, 255));
		label_1.setForeground(new Color(220, 20, 60));
		label_1.setFont(new Font("Impact", Font.PLAIN, 14));
		label_1.setBounds(21, 33, 121, 21);
		w_appointmen.add(label_1);

		JLabel label_2 = new JLabel("Polikinlik Ad\u0131:");
		label_2.setForeground(new Color(220, 20, 60));
		label_2.setBackground(new Color(255, 255, 255));
		label_2.setFont(new Font("Impact", Font.PLAIN, 14));
		label_2.setBounds(274, 55, 121, 21);
		w_appointmen.add(label_2);

		JComboBox select_clinic = new JComboBox();
		select_clinic.setBackground(new Color(245, 245, 245));
		select_clinic.setFont(new Font("Impact", Font.PLAIN, 11));
		select_clinic.setForeground(new Color(220, 20, 60));
		select_clinic.setBounds(274, 76, 121, 29);
		select_clinic.addItem("-- Polikinlik Seç --");

		for (int i = 0; i < clinic.getList().size(); i++) {
			select_clinic.addItem(new Item(clinic.getList().get(i).getId(), clinic.getList().get(i).getName()));
		}

		select_clinic.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (select_clinic.getSelectedIndex() != 0) {
					JComboBox c = (JComboBox) e.getSource();
					Item item = (Item) c.getSelectedItem();
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
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
				}else {
					DefaultTableModel clearModel = (DefaultTableModel) table_doctor.getModel();
					clearModel.setRowCount(0);
				}
			}
		});
		w_appointmen.add(select_clinic);
		
		JLabel lblDoctorSel = new JLabel("Doktor Se\u00E7:");
		lblDoctorSel.setForeground(new Color(220, 20, 60));
		lblDoctorSel.setBackground(new Color(245, 245, 245));
		lblDoctorSel.setFont(new Font("Impact", Font.PLAIN, 14));
		lblDoctorSel.setBounds(274, 116, 121, 21);
		w_appointmen.add(lblDoctorSel);
		
		JButton btn_selDoctor = new JButton("Se\u00E7");
		btn_selDoctor.setBackground(new Color(245, 245, 245));
		btn_selDoctor.setFont(new Font("Impact", Font.PLAIN, 11));
		btn_selDoctor.setForeground(new Color(220, 20, 60));
		btn_selDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_doctor.getSelectedRow();
				if(row >= 0) {
					String value = table_doctor.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					 try {
						for(int  i=0; i< whour.getWhourList(id).size();i++) {
							 whourData[0]= whour.getWhourList(id).get(i).getId();
							 whourData[1]= whour.getWhourList(id).get(i).getWdate();
							 whourModel.addRow(whourData);
							 }
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 
					 table_whour.setModel(whourModel);
					 selectDoctorID = id;
					 selectDoctorName = table_doctor.getModel().getValueAt(row, 1).toString();
					 System.out.println();
				}else {
					Helper.showMsg("Lütfen bir doktor seçiniz!");
				}
			}
		});
		btn_selDoctor.setBounds(274, 141, 121, 29);
		w_appointmen.add(btn_selDoctor);
		
		JScrollPane scoroll_pane = new JScrollPane();
		scoroll_pane.setBounds(419, 54, 230, 262);
		w_appointmen.add(scoroll_pane);
		
		table_whour = new JTable(whourModel);
		table_whour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		table_whour.setBackground(new Color(245, 245, 245));
		scoroll_pane.setViewportView(table_whour);
		table_whour.getColumnModel().getColumn(0).setPreferredWidth(5); // ýd yi kucultup tarihi uzattýk modelde
		
		JLabel label_3 = new JLabel("Mesai Saatleri");
		label_3.setBackground(new Color(255, 255, 255));
		label_3.setForeground(new Color(220, 20, 60));
		label_3.setFont(new Font("Impact", Font.PLAIN, 14));
		label_3.setBounds(425, 33, 121, 21);
		w_appointmen.add(label_3);
		
		JLabel lblRandevuAl = new JLabel("Randevu:");
		lblRandevuAl.setBackground(new Color(224, 255, 255));
		lblRandevuAl.setForeground(new Color(220, 20, 60));
		lblRandevuAl.setFont(new Font("Impact", Font.PLAIN, 14));
		lblRandevuAl.setBounds(274, 206, 121, 21);
		w_appointmen.add(lblRandevuAl);
		
		JButton btn_addApointment = new JButton("Randevu Al");
		btn_addApointment.setForeground(new Color(220, 20, 60));
		btn_addApointment.setFont(new Font("Impact", Font.PLAIN, 11));
		btn_addApointment.setBackground(new Color(245, 245, 245));
		btn_addApointment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow= table_whour.getSelectedRow();
				if(selRow >=0){
				String date = table_whour.getModel().getValueAt(selRow,1).toString();
				try {
					boolean control = hasta.addApointment(selectDoctorID,hasta.getId(),selectDoctorName,hasta.getName(), date);
							if(control) {
								Helper.showMsg("success");
								hasta.updateWhourStatus(selectDoctorID, date);
								updateWhourModel(selectDoctorID);
								updateAppointModel(hasta.getId());
							}else {
								Helper.showMsg("error");
							}
				} catch (Exception e2) {
					// TODO: handle exception
				}
					
				}else {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz");
				}
			}
		});
	
		btn_addApointment.setBounds(274, 227, 121, 29);
		w_appointmen.add(btn_addApointment);
		
		JPanel w_appoint = new JPanel();
		w_appoint.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Randevularým", null, w_appoint, null);
		w_appoint.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 637, 305);
		w_appoint.add(scrollPane);
		hastaMenu = new JPopupMenu();
		  JMenuItem delMenu = new JMenuItem("Sil");
		  hastaMenu.add(delMenu);
		  delMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Helper.confirm("del")) {
					int selID = Integer.parseInt(table_appoint.getValueAt(table_appoint.getSelectedRow(), 0).toString());
					String selwdate = table_appoint.getValueAt(table_appoint.getSelectedRow(),2).toString();
					try {
						if (appoint.deleteAppoint(selID)) {
							Helper.showMsg("success");
							updateAppointModel(hasta.getId());
							appoint.update(selwdate );
						
							
							
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

		});
		 
		
		table_appoint = new JTable(appointModel);
		table_appoint.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		table_appoint.setBackground(new Color(245, 245, 245));
		 table_appoint.setComponentPopupMenu(hastaMenu);
			table_appoint.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					Point point = e.getPoint();
					int selectRow = table_appoint.rowAtPoint(point);
					table_appoint.setRowSelectionInterval(selectRow, selectRow);
				}
			});
		scrollPane.setViewportView(table_appoint);
	
	}

	public void updateWhourModel(int doctor_id) {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		try {
			for (int i = 0; i < whour.getWhourList(doctor_id).size(); i++) {
				whourData[0] = whour.getWhourList(doctor_id).get(i).getId();
				whourData[1] = whour.getWhourList(doctor_id).get(i).getWdate();
				whourModel.addRow(whourData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateAppointModel(int hasta_id) {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);
		try {
			for (int i = 0; i < appoint.getHastaList(hasta_id).size(); i++) {
				appointData[0] = appoint.getHastaList(hasta_id).get(i).getId();
				appointData[1] = appoint.getHastaList(hasta_id).get(i).getDoctorName();
				appointData[2] = appoint.getHastaList(hasta_id).get(i).getAppDate();
				appointModel.addRow(appointData);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
