package com.ligadata.twitterfeeds.kafkaproducer;

public class GlobalParamaters {

	private static boolean producerActive = false;

	public static boolean isProducerActive() {
		return producerActive;
	}

	public static void setProducerActive(boolean producerActive) {
		GlobalParamaters.producerActive = producerActive;
	}

}
