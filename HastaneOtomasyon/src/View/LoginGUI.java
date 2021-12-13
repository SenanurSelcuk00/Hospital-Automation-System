package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;
import javax.swing.UIManager;

public class LoginGUI extends JFrame {

	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private DBConnection conn = new DBConnection();
	private JPasswordField fld_hastaPass;

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
		setTitle("Hastane Otomasyonu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("saglýk.png")));
		lbl_logo.setBounds(237, 10, 200, 200);
		w_pane.add(lbl_logo);

		JLabel lblNewLabel = new JLabel("Hastane Y\u00F6netim Sistemine Hosgeldiniz");
		lblNewLabel.setForeground(new Color(220, 20, 60));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Cooper Black", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(101, 215, 502, 32);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setForeground(new Color(220, 20, 60));
		w_tabpane.setBackground(new Color(220, 220, 220));
		w_tabpane.setBounds(10, 258, 676, 285);
		w_pane.add(w_tabpane);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Hasta Giriþi", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);

		JLabel lblTcNumaranz = new JLabel("T.C. Numaran\u0131z:");
		lblTcNumaranz.setForeground(new Color(220, 20, 60));
		lblTcNumaranz.setFont(new Font("Impact", Font.ITALIC, 22));
		lblTcNumaranz.setBounds(51, 35, 174, 40);
		w_hastaLogin.add(lblTcNumaranz);

		JLabel lblifre = new JLabel("\u015Eifre:");
		lblifre.setForeground(new Color(220, 20, 60));
		lblifre.setFont(new Font("Impact", Font.ITALIC, 22));
		lblifre.setBounds(51, 100, 174, 40);
		w_hastaLogin.add(lblifre);

		fld_hastaTc = new JTextField();
		fld_hastaTc.setForeground(new Color(0, 0, 0));
		fld_hastaTc.setBackground(new Color(245, 245, 245));
		fld_hastaTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		fld_hastaTc.setBounds(235, 37, 338, 40);
		w_hastaLogin.add(fld_hastaTc);
		fld_hastaTc.setColumns(10);

		JButton btn_register = new JButton("Kay\u0131t Ol");
		btn_register.setBackground(new Color(245, 245, 245));
		btn_register.setForeground(new Color(220, 20, 60));
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI register = new RegisterGUI();
				register.setVisible(true);
				dispose();
			}
		});
		btn_register.setFont(new Font("Impact", Font.ITALIC, 18));
		btn_register.setBounds(56, 203, 238, 45);
		w_hastaLogin.add(btn_register);

		JButton btn_hastaLogin = new JButton("Giri\u015F Yap");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_hastaTc.getText().length()==0|| fld_hastaPass.getText().length()==0) {
					Helper.showMsg("fill");
					
				}else {
					boolean key = true;
				
					
				try {
					Connection con = conn.connDb();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM user");
				
					while (rs.next()) {
						if (fld_hastaTc.getText().equals(rs.getString("tcno"))
								&& fld_hastaPass.getText().equals(rs.getString("password"))) {
							if (rs.getString("type").equals("hasta")) {
								Hasta hasta =new Hasta();
								hasta.setId(rs.getInt("id"));
								hasta.setPassword("password");
								hasta.setTcno(rs.getString("tcno"));
								hasta.setName(rs.getString("name"));
								hasta.setType(rs.getString("type"));
                                  HastaGUI hGUI= new HastaGUI(hasta);
								hGUI.setVisible(true);
								dispose();
								key = false;
							}
						}
						}
					
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				if(key) {
					Helper.showMsg("Böyle bir hasta bulunamadý. Lütfen kayýt olunuz");
				}
			}
			}
			
		});
		btn_hastaLogin.setForeground(new Color(220, 20, 60));
		btn_hastaLogin.setFont(new Font("Impact", Font.ITALIC, 18));
		btn_hastaLogin.setBackground(new Color(245, 245, 245));
		btn_hastaLogin.setBounds(352, 203, 238, 45);
		w_hastaLogin.add(btn_hastaLogin);
		
		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setForeground(new Color(0, 0, 0));
		fld_hastaPass.setBackground(new Color(245, 245, 245));
		fld_hastaPass.setBounds(235, 100, 338, 40);
		w_hastaLogin.add(fld_hastaPass);

		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Doktor Giriþi", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);

		JLabel lblTcNumaranz_1 = new JLabel("T.C. Numaran\u0131z:");
		lblTcNumaranz_1.setForeground(new Color(220, 20, 60));
		lblTcNumaranz_1.setFont(new Font("Impact", Font.ITALIC, 22));
		lblTcNumaranz_1.setBounds(56, 11, 174, 40);
		w_doctorLogin.add(lblTcNumaranz_1);

		fld_doctorTc = new JTextField();
		fld_doctorTc.setBackground(new Color(245, 245, 245));
		fld_doctorTc.setForeground(new Color(0, 0, 0));
		fld_doctorTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 17));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(240, 13, 338, 40);
		w_doctorLogin.add(fld_doctorTc);

		JLabel lblifre_1 = new JLabel(" \u015Eifre:");
		lblifre_1.setForeground(new Color(220, 20, 60));
		lblifre_1.setFont(new Font("Impact", Font.ITALIC, 22));
		lblifre_1.setBounds(43, 81, 174, 40);
		w_doctorLogin.add(lblifre_1);

		JButton btn_doktorLogin = new JButton("Giri\u015F Yap");
		btn_doktorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (fld_doctorTc.getText().equals(rs.getString("tcno"))
									&& fld_doctorPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("bashekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword("password");
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}
								else if (rs.getString("type").equals("doktor")) {

									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword("password");
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();

								}
							}
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}
			}
		});
		btn_doktorLogin.setForeground(new Color(220, 20, 60));
		btn_doktorLogin.setFont(new Font("Impact", Font.ITALIC, 18));
		btn_doktorLogin.setBackground(new Color(245, 245, 245));
		btn_doktorLogin.setBounds(118, 167, 411, 45);
		w_doctorLogin.add(btn_doktorLogin);

		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setBackground(new Color(245, 245, 245));
		fld_doctorPass.setForeground(new Color(0, 0, 0));
		fld_doctorPass.setBounds(240, 81, 338, 40);
		w_doctorLogin.add(fld_doctorPass);
	}
}