package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Helper.Helper;

public class Hasta extends User{
	Connection con = conn.connDb();
	public Hasta() {
	
	}
	public Hasta(int id, String tcno, String name, String password, String type) {
		super(id, tcno, name, password, type);
		// TODO Auto-generated constructor stub
	}
	PreparedStatement preparedStatement = null;
	Statement st= null;
	ResultSet rs= null;
	
	public boolean register(String tcno, String password,String name) throws SQLException {
		int key = 0;
		boolean duplicate = false;
		String query = "INSERT INTO user " + "(tcno,password,name,type) VALUES" + "(?,?,?,?)";

		try {
			st = con.createStatement();

			rs = st.executeQuery("SELECT * FROM user WHERE tcno = '" + tcno+ "'");
				
			
			while (rs.next()) {
			duplicate = true;
			Helper.showMsg("Bu TC numarasına ait bir kayıt bulunmaktadır.");
				break;
			}
			if (!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1,tcno);
				preparedStatement.setString(2, password);
				preparedStatement.setString(3,name);
				preparedStatement.setString(4,"hasta");
				

				preparedStatement.executeUpdate();
				key=1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(key ==1)
			return true;
		else
		return false;

	}
	
	public boolean addApointment(int doctor_id , int hasta_id, String doctor_name, String hasta_name, String appDate) throws SQLException {
		String query = "INSERT INTO apointment "+ "(doctor_id , doctor_name,hasta_id, hasta_name,app_date) VALUES"+"(?,?,?,?,?)";
		int key = 0;
				try {
					
					preparedStatement = con.prepareStatement(query);
					preparedStatement.setInt(1,doctor_id);
					preparedStatement.setString(2, doctor_name);
					preparedStatement.setInt(3,hasta_id);
					preparedStatement.setString(4,hasta_name);
					preparedStatement.setString(5,appDate);
					preparedStatement.executeUpdate();
					key = 1;
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				if(key==1)
				   return true;
				else
					return false;
	
				
	}
	
	public boolean updateWhourStatus(int doctor_id , String wdate) throws SQLException {
		String query = "UPDATE whour SET status = ? WHERE doctor_id =? AND wdate =? ";
		int key = 0;
				try {
					
					preparedStatement = con.prepareStatement(query);
					preparedStatement.setString(1, "p");
					preparedStatement.setInt(2,doctor_id);
					preparedStatement.setString(3, wdate);
					preparedStatement.executeUpdate();
					key = 1;
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				if(key==1)
				   return true;
				else
					return false;
	
				
	}
	
	
}
