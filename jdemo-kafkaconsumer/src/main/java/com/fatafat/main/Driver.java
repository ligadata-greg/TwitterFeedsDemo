package com.fatafat.main;

import java.sql.Connection;
import java.sql.SQLException;

import com.fatafat.dao.impl.DatasetsDao;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatasetsDao dao = new DatasetsDao();
		Connection conn = dao.getConnection();
		
		System.out.println(dao.selectAll(conn));
		
		try{
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		} finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
