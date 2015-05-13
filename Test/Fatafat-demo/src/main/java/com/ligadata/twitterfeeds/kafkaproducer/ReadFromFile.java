package com.ligadata.twitterfeeds.kafkaproducer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.lang.time.StopWatch;

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
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		int seconds = 0;
		String tweetText = null;
		String[] msgParts;

		while (GlobalParams.isProducerActived()) {

			seconds = (int) (stopWatch.getTime() / 1000);
			if (seconds >= MatchingParameters.getRefreshMatrixAfter()) {
				stopWatch.reset();
				stopWatch.start();
				MatchingParameters.refreshAlerts();
				MatchingParameters.resetSubjectsByIndustry();
			}

			if (line != null) {
				if (line.startsWith("System.twittermsg")) {
					msgParts = line.split(",");
					tweetText = msgParts[2];
//					System.out.println("tweet: " + tweetText);
					tweet = new TweetAnalysis(tweetText);
					// sb = new StringBuilder();
					// sb.append(line);
					MatchingParameters.incCounters(tweet);
					try {
						line = br.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					Thread t = Thread.currentThread();
//					String name = t.getName();
//					System.out.println("name=" + name);
//
//					System.out.println("Processed: "
//							+ Params.getTotalProcessedTweets());
//					System.out.println("Matched:"
//							+ Params.getTotalMatchedTweets());
//					System.out.println("Cummulative alerts over threshold: "
//							+ Params.getCummulativeNumOfAlertsOverThreshold());
//					System.out.println("Matrix: "
//							+ Params.getCummulativeResult());
//					System.out.println("Alerts: " + Params.getAlerts());
				} else {
//					sb.append(line);
				}
				// send to engine.
			} else {
				System.out.println("end of " + path);
				try {
					br = new BufferedReader(new FileReader(path));
					line = br.readLine();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
