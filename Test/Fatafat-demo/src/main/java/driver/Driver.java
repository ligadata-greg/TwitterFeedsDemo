package driver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.ligadata.demo.TweetAnalysis;
import com.ligadata.parameters.Params;
import com.ligadata.twitterfeeds.kafkaproducer.Main;

/**
 * ,
				"bank of america, iphone, loan rate, cvs",
				"pin, citi bank, key, term deposit", "barclays" 
 * */
public class Driver {

	public static void main(String[] args) {

//		String[] arr = {"google","facebook","twitter","apple","iphone","mac",
//				"barclays","citi bank","bank of america","chase bank","bank"};
//
//		try {
//			Main.mainFunc(arr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		// testHashMap();
		
		
		
		
		JSONObject json = new JSONObject();
		try {
			json.put("processed", Params.getTotalProcessedTweets());
			json.put("matched", Params.getTotalMatchedTweets());
			json.put("getCummulativeNumOfAlertsOverThreshold", Params.getCummulativeNumOfAlertsOverThreshold());
			json.put("matrix", Params.getCummulativeResult());
			json.put("alerts", Params.getAlerts());
			
			System.out.println(json);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void testHashMap() {
		HashMap<String, Integer> sample = new HashMap<String, Integer>();
		sample.put("s1", 10);
		sample.put("s2", 20);
		sample.put("s3", 30);
		System.out.println("map size: " + sample.size());
		Iterator it = sample.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println("Key: " + pair.getKey() + " Value: "
					+ pair.getValue());
		}
	}
}
