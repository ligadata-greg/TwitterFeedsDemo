package com.fatafat.counters;

import org.apache.commons.lang.time.StopWatch;

import com.fatafat.kafka.KafkaProducer;
import com.ligadata.datasets.GlobalWordSets;
import com.ligadata.parameters.MatchingParameters;

public class CountersHelper implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		StopWatch sw = new StopWatch();
		sw.start();
		int seconds = 0;
		String tempText = null;
	    KafkaProducer producer = new KafkaProducer();
		System.out.println("Industries Wordset: " + GlobalWordSets.getIndustriesDataSets());
		System.out.println("Subjects Wordset: " + GlobalWordSets.getSubjectsDataSets());
		while (true) {
			seconds = (int) (sw.getTime() / 1000);
			if (seconds % 10 == 1) {
				sw.reset();
				sw.start();
				tempText = "Processed: "
						+ MatchingParameters.getTotalProcessedTweets()
						+ " Matched: " + MatchingParameters.getTotalMatchedTweets()
						+ " Cummulative alerts over threshold: " + MatchingParameters.getCummulativeNumOfAlertsOverThreshold()
						+ " Matrix: " + MatchingParameters.getCummulativeResult()
						+ " Alerts: " + MatchingParameters.getAlerts();
				producer.send(tempText);
				System.out.println(tempText);
				System.out.println();
				System.out.println(">>>>>>><<<<<<<<<<");
				System.out.println();
			}
		}

	}

}
