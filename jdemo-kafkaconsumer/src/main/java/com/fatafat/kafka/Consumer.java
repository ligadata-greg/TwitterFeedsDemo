package com.fatafat.kafka;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.fatafat.counters.CountersHelper;
import com.ligadata.demo.TweetAnalysis;
import com.ligadata.parameters.GlobalParams;
import com.ligadata.parameters.MatchingParameters;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

//import com.ligadata.fatafat.GenericDAO;
//import com.ligadata.fatafat.impl.OutputJsonDAO;
//import com.ligadata.fatafat.objs.OutputJsonObj;

public class Consumer {

	private KafkaStream<byte[], byte[]> m_stream;
	private int m_threadNumber;
	static int counter_processed = 0;
	int counter = 0;

	public Consumer() {

	}

	public Consumer(KafkaStream<byte[], byte[]> a_stream, int a_threadNumber) {
		m_threadNumber = a_threadNumber;
		m_stream = a_stream;
	}
	private static void debug(String msg) {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss SSS");
		System.out.println(msg + " " + format.format(new Date()));
	}
	public void run() {

		try {
			System.out.println("calling Consumer.run()");
			ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
			System.out.println("connect to zookeeper");
			long globalStartTime = System.nanoTime();
		    float currentTime = 0;
		    TweetAnalysis tweet = null;
		    ArrayList<String> industries = new ArrayList<String>();
			ArrayList<String> subjects = new ArrayList<String>();
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
			GlobalParams.setIndustriesFiles(industries);
			GlobalParams.setSubjectsFiles(subjects);
			GlobalParams.setIndustriesDir("/home/fatafat/Documents/datasets/industries/");
			GlobalParams.setSubjectsDir("/home/fatafat/Documents/datasets/subjects/");
			
			debug("MatchingParameters started");

			MatchingParameters gp = new MatchingParameters();
			Thread t = new Thread(gp);
			t.start();
		    
			debug("MatchingParameters finished");
			
//			CountersHelper cHelper = new CountersHelper();
//			Thread t2 = new Thread(cHelper);
//			t2.start();
			
			while (true) {
			if (it.hasNext()) {
					
					currentTime = (System.nanoTime() - globalStartTime) /  1000000000f;
//					System.out.println("currentTime >>>>>>>>>>>>>> " + currentTime);
					
					if (currentTime  > 2) {
						counter = 0;
						globalStartTime = System.nanoTime();
					}

//					System.out.println("Thread " + m_threadNumber + ": "
//							+ new String(it.next().message()));
					debug("analyzing tweet started");
					tweet = new TweetAnalysis(new String(it.next().message()));
					debug("analyzing tweet finished");
					debug("increment counters started");
					MatchingParameters.incCounters(tweet);
 					debug("increment counters finished");
					
//					counter_processed++;
//					counter++;
//					System.out.println("counter_processed  >>>>>>>>>>> "
//							+ counter_processed);
//					System.out.println("counter  >>>>>>>>>>> "
//							+ counter);

//					org.json.simple.JSONObject obj = new org.json.simple.JSONObject();
//					obj.put("total", "" + Consumer.counter_processed);
//					obj.put("latest", "" + counter);
//					obj.put("tags", "All Data");

//					c.setHashTagData("#Obama",obj.toString());
//					c.setData(obj.toString());
//					c.setData(new String(it.next().message()));
					
//					OutputJsonObj obj = new OutputJsonObj("1", new String(it.next().message()));
//					System.out.println(ojDao.insert(obj, preparedStatement));
					
				}
			}

			// c.close();

			// System.out.println("Shutting down Thread: " + m_threadNumber);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
//				con.close();
//				preparedStatement.close();
//				c.close();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
