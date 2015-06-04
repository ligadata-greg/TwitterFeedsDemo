package com.fatafat.counters;

import java.util.LinkedHashMap;
import java.util.Map;

import com.fatafat.dao.objs.GenericObj;
import com.fatafat.dao.objs.MatchedTweetObj;

public class MatchingCounters {

	private static long totalProcessedTweets;
	private static long totalMatchedTweets;
	private static Map<String, Integer> countersMatrix = null;
	
	static{
		refreshCountersMatrix();
	}
	
	public static void refreshCountersMatrix(){
		countersMatrix = new LinkedHashMap();
		countersMatrix.put("barclays_positive", 0);
		countersMatrix.put("barclays_negative", 0);
		countersMatrix.put("citibank_positive", 0);
		countersMatrix.put("citibank_negative", 0);
		countersMatrix.put("bofa_positive", 0);
		countersMatrix.put("bofa_negative", 0);
		countersMatrix.put("royalbankofscotland_positive", 0);
		countersMatrix.put("royalbankofscotland_negative", 0);
		countersMatrix.put("hsbc_positive", 0);
		countersMatrix.put("hsbc_negative", 0);
		countersMatrix.put("jpm_positive", 0);
		countersMatrix.put("jpm_negative", 0);
	}
	private MatchingCounters(){
	}
	private static void incCountPerKey(String key){
		totalMatchedTweets++;
		countersMatrix.put(key, countersMatrix.get(key) + 1);
	}
	public static void incCounters(GenericObj obj) throws Exception{
		if(!(obj instanceof MatchedTweetObj))
			throw new Exception();
		MatchedTweetObj tempObj = (MatchedTweetObj) obj;
		totalProcessedTweets++;
		if(tempObj.getBarclays() + tempObj.getPositive() > 1)
			incCountPerKey("barclays_positive");
		if(tempObj.getBarclays() + tempObj.getNegative() > 1)
			incCountPerKey("barclays_negative");
		
		if(tempObj.getCitiBank() + tempObj.getPositive() > 1)
			incCountPerKey("citibank_positive");
		if(tempObj.getCitiBank() + tempObj.getNegative() > 1)
			incCountPerKey("citibank_negative");

		if(tempObj.getBofa() + tempObj.getPositive() > 1)
			incCountPerKey("bofa_positive");
		if(tempObj.getBofa() + tempObj.getNegative() > 1)
			incCountPerKey("bofa_negative");
		
		if(tempObj.getRoyalBankofScotland() + tempObj.getPositive() > 1)
			incCountPerKey("royalbankofscotland_positive");
		if(tempObj.getRoyalBankofScotland() + tempObj.getNegative() > 1)
			incCountPerKey("royalbankofscotland_negative");
		
		if(tempObj.getHsbc() + tempObj.getPositive() > 1)
			incCountPerKey("hsbc_positive");
		if(tempObj.getHsbc() + tempObj.getNegative() > 1)
			incCountPerKey("hsbc_negative");
		
		if(tempObj.getJpm() + tempObj.getPositive() > 1)
			incCountPerKey("jpm_positive");
		if(tempObj.getJpm() + tempObj.getNegative() > 1)
			incCountPerKey("jpm_negative");
	}
	public static long getTotalProcessedTweets() {
		return totalProcessedTweets;
	}
	public static long getTotalMatchedTweets() {
		return totalMatchedTweets;
	}
	public static Map<String, Integer> getCountersMatrix() {
		return countersMatrix;
	}
}
