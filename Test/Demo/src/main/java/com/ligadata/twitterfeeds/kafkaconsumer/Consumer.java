package com.ligadata.twitterfeeds.kafkaconsumer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.ligadata.dao.fatafat.impl.GenericDAO;
import com.ligadata.dao.fatafat.impl.OutputJsonDAO;
import com.ligadata.dao.fatafat.objs.OutputJsonObj;
import com.ligadata.twitterfeeds.params.GlobalParameters;
import com.ligadata.twitterfeeds.zookeeperclient.Client;
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
		Client c = new Client();
		Connection con = new OutputJsonDAO().getConnection();
		OutputJsonDAO ojDao = new OutputJsonDAO();
		OutputJsonObj obj = null;
		PreparedStatement preparedStatement = null;
		
		try {
			System.out.println("calling Consumer.run()");
			ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
			System.out.println("connect to zookeeper");
			long globalStartTime = System.nanoTime();
			float currentTime = 0;
			preparedStatement = con.prepareStatement("INSERT INTO "
					+ GenericDAO.getSCHEMA_NAME() + ".outputdata(userid, json)" + "VALUES (?, ?)");
			
			while (GlobalParameters.isKafkaConsActive()) {
				System.out.println("Consumer thread have started.");
				if (it.hasNext()) {
					System.out.println("iterator found items.");
					currentTime = (System.nanoTime() - globalStartTime) / 1000000000f;
					System.out.println("currentTime >>>>>>>>>>>>>> "
							+ currentTime);

					if (currentTime > 2) {
						counter = 0;
						globalStartTime = System.nanoTime();
					}

					obj = new OutputJsonObj("1", new String(it
							.next().message()));
					System.out.println(ojDao.insert(obj, preparedStatement));

				}
			}
			c.close();
			preparedStatement.close();
			con.close();
			System.out.println("Shutting down Thread: " + m_threadNumber);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
				preparedStatement.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
