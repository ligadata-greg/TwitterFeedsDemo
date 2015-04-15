package com.ligadata.twitterfeeds.params;

public final class GlobalParameters {

	private static final String serverIP = "localhost";
	private static final String servicePort = "8081";

	public static String getServerip() {
		return serverIP;
	}

	public static String getServiceport() {
		return servicePort;
	}

	@Override
	public String toString() {
		return "GlobalParameters [serverIP=" + serverIP + ", servicePort="
				+ servicePort + "]";
	}

}
