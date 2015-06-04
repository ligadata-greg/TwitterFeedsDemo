package com.ligadata.twitterfeeds.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.influxdb.InfluxDB;
import org.json.JSONObject;

import com.fatafat.dao.impl.AlertsDao;
import com.fatafat.dao.objs.AlertsObj;
import com.fatafat.dao.objs.GenericObj;

/**
 * Servlet implementation class LandingService
 */
public class LandingService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LandingService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AlertsDao aDao = new AlertsDao();
		InfluxDB connection = aDao.getConnection();
		
		List<GenericObj> result = aDao.selectAllStoredRecsInLastSecond(connection);
		JSONObject json = new JSONObject();
		try{
			json.put("result",result);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		java.io.PrintWriter out = response.getWriter();
		out.println(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
