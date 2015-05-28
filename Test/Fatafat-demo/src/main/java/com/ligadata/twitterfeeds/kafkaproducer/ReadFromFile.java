package com.ligadata.twitterfeeds.kafkaproducer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.ligadata.demo.TweetAnalysis;
import com.ligadata.parameters.GlobalParams;
import com.ligadata.parameters.MatchingParameters;

public class ReadFromFile implements Runnable {

	private BufferedReader br;
	private String line;
	private String path;

	public ReadFromFile(String filePath) {
		try {
			path = filePath;
			br = new BufferedReader(new FileReader(filePath));
			line = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {

		TweetAnalysis tweet = null;
//		StopWatch stopWatch = new StopWatch();
//		stopWatch.start();
//		int seconds = 0;
		String tweetText = null;
		String[] msgParts;
		List<String> tweets = new ArrayList<String>();
		
		while(line != null) {
			if (line.startsWith("System.twittermsg")) {
				msgParts = line.split(",");
				tweetText = msgParts[2];
				tweets.add(tweetText);
			}
			try {
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (GlobalParams.isProducerActived()) {

			for (String string : tweets) {
//				seconds = (int) (stopWatch.getTime() / 1000);
//				if (seconds >= MatchingParameters.getRefreshMatrixAfter()) {
//					stopWatch.reset();
//					stopWatch.start();
//					MatchingParameters.refreshAlerts();
//					MatchingParameters.resetSubjectsByIndustry();
//				}
				tweet = new TweetAnalysis(string);
				MatchingParameters.incCounters(tweet);
			}
		}
	}

}
