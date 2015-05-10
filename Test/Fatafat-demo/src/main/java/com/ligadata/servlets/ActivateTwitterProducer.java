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

		Params.setProducerActived(Boolean.parseBoolean(activateProducerFlag));

		if (Params.isProducerActived()) {
			String[] arr = { "google", "facebook", "twitter", "apple",
					"iphone", "mac", "barclays", "citi bank",
					"bank of america", "chase bank", "bank" };

			try {
				Main.mainFunc(arr);
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
