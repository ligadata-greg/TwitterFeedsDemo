package com.ligadata.twitterfeeds.kafkaproducer;

public class GlobalParamaters {

	private static boolean producerActive = false;
	private static String folderPath = "/data/tweets";

	public static String getFolderPath() {
		return folderPath;
	}

	public static void setFolderPath(String folderPath) {
		GlobalParamaters.folderPath = folderPath;
	}

	public static boolean isProducerActive() {
		return producerActive;
	}

	public static void setProducerActive(boolean producerActive) {
		GlobalParamaters.producerActive = producerActive;
	}

}
