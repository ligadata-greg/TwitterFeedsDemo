package com.ligadata.twitterfeeds.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ligadata.dao.fatafat.impl.OutputJsonDAO;
import com.ligadata.dao.fatafat.impl.TwitterFeedsFiltersDAO;
import com.ligadata.dao.fatafat.objs.TwitterFeedsFiltersObj;
import com.ligadata.twitterfeeds.kafkaproducer.Main;
import com.ligadata.twitterfeeds.params.GlobalParameters;

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
		String activateProducerFlag = request
				.getParameter("activateProducerParam");
		String wordSetParam = request.getParameter("wordSetParam");
		if (activateProducerFlag == null || wordSetParam == null)
			throw new ServletException(
					"activateProducerParam or wordSetParam is null.");

		boolean isTrueFlag = activateProducerFlag.toUpperCase().equals("TRUE");
		boolean isFalseFlag = activateProducerFlag.toUpperCase()
				.equals("FALSE");
		OutputJsonDAO outputDAO = new OutputJsonDAO();
		TwitterFeedsFiltersDAO feedsDAO = new TwitterFeedsFiltersDAO();
		TwitterFeedsFiltersObj feedsObj = new TwitterFeedsFiltersObj();

		if (isTrueFlag && isFalseFlag)
			throw new ServletException(
					"activateProducerParam does not contain true or false.");

		GlobalParameters.setKafkaProdActive(Boolean
				.parseBoolean(activateProducerFlag));

		if (GlobalParameters.isKafkaProdActive()) {
			// to make sure the previous connection to twitter have been closed.
			GlobalParameters.setKafkaProdActive(false);
			// missing step: Empty producer queue
			// missing step: Empty consumer queue
			// stop conumer.
			GlobalParameters.setKafkaConsActive(false);

			// fill args from database (wordSetParam).
//			feedsObj = (TwitterFeedsFiltersObj) feedsDAO.selectById(1);
			feedsObj = (TwitterFeedsFiltersObj) feedsDAO.selectById(Integer
					.parseInt(wordSetParam));
			String[] args = { feedsObj.getCsvWordSet() };
			GlobalParameters.setKafkaProdActive(true);
			try {
//				com.ligadata.twitterfeeds.kafkaproducer.Main.mainFunc(args);
				com.ligadata.twitterfeeds.kafkaproducer.Main prodMain = new com.ligadata.twitterfeeds.kafkaproducer.Main(args);
				Thread t = new Thread(prodMain);
				t.start();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 GlobalParameters.setKafkaConsActive(true);
			// truncate outputdata table.
//			outputDAO.truncateTable();
			
//			com.ligadata.twitterfeeds.kafkaconsumer.Main.mainFunc();
			com.ligadata.twitterfeeds.kafkaconsumer.Main consMain = new com.ligadata.twitterfeeds.kafkaconsumer.Main();
			Thread t2 = new Thread(consMain);
			t2.start();
		} else {
			System.out.println("Kafka producer is stopped.");
		}
		PrintWriter out = response.getWriter();
		out.print("Producer is set to " + GlobalParameters.isKafkaProdActive());
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
