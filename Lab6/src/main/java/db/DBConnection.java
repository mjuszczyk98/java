package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	private static final String URL = "jdbc:postgresql://localhost/user";
	private static final String USER = "user";
	private static final String PASSWORD = "";
	
	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
	
	public static ResultSet select(Connection connection, String query) throws SQLException {
		Statement statement = connection.createStatement();
		return statement.executeQuery(query);
	}
	
	public static void query(Connection connection, String query) throws SQLException{
		Statement statement = connection.createStatement();
		statement.execute(query);
	}

}
