package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;


import Helper.DBCon;

public class Klinik {
	DBCon conn=new DBCon();
	
    Statement st =null;
	ResultSet rs=null;
	PreparedStatement preparedStatement=null;
	
	private int klinik_id;
	private String klinik_isim;
	
	public Klinik() {};
	
	
	
	public Klinik(int klinik_id, String klinik_isim) {
		super();
		this.klinik_id = klinik_id;
		this.klinik_isim = klinik_isim;
	}


	public ArrayList<Klinik> getList() throws SQLException{
		ArrayList<Klinik> list =new ArrayList<>();
	
		
		
		Klinik obj;
	    Connection con =(Connection) conn.connDb();
		try {
			
			st=con.createStatement();
			rs=st.executeQuery("SELECT* FROM klinik");
			while(rs.next()) {
				obj= new Klinik(rs.getInt("klinik_id"),rs.getString("klinik_isim"))	;
				list.add(obj);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;

	}
	
	public boolean klinikEkle(String klinik_isim) throws SQLException{
		
		String query="INSERT INTO klinik "+"(klinik_isim) VALUES"+"(?)";
		
		boolean key =false;
		Connection con =(Connection) conn.connDb();
		
		try{
			st=con.createStatement();
		preparedStatement=con.prepareStatement(query);
		preparedStatement.setString(1, klinik_isim ); 
		

		preparedStatement.executeUpdate();
		key=true;
		
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if (key) {
		return true;
		}else {
			return false;
		}
		
	}


	public boolean klinikSil(int klinik_id) throws SQLException{
		Connection con =(Connection) conn.connDb();
		String query="DELETE FROM klinik WHERE klinik_id=?";
	
		boolean key =false;
			
		try{st=con.createStatement();
		preparedStatement=con.prepareStatement(query);
		preparedStatement.setInt(1, klinik_id);
		preparedStatement.executeUpdate();
		
		key=true;
		
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if (key) {
		return true;
		}else {
			return false;
		}
		
		
		
	}
	
public boolean klinikGuncelle(int klinik_id,String klinik_isim) throws SQLException{
		Connection con =(Connection) conn.connDb();
		String query="UPDATE klinik SET klinik_isim=? WHERE klinik_id=?";
		
		boolean key =false;
		
		try{st=con.createStatement();
		preparedStatement=con.prepareStatement(query);
		preparedStatement.setString(1, klinik_isim);
		preparedStatement.setInt(2, klinik_id);

		preparedStatement.executeUpdate();
		
		key=true;
		
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		if (key) {
		return true;
		}else {
			return false;
		}
		
	}


public Klinik getFetch(int klinik_id){

	Connection con =(Connection) conn.connDb();

	Klinik c=new Klinik();
	
	try {
		
		st=con.createStatement();
		rs=st.executeQuery("SELECT * FROM klinik WHERE klinik_id="+klinik_id);
		while(rs.next()) {
			c.setKlinik_id(rs.getInt("klinik_id"));
			c.setKlinik_isim(rs.getString("klinik_isim"));
			break;
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return c;
}







public int getKlinik_id() {
	return klinik_id;
}



public void setKlinik_id(int klinik_id) {
	this.klinik_id = klinik_id;
}



public String getKlinik_isim() {
	return klinik_isim;
}



public void setKlinik_isim(String klinik_isim) {
	this.klinik_isim = klinik_isim;
}

public ArrayList<User> getClinicDoktorList(int klinik_id) throws SQLException {
	ArrayList<User> list = new ArrayList<>();

	User obj;
	try {
		Connection con =(Connection) conn.connDb();
		st = con.createStatement();
		rs = st.executeQuery("SELECT u.id,u.tcno,u.isim,u.sifre, u.type FROM worker w LEFT JOIN user u ON  w.id=u.id WHERE klinik_id="+klinik_id );

		while (rs.next()) {
			obj = new User(rs.getInt("u.id"), rs.getString("u.tcno"), rs.getString("u.isim"), rs.getString("u.sifre"),
					rs.getString("u.type"));
			list.add(obj);

		}
	} catch (SQLException e) {
		e.printStackTrace();

	}
	return list;

}


}
