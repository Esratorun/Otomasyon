package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.mariadb.jdbc.Connection;

public class BasHekim extends User {

	Connection con = (Connection) conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public BasHekim(int id, String tcno, String isim, String sifre, String type) {
		super(id, tcno, isim, sifre, type);

	}

	public BasHekim() {
	}

	public ArrayList<User> getDoctorList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();

		User obj;
		try {
			st = con.createStatement();

			rs = st.executeQuery("SELECT * FROM user WHERE type='doktor'");

			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getString("tcno"), rs.getString("isim"), rs.getString("sifre"),
						rs.getString("type"));
				list.add(obj);

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return list;

	}
	
	
	public ArrayList<User> getClinicDoktorList(int klinik_id) throws SQLException {
		ArrayList<User> list = new ArrayList<>();

		User obj;
		try {
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

	public boolean doktorEkle(String tcno, String sifre, String isim) throws SQLException {

		String query = "INSERT INTO user " + "(tcno,sifre,isim,type) VALUES" + "(?,?,?,?)";

		boolean key = false;

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, tcno);
			preparedStatement.setString(2, sifre); // 1,2,3,4 soru işaretlerinin numaraları
			preparedStatement.setString(3, isim);
			preparedStatement.setString(4, "doktor");
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

	public boolean doktorSil(int id) throws SQLException {

		String query = "DELETE From user WHERE id=?";

		boolean key = false;

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
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

	public boolean doktorGuncelle(int id, String tcno, String sifre, String isim) throws SQLException {

		String query = "UPDATE user SET isim=?,tcno=?,sifre=? WHERE id=?";

		boolean key = false;

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, isim);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, sifre);
			preparedStatement.setInt(4, id);
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

	public boolean workerEkle(int id, int klinik_id) throws SQLException {

		String query = "INSERT INTO worker " + "(id,klinik_id) VALUES" + "(?,?)";

		boolean key = false;
		int count = 0;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM worker WHERE klinik_id=" + klinik_id + " AND id="+id);
			while (rs.next()) {
				count++;

			}
			if (count == 0) {

				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, id);
				preparedStatement.setInt(2, klinik_id);
				preparedStatement.executeUpdate();
			}

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
