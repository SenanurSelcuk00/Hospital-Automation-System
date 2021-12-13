package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;
import Model.Doctor;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class DoctorGUI extends JFrame {
	static Doctor doctor = new Doctor();
	private JPanel w_pane;

	private DefaultTableModel whourModel;
	private Object[] whourData = null;
	private JTable table_whour;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DoctorGUI frame = new DoctorGUI(doctor);
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
	public DoctorGUI(Doctor doctor) {
		setResizable(false);

		whourModel = new DefaultTableModel();
		Object[] colWhour = new Object[2];
		colWhour[0] = "ID";
		colWhour[1] = "Tarih";
		whourModel.setColumnIdentifiers(colWhour);
		whourData = new Object[2];
		try {
			for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
				whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
				whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
				whourModel.addRow(whourData);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		setTitle("Hastane Y\u00F6netim Sistemi Doktor Giri\u015Fi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel button = new JLabel("Ho\u015Fgeldiniz, Say\u0131n " + doctor.getName());
		button.setForeground(new Color(220, 20, 60));
		button.setBackground(new Color(224, 255, 255));
		button.setFont(new Font("Impact", Font.PLAIN, 16));
		button.setBounds(10, 11, 422, 27);
		w_pane.add(button);

		JButton btnNewButton = new JButton("\u00C7\u0131k\u0131\u015F Yap");
		btnNewButton.setForeground(new Color(220, 20, 60));
		btnNewButton.setBackground(new Color(245, 245, 245));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login =new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Impact", Font.PLAIN, 16));
		btnNewButton.setBounds(580, 16, 132, 33);
		w_pane.add(btnNewButton);

		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBackground(new Color(220, 220, 220));
		w_tab.setForeground(new Color(220, 20, 60));
		w_tab.setFont(new Font("Impact", Font.PLAIN, 11));
		w_tab.setBounds(29, 82, 662, 355);
		w_pane.add(w_tab);

		JPanel w_whour = new JPanel();
		w_whour.setBackground(new Color(255, 255, 255));
		w_tab.addTab("Çalýþma Saatleri", null, w_whour, null);
		w_whour.setLayout(null);

		JDateChooser select_date = new JDateChooser();
		select_date.setBounds(25, 11, 130, 34);
		w_whour.add(select_date);

		JComboBox select_time = new JComboBox();
		select_time.setForeground(new Color(220, 20, 60));
		select_time.setBackground(new Color(245, 245, 245));
		select_time.setFont(new Font("Impact", Font.PLAIN, 13));
		select_time.setModel(new DefaultComboBoxModel(new String[] { "9.00", "9.30", "10.00", "10.30", "11.00", "11.30",
				"13.00", "13.30", "14.00", "14.30", "15.00", "15.30" }));
		select_time.setBounds(165, 11, 90, 34);
		w_whour.add(select_time);

		JButton btn_addWhour = new JButton("EKLE");
		btn_addWhour.setBackground(new Color(245, 245, 245));
		btn_addWhour.setForeground(new Color(220, 20, 60));
		btn_addWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(select_date.getDate());
				} catch (Exception e2) {
					// TODO: handle exception
				}
				if (date.length() == 0) {
					Helper.showMsg("Lütfen geçerli bir tarih giriniz!");
				} else {
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time;
					try {
						boolean control = doctor.addWhour(doctor.getId(), doctor.getName(), selectDate);
						if (control) {
							Helper.showMsg("succes");
							updateWhourModel(doctor);
						} else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btn_addWhour.setFont(new Font("Impact", Font.PLAIN, 16));
		btn_addWhour.setBounds(285, 11, 107, 34);
		w_whour.add(btn_addWhour);

		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(10, 56, 637, 260);
		w_whour.add(w_scrollWhour);

		table_whour = new JTable(whourModel);
		table_whour.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 12));
		table_whour.setBackground(new Color(245, 245, 245));
		w_scrollWhour.setViewportView(table_whour);
		
		JButton btn_deleteWhour = new JButton("S\u0130L");
		btn_deleteWhour.setForeground(new Color(220, 20, 60));
		btn_deleteWhour.setBackground(new Color(245, 245, 245));
		btn_deleteWhour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow >= 0) {
					String selectRow = table_whour.getModel().getValueAt(selRow, 0).toString();
					int selID = Integer.parseInt(selectRow);
					boolean control;
					try {
						control = doctor.deleteWhour(selID);
						if(control) {
							Helper.showMsg("succes");
							updateWhourModel(doctor);
						}else {
							Helper.showMsg("error");
						}
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					}else {
						Helper.showMsg("Lütfen bir tarih seçiniz!");
					}

		}
		});
		btn_deleteWhour.setFont(new Font("Impact", Font.PLAIN, 16));
		btn_deleteWhour.setBounds(463, 11, 107, 34);
		w_whour.add(btn_deleteWhour);
	}

	public void updateWhourModel(Doctor doctor) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < doctor.getWhourList(doctor.getId()).size(); i++) {
			whourData[0] = doctor.getWhourList(doctor.getId()).get(i).getId();
			whourData[1] = doctor.getWhourList(doctor.getId()).get(i).getWdate();
			whourModel.addRow(whourData);
		}

	}

/*	private static class __Tmp {
		private static void __tmp() {
			javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}*/
}
