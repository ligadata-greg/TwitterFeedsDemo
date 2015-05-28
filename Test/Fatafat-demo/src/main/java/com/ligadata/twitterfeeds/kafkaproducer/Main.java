package com.ligadata.twitterfeeds.kafkaproducer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.ligadata.parameters.GlobalParams;
import com.ligadata.parameters.MatchingParameters;

public class Main {

	public static void mainFunc() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (String filepath : GlobalParams.getDataFiles()) {
			ReadFromFile f = new ReadFromFile(filepath);
			executor.execute(f);
		}
		executor.shutdown();
		
		MatchingParameters gp = new MatchingParameters();
		Thread t = new Thread(gp);
		t.start();
	}
}
