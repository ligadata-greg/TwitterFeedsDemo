package com.ligadata.twitterfeeds.kafkaconsumer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.ligadata.fatafat.GenericDAO;
import com.ligadata.fatafat.impl.OutputJsonDAO;
import com.ligadata.fatafat.objs.OutputJsonObj;
//import com.ligadata.twitterfeeds.zookeeperclient.Client;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

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

	public void run() {
		Connection con = null;
		PreparedStatement preparedStatement = null;
//		Client c = null;
		GenericDAO dao = null;
		OutputJsonDAO ojDao = null;
		
		try {
			System.out.println("calling Consumer.run()");
			ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
//			c = new Client();
			System.out.println("connect to zookeeper");
			long globalStartTime = System.nanoTime();
		    float currentTime = 0;
		    ojDao = new OutputJsonDAO();
		    con = ojDao.getConnection();
		    preparedStatement = con.prepareStatement("INSERT INTO "
					+ GenericDAO.getSCHEMA_NAME() + ".outputdata(userid, json)" + "VALUES (?, ?)");
			
		    while (true) {
				if (it.hasNext()) {
					
					currentTime = (System.nanoTime() - globalStartTime) /  1000000000f;
					System.out.println("currentTime >>>>>>>>>>>>>> " + currentTime);
					
					if (currentTime  > 2) {
						counter = 0;
						globalStartTime = System.nanoTime();
					}

//					System.out.println("Thread " + m_threadNumber + ": "
//							+ new String(it.next().message()));
//
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
					
					OutputJsonObj obj = new OutputJsonObj("1", new String(it.next().message()));
					System.out.println(ojDao.insert(obj, preparedStatement));
					
				}
			}

			// c.close();

			// System.out.println("Shutting down Thread: " + m_threadNumber);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				con.close();
				preparedStatement.close();
//				c.close();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
