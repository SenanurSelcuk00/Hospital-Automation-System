package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Clinic;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UpdateClinicGUI extends JFrame {

	private JPanel contentPane;
	private JTextField fld_clinicName;
	private static Clinic clinic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateClinicGUI frame = new UpdateClinicGUI(clinic);
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
	public UpdateClinicGUI(Clinic clinic) {
		setTitle("Polikinlik G\u00FCncelle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 225, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPolikinlikEkle = new JLabel("Polikinlik Ekle:");
		lblPolikinlikEkle.setForeground(new Color(220, 20, 60));
		lblPolikinlikEkle.setFont(new Font("Impact", Font.PLAIN, 14));
		lblPolikinlikEkle.setBounds(28, 11, 153, 20);
		contentPane.add(lblPolikinlikEkle);
		
		fld_clinicName = new JTextField();
		fld_clinicName.setBackground(new Color(245, 245, 245));
		fld_clinicName.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 12));
		fld_clinicName.setColumns(10);
		fld_clinicName.setBounds(28, 31, 173, 25);
		fld_clinicName.setText(clinic.getName());
		contentPane.add(fld_clinicName);
		
		JButton btn_updateClinic = new JButton("D\u00FCzenle");
		btn_updateClinic.setForeground(new Color(220, 20, 60));
		btn_updateClinic.setBackground(new Color(245, 245, 245));
		btn_updateClinic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Helper.confirm("sure")) {
					try {
						clinic.updateClinic(clinic.getId(), fld_clinicName.getText());
						Helper.showMsg("success");
						dispose(); 
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btn_updateClinic.setFont(new Font("Impact", Font.PLAIN, 12));
		btn_updateClinic.setBounds(28, 67, 173, 28);
		contentPane.add(btn_updateClinic);
	}

	

	
}
