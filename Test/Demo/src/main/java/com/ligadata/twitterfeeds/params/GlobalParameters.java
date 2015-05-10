package com.ligadata.twitterfeeds.params;

public final class GlobalParameters {

	private static final String serverIP = "fatafat.ligadata.com";
	private static final String servicePort = "8081";

	private static boolean kafkaProdActive = false;
	private static boolean kafkaConsActive = false;
	
	public static boolean isKafkaConsActive() {
		return kafkaConsActive;
	}

	public static void setKafkaConsActive(boolean kafkaConsActive) {
		GlobalParameters.kafkaConsActive = kafkaConsActive;
	}

	public static boolean isKafkaProdActive() {
		return kafkaProdActive;
	}

	public static void setKafkaProdActive(boolean kafkaProdActive) {
		GlobalParameters.kafkaProdActive = kafkaProdActive;
	}

	public static String getServerip() {
		return serverIP;
	}

	public static String getServiceport() {
		return servicePort;
	}

	private GlobalParameters() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "GlobalParameters [serverIP=" + serverIP + ", servicePort="
				+ servicePort + "]";
	}

}
