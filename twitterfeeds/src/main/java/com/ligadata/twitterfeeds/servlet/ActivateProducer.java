package com.ligadata.twitterfeeds.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.ligadata.twitterfeeds.kafkaproducer.*;

/**
 * Servlet implementation class ActivateProducer
 */
public class ActivateProducer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActivateProducer() {
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

		JSONObject json = new JSONObject();
		try {
			json.put("isProducerActive", GlobalParamaters.isProducerActive());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		out.print(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String activateProducerFlag = request
				.getParameter("activateProducerParam");
		if (activateProducerFlag == null
				|| (!activateProducerFlag.toUpperCase().equals("TRUE") && !activateProducerFlag
						.toUpperCase().equals("FALSE")))
			throw new ServletException(
					"activateProducerParam is null or doesnt contain true or false values..");

		if (GlobalParamaters.isProducerActive()
				&& Boolean.parseBoolean(activateProducerFlag)) {

		} else {
			GlobalParamaters.setProducerActive(Boolean
					.parseBoolean(activateProducerFlag));
			if (GlobalParamaters.isProducerActive()) {
				String[] args = { "/data/v1-temp_1.txt,/data/temp_1.txt,/data/temp_2.txt,/data/temp_3.txt,/data/temp_4.txt" };
				try {
					Main.main(args);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
