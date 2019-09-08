import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaWrite {
	public static void main(String args[]) {
		Connection connection = null;
		try {

			// Load the MySQL JDBC driver

			String driverName = "com.mysql.jdbc.Driver";

			Class.forName(driverName);

			// Create a connection to the database

			String serverName = "localhost";

			String schema = "mydb";

			String url = "jdbc:mysql://" + serverName + ":3306/" + schema;

			String username = "root";

			String password = "admin";

			connection = DriverManager.getConnection(url, username, password);

			System.out.println("Successfully Connected to the database!");

		} catch (ClassNotFoundException e) {

			System.out.println("Could not find the database driver " + e.getMessage());

		} catch (SQLException e) {

			System.out.println("Could not connect to the database " + e.getMessage());
		}
	}
}
