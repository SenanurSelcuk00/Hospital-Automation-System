package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.Helper;
import Model.Hasta;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class RegisterGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_name;
	private JTextField fld_tcno;
	private JPasswordField fld_pass;
	private Hasta hasta = new Hasta();

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
		setTitle("Kay\u0131t Sistemi");
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 400 );
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel label = new JLabel("Ad Soyad :");
		label.setForeground(new Color(220, 20, 60));
		label.setBackground(new Color(240, 240, 240));
		label.setFont(new Font("Impact", Font.PLAIN, 14));
		label.setBounds(23, 11, 153, 20);
		w_pane.add(label);
		
		fld_name = new JTextField();
		fld_name.setBackground(new Color(245, 245, 245));
		fld_name.setColumns(10);
		fld_name.setBounds(23, 31, 168, 20);
		w_pane.add(fld_name);
		
		JLabel lblTcnumarasý = new JLabel("T.C. No:");
		lblTcnumarasý.setForeground(new Color(220, 20, 60));
		lblTcnumarasý.setFont(new Font("Impact", Font.PLAIN, 14));
		lblTcnumarasý.setBounds(23, 62, 153, 20);
		w_pane.add(lblTcnumarasý);
		
		fld_tcno = new JTextField();
		fld_tcno.setBackground(new Color(245, 245, 245));
		fld_tcno.setColumns(10);
		fld_tcno.setBounds(23, 85, 168, 20);
		w_pane.add(fld_tcno);
		
		JLabel lblSifre = new JLabel("\u015Eifre:");
		lblSifre.setForeground(new Color(220, 20, 60));
		lblSifre.setFont(new Font("Impact", Font.PLAIN, 14));
		lblSifre.setBounds(23, 119, 153, 20);
		w_pane.add(lblSifre);
		
		fld_pass = new JPasswordField();
		fld_pass.setBackground(new Color(245, 245, 245));
		fld_pass.setBounds(23, 139, 168, 20);
		w_pane.add(fld_pass);
		
		JButton btn_register = new JButton("Kay\u0131t Ol");
		btn_register.setBackground(new Color(245, 245, 245));
		btn_register.setForeground(new Color(220, 20, 60));
		btn_register.setFont(new Font("Impact", Font.PLAIN, 11));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tcno.getText().length()==0 || fld_pass.getText().length()==0||fld_name.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						boolean control = hasta.register(fld_tcno.getText(), fld_pass.getText(),fld_name.getText());
						
						if(control) {
							Helper.showMsg("success");
							LoginGUI login= new LoginGUI();
							login.setVisible(true);
							dispose();
				
							
						}else {
							Helper.showMsg("error");
						}
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
					
				}
				
			}
		});
		btn_register.setBounds(67, 203, 143, 28);
		w_pane.add(btn_register);
		
		JButton btn_backto = new JButton("Geri D\u00F6n");
		btn_backto.setBackground(new Color(245, 245, 245));
		btn_backto.setForeground(new Color(220, 20, 60));
		btn_backto.setFont(new Font("Impact", Font.PLAIN, 11));
		btn_backto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login= new LoginGUI();
				login.setVisible(true);
				dispose();
			}
		});
		btn_backto.setBounds(67, 254, 143, 28);
		w_pane.add(btn_backto);
	}
}
