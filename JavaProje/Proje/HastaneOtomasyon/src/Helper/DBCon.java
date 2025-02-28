package Helper;

import java.sql.*;

public class DBCon {
	
	
		Connection c=null;
		
		public DBCon(){}
		
		public Connection connDb() {
			
			try {
				this.c=DriverManager.getConnection("jdbc:mariadb://localhost:3306/hastaneotomasyonudb?user=root&password=Esra_sql24");
				return c;
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return c;		
		
		
		
		
	}	

	
	
}
