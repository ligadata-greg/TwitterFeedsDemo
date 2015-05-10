package com.ligadata.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.ligadata.parameters.Params;
import com.ligadata.twitterfeeds.kafkaproducer.Main;

public class CountersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CountersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		JSONObject json = new JSONObject();
		try {
			json.put("processed", Params.getTotalProcessedTweets());
			json.put("matched", Params.getTotalMatchedTweets());
			json.put("getCummulativeNumOfAlertsOverThreshold", Params.getCummulativeNumOfAlertsOverThreshold());
			json.put("matrix", Params.getCummulativeResult());
			json.put("alerts", Params.getAlerts());
			
//			System.out.println(json);
			
			java.io.PrintWriter out = response.getWriter();
			out.println(json.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
