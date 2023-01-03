package EventDatabaseSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MiddleTier {
	
	public static Connection getConnection() {
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/schema","root","0000");
		} catch (SQLException e) {
			System.out.println("Failed to connect to database");
			e.printStackTrace();
		}
		return c;
	}
}
