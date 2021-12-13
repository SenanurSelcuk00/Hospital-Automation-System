package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Apointment {
	
	public Apointment() {

	 }
	private int id, doctorID, hastaID;
	private String doctorName, hastaName, appData;
	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	PreparedStatement preparedStatement = null;
	Statement st = null;
	ResultSet rs = null;

 
 public ArrayList<Apointment> getDoctorList(int doctor_id) throws SQLException{
		ArrayList<Apointment> list = new ArrayList<>();
		Apointment obj;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM apointment WHERE doctor_id ="+ doctor_id);
			while(rs.next()) {
				obj = new Apointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
				
	            list.add(obj);
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally{
			st.close();
			rs.close();
			con.close();
		}
		return list;
	}
 public boolean update( String wdate) throws SQLException {
		String query = "UPDATE whour SET status=? WHERE wdate =?";
		boolean key = false;
		Connection con = conn.connDb();
				try {
					st = con.createStatement();
					preparedStatement = con.prepareStatement(query);
					preparedStatement.setString(1,"a" );	
					preparedStatement.setString(2,wdate);
					preparedStatement.executeUpdate();
					key = true;
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				if(key)
				   return true;
				else
					return false;
	
				
	}

 
 public ArrayList<Apointment> getHastaList(int hasta_id) throws SQLException{
		ArrayList<Apointment> list = new ArrayList<>();
		Apointment obj;
		Connection con = conn.connDb();
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM apointment WHERE hasta_id ="+ hasta_id);
			while(rs.next()) {
				obj = new Apointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorID(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaID(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
				
	            list.add(obj);
			}
			
		}catch(SQLException e1) {
			e1.printStackTrace();
		}finally{
			st.close();
			rs.close();
			con.close();
		}
		return list;
	}
 public boolean deleteAppoint(int id) throws SQLException {
		String query = "DELETE FROM apointment WHERE id =?";
		boolean key = false;
		Connection con = conn.connDb();
				try {
					st = con.createStatement();
					preparedStatement = con.prepareStatement(query);
					preparedStatement.setInt(1,id);
					preparedStatement.executeUpdate();
					key = true;
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				if(key)
				   return true;
				else
					return false;
			
	}
 public boolean deleteAppointt(int id) throws SQLException {
		Connection con = conn.connDb();
		String query = "DELETE FROM apointment WHERE doctor_id =?";
		boolean key = false;
				try {
					st = con.createStatement();
					preparedStatement = con.prepareStatement(query);
					preparedStatement.setInt(1,id);
				
					preparedStatement.executeUpdate();
					key = true;
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				if(key)
				   return true;
				else
					return false;

				
	}
 

public Apointment(int id, int doctorID, int hastaID, String doctorName, String hastaName, String appData) {
	super();
	this.id = id;
	this.doctorID = doctorID;
	this.hastaID = hastaID;
	this.doctorName = doctorName;
	this.hastaName = hastaName;
	this.appData = appData;
}


private void setAppDate(String string) {
	// TODO Auto-generated method stub
	this.appData= string;
	
}

private void setHastaName(String string) {
	// TODO Auto-generated method stub
	this.hastaName= string;
}

private void setHastaID(int int1) {
	// TODO Auto-generated method stub
	this.hastaID= int1;
	
}

private void setDoctorName(String string) {
this.doctorName = string;
	
}

private void setDoctorID(int int1) {
	this.doctorID = int1;	
}

private void setId(int int1) {
	// TODO Auto-generated method stub
	this.id = int1;
	
}
public Object getId() {
	// TODO Auto-generated method stub
	return id;
}
public String getAppData() {
	return appData;
}
public void setAppData(String appData) {
	this.appData = appData;
}
public int getDoctorID() {
	return doctorID;
}
public int getHastaID() {
	return hastaID;
}
public String getDoctorName() {
	return doctorName;
}
public String getHastaName() {
	return hastaName;
}
public Object getAppDate() {
	// TODO Auto-generated method stub
	return appData;
}
}