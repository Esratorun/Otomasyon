package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.mariadb.jdbc.Connection;

public class Doktor extends User {
	Connection con = (Connection) conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Doktor() {
		super();

	}

	public Doktor(int id, String tcno, String isim, String sifre, String type) {
		super(id, tcno, isim, sifre, type);

	}

	public boolean whourEkle(int id, String isim, String wdate) throws SQLException {

		int key = 0;
		int count = 0;
		String query = "INSERT INTO whour" + "(id,isim,wdate) VALUES" + "(?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status = 'a' AND id = " + id + " AND wdate = '" + wdate + "'");

			while (rs.next()) {

				count++;
				break;

			}

			if (count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, isim);
				preparedStatement.setString(3, wdate);
				preparedStatement.executeUpdate();
				
			}
			
			key = 1;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (key == 1) {
			return true;
		} else {
			return false;
		}

	}

	

	public ArrayList<Whour> getWhourList(int id) throws SQLException {
		ArrayList<Whour> list = new ArrayList<>();

		Whour obj;
		try {
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
	
	public boolean whourSil(int whour_id) throws SQLException {

		String query = "DELETE From Whour WHERE whour_id=?";

		boolean key = false;

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1,whour_id);
			preparedStatement.executeUpdate();

			key = true;

		} catch (Exception e) {
			e.printStackTrace();

		}

		if (key) {
			return true;
		} else {
			return false;
		}

	}
	
}
