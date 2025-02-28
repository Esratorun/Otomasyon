package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.mariadb.jdbc.Connection;

import Helper.DBCon;

public class Appointment {
private int id,doktor_id,hasta_id;
private String doktor_isim , hasta_isim,app_date;

DBCon conn=new DBCon();

Statement st =null;
ResultSet rs=null;
PreparedStatement preparedStatement=null;


public Appointment() {}

public ArrayList<Appointment> getHastaList(int hasta_id) throws SQLException {
	ArrayList<Appointment> list = new ArrayList<>();

	Appointment obj;
	try {
		Connection con =(Connection) conn.connDb();
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM appointment WHERE hasta_id = "+ hasta_id);

		while (rs.next()) {
			obj = new Appointment();
			obj.setId(rs.getInt("id"));
			obj.setDoktor_id(rs.getInt("doktor_id"));
			obj.setDoktor_isim(rs.getString("doktor_isim"));
			obj.setHasta_id(rs.getInt("hasta_id"));
			obj.setHasta_isim(rs.getString("hasta_isim"));
			obj.setApp_date(rs.getString("app_date"));
			list.add(obj);

		}
	} catch (SQLException e) {
		e.printStackTrace();

	}
	return list;

}

public ArrayList<Appointment> getDoktorList(int doktor_id) throws SQLException {
	ArrayList<Appointment> list = new ArrayList<>();

	Appointment obj;
	try {
		Connection con =(Connection) conn.connDb();
		st = con.createStatement();
		rs = st.executeQuery("SELECT * FROM appointment WHERE doktor_id = "+ doktor_id);

		while (rs.next()) {
			obj = new Appointment();
			obj.setId(rs.getInt("id"));
			obj.setDoktor_id(rs.getInt("doktor_id"));
			obj.setDoktor_isim(rs.getString("doktor_isim"));
			obj.setHasta_id(rs.getInt("hasta_id"));
			obj.setHasta_isim(rs.getString("hasta_isim"));
			obj.setApp_date(rs.getString("app_date"));
			list.add(obj);

		}
	} catch (SQLException e) {
		e.printStackTrace();

	}
	return list;

}

public Appointment(int id, int doktor_id, int hasta_id, String doktor_isim, String hasta_isim, String app_date) {
	super();
	this.id = id;
	this.doktor_id = doktor_id;
	this.hasta_id = hasta_id;
	this.doktor_isim = doktor_isim;
	this.hasta_isim = hasta_isim;
	this.app_date = app_date;
}




public int getId() {
	return id;
}




public void setId(int id) {
	this.id = id;
}




public int getDoktor_id() {
	return doktor_id;
}




public void setDoktor_id(int doktor_id) {
	this.doktor_id = doktor_id;
}




public int getHasta_id() {
	return hasta_id;
}




public void setHasta_id(int hasta_id) {
	this.hasta_id = hasta_id;
}




public String getDoktor_isim() {
	return doktor_isim;
}




public void setDoktor_isim(String doktor_isim) {
	this.doktor_isim = doktor_isim;
}




public String getHasta_isim() {
	return hasta_isim;
}




public void setHasta_isim(String hasta_isim) {
	this.hasta_isim = hasta_isim;
}




public String getApp_date() {
	return app_date;
}




public void setApp_date(String app_date) {
	this.app_date = app_date;
}







}
