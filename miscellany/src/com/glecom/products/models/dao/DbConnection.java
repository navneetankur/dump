package com.glecom.products.models.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author navneet
 *Create a connection with database.
 */
public class DbConnection {
	private Connection connection;
	private Statement statement;
	public DbConnection(String user, String pass, String dbUrl) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl, user, pass);
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public DbConnection() {
		this("root","root","jdbc:mysql://localhost:3306/global_logic_project");
	}
	/**
	 * @param query
	 * @return
	 * @throws SQLException
	 * executes select query
	 */
	public ResultSet executeQuery(String query) throws SQLException {
		return statement.executeQuery(query);
	}

	/**
	 * @param query
	 * @throws SQLException
	 * executes altering query
	 */
	public void execute(String query) throws SQLException {
			statement.execute(query);
	}

	/**
	 * @throws SQLException
	 * closes connection
	 */
	public void close() throws SQLException {
			statement.close();
			connection.close();
	}
}
