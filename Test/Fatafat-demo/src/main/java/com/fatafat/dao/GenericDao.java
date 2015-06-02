package com.fatafat.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import com.fatafat.idao.IGenericDao;

public abstract class GenericDao implements IGenericDao{
	private final String URL = "jdbc:mysql://localhost:3306/";
	private final static String SCHEMA_NAME = "testdb";
	private final String USERNAME = "demo";
	private final String PASSWORD = "demo123";

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
