package com.ligadata.parameters;

import java.util.ArrayList;

public class GlobalParams {

	private static boolean producerActived = false;
	private static String industriesDir = null;
	private static String subjectsDir = null;
	private static ArrayList<String> industriesFiles = new ArrayList<String>();
	private static ArrayList<String> subjectsFiles = new ArrayList<String>();
	private static ArrayList<String> dataFiles = new ArrayList<String>();
	
	public static boolean isProducerActived() {
		return producerActived;
	}
	public static void setProducerActived(boolean producerActived) {
		GlobalParams.producerActived = producerActived;
	}
	public static String getIndustriesDir() {
		return industriesDir;
	}
	public static void setIndustriesDir(String industriesDir) {
		GlobalParams.industriesDir = industriesDir;
	}
	public static String getSubjectsDir() {
		return subjectsDir;
	}
	public static void setSubjectsDir(String subjectsDir) {
		GlobalParams.subjectsDir = subjectsDir;
	}
	public static ArrayList<String> getIndustriesFiles() {
		return industriesFiles;
	}
	public static void setIndustriesFiles(ArrayList<String> industriesFiles) {
		GlobalParams.industriesFiles = industriesFiles;
	}
	public static ArrayList<String> getSubjectsFiles() {
		return subjectsFiles;
	}
	public static void setSubjectsFiles(ArrayList<String> subjectsFiles) {
		GlobalParams.subjectsFiles = subjectsFiles;
	}
	public static ArrayList<String> getDataFiles() {
		return dataFiles;
	}
	public static void setDataFiles(ArrayList<String> dataFiles) {
		GlobalParams.dataFiles = dataFiles;
	}
	
}
