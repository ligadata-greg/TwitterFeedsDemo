package com.ligadata.dao.fatafat.impl;

import java.sql.Connection;
import java.sql.DriverManager;

import com.ligadata.dao.fatafat.IGenericDAO;

public abstract class GenericDAO implements IGenericDAO{
//	public final String URL = "jdbc:mysql://localhost:3306/";
	private final static String URL = "jdbc:mysql://fatafat.ligadata.com:3306/";

	private final static String SCHEMA_NAME = "demo";
//	private final static String SCHEMA_NAME = "myschema";

	public final String USERNAME = "demo";
//	private final static String USERNAME = "rootConn";
	
	public final String PASSWORD = "demo123";
//	private final static String PASSWORD = "root";

	public static String getSCHEMA_NAME() {
		return SCHEMA_NAME;
	}
	
	public Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL + SCHEMA_NAME, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}
}
