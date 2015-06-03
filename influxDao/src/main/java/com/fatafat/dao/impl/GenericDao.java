package com.fatafat.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

import com.fatafat.idao.IGenericDao;

public abstract class GenericDao implements IGenericDao{

	private final String URL = "http://fatafat-dev.ligadata.com:8086";
	private final static String DATABASE_NAME = "demo";
	private final String USERNAME = "root";
	private final String PASSWORD = "root";
	
	public InfluxDB getConnection() {
		InfluxDB influxDB = InfluxDBFactory.connect(
				URL, USERNAME, PASSWORD);
		
		return influxDB;
	}

	public static String getDatabaseName() {
		return DATABASE_NAME;
	}
}
