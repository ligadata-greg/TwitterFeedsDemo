package com.ligadata.fatafat;

import java.sql.Connection;
import java.sql.DriverManager;

public class GenericDAO {
	public final String URL = "jdbc:mysql://localhost:3306/demo";
	/**
	 * In my case username is "root" *
	 */
	public final String USERNAME = "demo";
	/**
	 * In my case password is "1234" *
	 */
	public final String PASSWORD = "demo123";

	public Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}
}
