package driver;

import java.util.ArrayList;
import org.apache.commons.lang.time.StopWatch;
import com.ligadata.parameters.GlobalParams;
import com.ligadata.parameters.MatchingParameters;
import com.ligadata.twitterfeeds.kafkaproducer.Main;

public class Driver {

	public static void main(String[] args) {
		
		ArrayList<String> dataFiles = new ArrayList<String>();
		ArrayList<String> industries = new ArrayList<String>();
		ArrayList<String> subjects = new ArrayList<String>();
		
		//this is the directory of the text files which the program needs to feed the engine.
		String dataDir = "D:\\Fatafat\\data110515\\";
		
		//the name of each file we would like to use in the above directory assigned to variable "dataDir"
		dataFiles.add(dataDir + "100k-matched2.txt");
		dataFiles.add(dataDir + "xxx2.txt"); //dummy data to minimize the matching rate
		
		//subjects/topics files, make sure each term is in a singe line.
		subjects.add("fraud.csv");
		subjects.add("investment.csv");
		subjects.add("retirement.csv");
		subjects.add("security.csv");
		
		//industries files, make sure each term is in a singe line.
		industries.add("financialPubs.csv");
		industries.add("insurance.csv");
		industries.add("investmentBanks.csv");
		industries.add("retailBanks.csv");
		
		/**
		 * this parameter indicates the time window in our Demo, 20 means every 20 seconds the program will do the below:
		 * 1- refresh the alerts matrix.
		 * 2- increment the cumulative alerts over threshold for every new alert.
		 * 3- reset the counters matrix
		 * */
		MatchingParameters.setRefreshMatrixAfter(20);
		
		/**
		 * this parameter is the one needed when the refresh alert function is called, as the function compares every counter in the 
		 * counter matrix with this value, and it triggers an alert if the counter is greater than or equal this parameter value.  
		 * */
		MatchingParameters.setThreshold(30000);
		
		//Currently for both industries and subjects must be placed in separate folders. 
		GlobalParams.setIndustriesDir("D:\\Fatafat\\demo-data\\industries\\");
		GlobalParams.setSubjectsDir("D:\\Fatafat\\demo-data\\subjects\\");
		
		GlobalParams.setDataFiles(dataFiles);
		GlobalParams.setIndustriesFiles(industries);
		GlobalParams.setSubjectsFiles(subjects);
		
		try {
			GlobalParams.setProducerActived(true);
			Main.mainFunc();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// testHashMap();

		StopWatch sw = new StopWatch();
		sw.start();
		int seconds = 0;
		while (true) {
			seconds = (int) (sw.getTime() / 1000);
			if (seconds % 10 == 1) {
				sw.reset();
				sw.start();
				System.out.println("Processed: "
						+ MatchingParameters.getTotalProcessedTweets());
				System.out.println("Matched:" + MatchingParameters.getTotalMatchedTweets());
				System.out.println("Cummulative alerts over threshold: "
						+ MatchingParameters.getCummulativeNumOfAlertsOverThreshold());
				System.out.println("Matrix: " + MatchingParameters.getCummulativeResult());
				System.out.println("Alerts: " + MatchingParameters.getAlerts());
				System.out.println();
				System.out.println(">>>>>>><<<<<<<<<<");
				System.out.println();
			}
		}
	}
}
