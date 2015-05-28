package com.ligadata.parameters;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.time.StopWatch;

import com.ligadata.demo.TweetAnalysis;

public class MatchingParameters implements Runnable {

	private static int totalProcessedTweets = 0;
	private static int totalMatchedTweets = 0;
	private static int cummulativeNumOfAlertsOverThreshold = 0;
	private static int refreshMatrixAfter = 30;
	private static int threshold = 10;
	private static ConcurrentHashMap<String, Integer> cummulativeResult = new ConcurrentHashMap<String, Integer>();
	private static ConcurrentHashMap<String, String> alerts = new ConcurrentHashMap<String, String>();

	public void run() {
		// TODO Auto-generated method stub
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		int seconds = 0;
		while (GlobalParams.isProducerActived()) {
			seconds = (int) (stopWatch.getTime() / 1000);
			if (seconds >= MatchingParameters.getRefreshMatrixAfter()) {
				stopWatch.reset();
				stopWatch.start();
				MatchingParameters.refreshAlerts();
				MatchingParameters.resetSubjectsByIndustry();
			}
		}
	}

	public static void resetAllCounters() {
		totalProcessedTweets = 0;
		totalMatchedTweets = 0;
		cummulativeNumOfAlertsOverThreshold = 0;
//		refreshMatrixAfter = 0;
//		threshold = 0;
		resetSubjectsByIndustry();
		resetAlerts();
	}

	public static void resetAlerts(){
		alerts.clear();
		cummulativeNumOfAlertsOverThreshold = 0;
	}
	
	private static void refreshAlerts() {
		Iterator it = cummulativeResult.entrySet().iterator();
		Map.Entry pair = null;
		String tempKey = null;
		Integer tempVal = null;
		String alertText = null;
		Date date = null;
		String dateToStr = null;

		while (it.hasNext()) {
			pair = (Map.Entry) it.next();
			tempKey = (String) pair.getKey();
			tempVal = (Integer) pair.getValue();

			if (tempVal.intValue() >= threshold) {
				date = new Date();
				dateToStr = DateFormat.getTimeInstance(DateFormat.MEDIUM)
						.format(date);
				cummulativeNumOfAlertsOverThreshold++;
				alertText = tempVal + " Alerts "
						+ tempKey.substring(0, tempKey.indexOf('_')) + "/"
						+ tempKey.substring(tempKey.indexOf('_') + 1) + " at "
						+ dateToStr;
				alerts.put(tempKey, alertText);
			}
		}

	}

	public static void incCounters(TweetAnalysis tweetObj) {
		if (tweetObj != null && !tweetObj.getTweet().equals(null)) {
			totalProcessedTweets++;
			if (tweetObj.isSubjectMatch()) {
				totalMatchedTweets++;
				updateSubjectsByIndustry(tweetObj);
			}
		}
	}

	private static void updateSubjectsByIndustry(TweetAnalysis tweetObj) {
		HashMap<String, Boolean> tempSubMap = tweetObj.getSubMatchingResult();
		HashMap<String, Boolean> tempIndMap = tweetObj.getIndMatchingResult();

		Iterator subIt = tempSubMap.entrySet().iterator();
		Iterator indIt = null;

		String subTempKey, indTempKey = null;
		Boolean subTempVal, indTempVal = false;

		Integer tempVar = null;

		while (subIt.hasNext()) {
			Map.Entry subPair = (Map.Entry) subIt.next();
			subTempKey = (String) subPair.getKey();
			subTempVal = (Boolean) subPair.getValue();
			indIt = tempIndMap.entrySet().iterator();

			while (indIt.hasNext()) {
				Map.Entry indPair = (Map.Entry) indIt.next();
				indTempKey = (String) indPair.getKey();
				indTempVal = (Boolean) indPair.getValue();

				tempVar = (Integer) cummulativeResult.get(subTempKey + "_"
						+ indTempKey);

				if (indTempVal && subTempVal) {
					if (tempVar == null)
						cummulativeResult.put(subTempKey + "_" + indTempKey, 1);
					else
						cummulativeResult.put(subTempKey + "_" + indTempKey,
								tempVar + 1);
				} else {
					if (tempVar == null)
						cummulativeResult.put(subTempKey + "_" + indTempKey, 0);
				}
			}

		}
	}

	public static void resetSubjectsByIndustry() {
		cummulativeResult.clear();
	}

	public static int getTotalProcessedTweets() {
		return totalProcessedTweets;
	}

	public static int getTotalMatchedTweets() {
		return totalMatchedTweets;
	}

	public static int getCummulativeNumOfAlertsOverThreshold() {
		return cummulativeNumOfAlertsOverThreshold;
	}

	public static ConcurrentHashMap<String, Integer> getCummulativeResult() {
		return cummulativeResult;
	}

	public static ConcurrentHashMap<String, String> getAlerts() {
		return alerts;
	}

	public static int getRefreshMatrixAfter() {
		return refreshMatrixAfter;
	}

	public static void setRefreshMatrixAfter(int refreshMatrixAfter) {
		MatchingParameters.refreshMatrixAfter = refreshMatrixAfter;
	}

	public static int getThreshold() {
		return threshold;
	}

	public static void setThreshold(int threshold) {
		MatchingParameters.threshold = threshold;
	}
}
