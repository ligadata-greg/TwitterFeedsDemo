package com.ligadata.servlets;

import java.io.IOException;

import javax.annotation.MatchesPattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ligadata.parameters.*;
import com.ligadata.twitterfeeds.kafkaproducer.Main;

/**
 * Servlet implementation class ActivateTwitterProducer
 */

public class ActivateTwitterProducer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActivateTwitterProducer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String activateProducerFlag = request
				.getParameter("activateProducerParam");
		if (activateProducerFlag == null
				|| (!activateProducerFlag.toUpperCase().equals("TRUE") && !activateProducerFlag
						.toUpperCase().equals("FALSE")))
			throw new ServletException(
					"activateProducerParam is null or doesnt contain true or false values..");

		GlobalParams.setProducerActived(Boolean.parseBoolean(activateProducerFlag));

		if (GlobalParams.isProducerActived()) {
			String[] arr = 
				{"D:\\Fatafat\\data110515\\v1-temp_1.txt",
					"D:\\Fatafat\\data110515\\v2-temp_5.txt",
					"D:\\Fatafat\\data110515\\v3-temp_4.txt",
					"D:\\Fatafat\\data110515\\v4-temp_3.txt",
					"D:\\Fatafat\\data110515\\v5-temp_2.txt",
					"D:\\Fatafat\\data110515\\v6-temp_1.txt",
					"D:\\Fatafat\\data110515\\temp_1.txt",
					"D:\\Fatafat\\data110515\\temp_2.txt",
					"D:\\Fatafat\\data110515\\temp_3.txt"
					};

			try {
				//Main.mainFunc(arr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
