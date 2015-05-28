package com.ligadata.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.MatchesPattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.IntHolder;

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

		int intTimeWindow = 0;
		int intThreshold = 0;
		String activateProducerFlag = request
				.getParameter("activateProducerParam");
		String timeWindow = request.getParameter("timeWindow");
		String threshold = request.getParameter("threshold");

		if (timeWindow == null || threshold == null)
			throw new ServletException(
					"missing timeWindow or threshold parameters");

		if (activateProducerFlag == null
				|| (!activateProducerFlag.toUpperCase().equals("TRUE") && !activateProducerFlag
						.toUpperCase().equals("FALSE")))
			throw new ServletException(
					"activateProducerParam is null or doesnt contain true or false values..");

		try {
			intThreshold = Integer.parseInt(threshold);
			intTimeWindow = Integer.parseInt(timeWindow);
		} catch (Exception e) {
			// e.printStackTrace();
		}
		/**
		 * this parameter indicates the time window in our Demo, 20 means every
		 * 20 seconds the program will do the below: 1- refresh the alerts
		 * matrix. 2- increment the cumulative alerts over threshold for every
		 * new alert. 3- reset the counters matrix
		 * */
		/**
		 * this parameter is the one needed when the refresh alert function is
		 * called, as the function compares every counter in the counter matrix
		 * with this value, and it triggers an alert if the counter is greater
		 * than or equal this parameter value.
		 * */

		if (MatchingParameters.getThreshold() != intThreshold
				|| MatchingParameters.getRefreshMatrixAfter() != intTimeWindow)
			MatchingParameters.resetAlerts();

		if (intThreshold != 0 && intTimeWindow != 0) {
			MatchingParameters.setThreshold(intThreshold);
			MatchingParameters.setRefreshMatrixAfter(intTimeWindow);
		} else {
			MatchingParameters.setThreshold(1000);
			MatchingParameters.setRefreshMatrixAfter(30);
		}

		GlobalParams.setProducerActived(Boolean
				.parseBoolean(activateProducerFlag));

		if (GlobalParams.isProducerActived()) {
			
			GlobalParams.setProducerActived(false);
			ArrayList<String> dataFiles = new ArrayList<String>();
			ArrayList<String> industries = new ArrayList<String>();
			ArrayList<String> subjects = new ArrayList<String>();

			// this is the directory of the text files which the program needs
			// to feed the engine.
			String dataDir = "/home/fatafat/Documents/inputdata/";
//			String dataDir = "C:\\Fatafat\\inputdata\\";
			// the name of each file we would like to use in the above directory
			// assigned to variable "dataDir"
			dataFiles.add(dataDir + "100k-matched2.txt");
			dataFiles.add(dataDir + "xxx2.txt"); // dummy data to minimize the
													// matching rate

			// subjects/topics files, make sure each term is in a singe line.
			subjects.add("fraud.csv");
			subjects.add("investment.csv");
			subjects.add("retirement.csv");
			subjects.add("security.csv");

			// industries files, make sure each term is in a singe line.
			industries.add("financialPubs.csv");
			industries.add("insurance.csv");
			industries.add("investmentBanks.csv");
			industries.add("retailBanks.csv");

			// Currently for both industries and subjects must be placed in
			// separate folders.
//			GlobalParams.setIndustriesDir("/home/twitter-demo/fatafat/datasets/industries/");
//			GlobalParams.setSubjectsDir("/home/twitter-demo/fatafat/datasets/subjects/");
//			GlobalParams.setIndustriesDir("C:\\Fatafat\\fatafat\\datasets\\industries\\");
//			GlobalParams.setSubjectsDir("C:\\Fatafat\\fatafat\\datasets\\subjects\\");
			GlobalParams.setIndustriesDir("/home/fatafat/Documents/datasets/industries/");
			GlobalParams.setSubjectsDir("/home/fatafat/Documents/datasets/subjects/");
			
			GlobalParams.setDataFiles(dataFiles);
			GlobalParams.setIndustriesFiles(industries);
			GlobalParams.setSubjectsFiles(subjects);

			try {
				GlobalParams.setProducerActived(true);
				Main.mainFunc();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			MatchingParameters.resetAllCounters();
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
