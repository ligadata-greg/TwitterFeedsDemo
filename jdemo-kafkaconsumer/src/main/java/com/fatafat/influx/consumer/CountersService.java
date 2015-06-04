package com.fatafat.influx.consumer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.influxdb.InfluxDB;

import com.fatafat.counters.CountersRefresher;
import com.fatafat.counters.MatchingCounters;
import com.fatafat.dao.impl.MatchedTweetsDao;
import com.fatafat.dao.objs.*;
import com.fatafat.kafka.KafkaProducer;
import com.fatafat.params.MatchingParams;
import com.ligadata.parameters.MatchingParameters;

public class CountersService implements Runnable {

	@Override
	public void run() {

		MatchedTweetsDao dao = new MatchedTweetsDao();
		InfluxDB connection = dao.getConnection();
		List<GenericObj> result = null;
		MatchedTweetObj tempObj = null;
		Iterator it = null;
		Map.Entry<String, Integer> pair;
		StringBuilder outputMessage = null;
		String seperator = ",";
		KafkaProducer producer = new KafkaProducer();
		String msg = null;

		while (true) {
			result = dao.selectAllStoredRecsInLastSecond(connection);
			if (result != null) {
				for (GenericObj genericObj : result) {
					if (genericObj instanceof MatchedTweetObj) {
						tempObj = (MatchedTweetObj) genericObj;
						outputMessage = new StringBuilder();
						try {
							MatchingCounters.incCounters(tempObj);
							// outputMessage.append(((MatchedTweetObj)
							// genericObj).getTime());
							outputMessage.append("System.twittermsg2");
							outputMessage.append(seperator);
							it = MatchingCounters.getCountersMatrix()
									.entrySet().iterator();
							while (it.hasNext()) {
								pair = (Map.Entry) it.next();
								outputMessage.append(pair.getValue());
								outputMessage.append(seperator);
								outputMessage.append(MatchingParams
										.getThreshold().get(pair.getKey()));
								outputMessage.append(seperator);
							}
							outputMessage.append(tempObj.getTweetText());
							outputMessage.append(seperator);
							outputMessage.append(tempObj.getUsername());
							outputMessage.append(seperator);
							outputMessage.append(tempObj.getRetweetCount());
							outputMessage.append(seperator);
							outputMessage.append(tempObj.getFavoriteCount());
							outputMessage.append(seperator);
							outputMessage.append(tempObj.getFollowersCount());
							outputMessage.append(seperator);
							outputMessage.append(MatchingCounters
									.getTotalProcessedTweets());
							outputMessage.append(seperator);
							outputMessage.append(MatchingCounters
									.getTotalMatchedTweets());
							outputMessage.append(seperator);
							outputMessage
									.append(MatchingParams.getTimeWindow());
							outputMessage.append(seperator);
							outputMessage.append(tempObj.getNps());
							outputMessage.append(seperator);

							msg = outputMessage.toString().substring(0,
									outputMessage.toString().length() - 1);
							System.out.println(msg);
							producer.send(msg);

						} catch (Exception e) {
							e.printStackTrace();
							producer.close();
							// break;
						}
					}
				}
			}
		}
	}

}
