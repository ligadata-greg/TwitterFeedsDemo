package com.ligadata.twitterfeeds.kafkaproducer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ligadata.parameters.GlobalParams;

public class Main {

	public static void mainFunc() throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(15);
		for (String filepath : GlobalParams.getDataFiles()) {
			ReadFromFile f = new ReadFromFile(filepath);
			executor.execute(f);
		}
		executor.shutdown();
	}
}
