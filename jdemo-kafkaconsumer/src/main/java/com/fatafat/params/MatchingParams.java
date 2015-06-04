package com.fatafat.params;

import java.util.HashMap;
import java.util.Map;

public class MatchingParams {

	private static boolean activateConsumer;
	private static Map<String, Integer> threshold;
	private static int timeWindow;
	
	static{
		int tempVal = 30;
		threshold = new HashMap<String, Integer>();
		threshold.put("barclays_positive", tempVal);
		threshold.put("barclays_negative", tempVal);
		threshold.put("citibank_positive", tempVal);
		threshold.put("citibank_negative", tempVal);
		threshold.put("bofa_positive", tempVal);
		threshold.put("bofa_negative", tempVal);
		threshold.put("royalbankofscotland_positive", tempVal);
		threshold.put("royalbankofscotland_negative", tempVal);
		threshold.put("hsbc_positive", tempVal);
		threshold.put("hsbc_negative", tempVal);
		threshold.put("jpm_positive", tempVal);
		threshold.put("jpm_negative", tempVal);
	}

	public static boolean isActivateConsumer() {
		return activateConsumer;
	}

	public static void setActivateConsumer(boolean activateConsumer) {
		MatchingParams.activateConsumer = activateConsumer;
	}

	public static Map<String, Integer> getThreshold() {
		return threshold;
	}

	public static void setThreshold(Map<String, Integer> threshold) {
		MatchingParams.threshold = threshold;
	}

	public static int getTimeWindow() {
		return timeWindow;
	}

	public static void setTimeWindow(int timeWindow) {
		MatchingParams.timeWindow = timeWindow;
	}
}
