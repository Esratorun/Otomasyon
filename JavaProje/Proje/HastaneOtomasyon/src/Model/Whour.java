package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.mariadb.jdbc.Connection;

import Helper.DBCon;

public class Whour {
	private int whour_id;
	private int id;
	private String isim,wdate,status;
	
	
	
	
	DBCon conn=new DBCon();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	public Whour() {}
	
	public Whour(int whour_id, int id, String isim, String wdate, String status) {
		super();
		this.whour_id = whour_id;
		this.id = id;
		this.isim = isim;
		this.wdate = wdate;
		this.status = status;
	}
	
	
	public int getWhour_id() {
		return whour_id;
	}
	public void setWhour_id(int whour_id) {
		this.whour_id = whour_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsim() {
		return isim;
	}
	public void setIsim(String isim) {
		this.isim = isim;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	


	public ArrayList<Whour> getWhourList(int id) throws SQLException {
		ArrayList<Whour> list = new ArrayList<>();

		Whour obj;
		try {
			Connection con = (Connection) conn.connDb();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status='a' AND id=" + id);

			while (rs.next()) {
				obj = new Whour();
				obj.setWhour_id(rs.getInt("whour_id"));
				obj.setId(rs.getInt("id"));
				obj.setIsim(rs.getString("isim"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return list;

	}
	
	

}
