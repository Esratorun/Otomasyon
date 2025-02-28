package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mariadb.jdbc.Connection;

import Helper.Helper;

public class Hasta extends User {

	Statement st = null;
	ResultSet rs = null;
	Connection con = (Connection) conn.connDb();
	PreparedStatement preparedStatement = null;

	public Hasta() {
	}

	public Hasta(int id, String tcno, String isim, String sifre, String type) {
		super(id, tcno, isim, sifre, type);

	}

	public boolean register(String tcno,String sifre,String isim) throws SQLException {

		int key = 0;
		boolean duplicate=false;
		String query = "INSERT INTO user (tcno, sifre, isim, type) VALUES" + "(?, ?, ?, ?)";
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE tcno='" + tcno + "'");

			while (rs.next()) {

				duplicate=true;
				Helper.showMsg("Bu TC Kimlik Numarası zaten kayıtlı !");
				
				break;

			}

			if (!duplicate) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, tcno);
				preparedStatement.setString(2, sifre);
				preparedStatement.setString(3, isim);
				preparedStatement.setString(4, "hasta");
				preparedStatement.executeUpdate();
			key = 1;
			}
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		if (key == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addAppointment(int doktor_id, int hasta_id, String doktor_isim, String hasta_isim, String app_date)
			throws SQLException {

		int key = 0;

		String query = "INSERT INTO appointment (doktor_id, doktor_isim, hasta_id, hasta_isim, app_date) VALUES" + "(?, ?,?, ?, ?)";
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, doktor_id);
			preparedStatement.setString(2, doktor_isim);
			preparedStatement.setInt(3, hasta_id);
			preparedStatement.setString(4, hasta_isim);
			preparedStatement.setString(5, app_date);
			preparedStatement.executeUpdate();
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

	public boolean updateWhourStatus(int doktor_id, String wdate)
			throws SQLException {

		int key = 0;

		String query = "UPDATE whour SET status =? WHERE id=? AND wdate=?";
		try {
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, "p");
			preparedStatement.setInt(2, doktor_id);
			preparedStatement.setString(3, wdate);
			preparedStatement.executeUpdate();
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

	
}
