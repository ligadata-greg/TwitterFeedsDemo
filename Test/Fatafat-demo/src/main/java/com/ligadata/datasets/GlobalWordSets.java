package com.ligadata.datasets;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import com.ligadata.datasets.utils.ReadCSV;
import com.ligadata.parameters.GlobalParams;

public final class GlobalWordSets {

	private static HashMap<String, HashMap<String, Integer>> subjectsDataSets = new HashMap<String, HashMap<String, Integer>>();
	private static HashMap<String, HashMap<String, Integer>> industriesDataSets = new HashMap<String, HashMap<String, Integer>>();

	private GlobalWordSets() {
	}

	static {
		for (String string : GlobalParams.getSubjectsFiles()) {
			subjectsDataSets.put(string, ReadCSV.loadTextFileToHashMap(GlobalParams.getSubjectsDir() + string));
		}
		for (String string : GlobalParams.getIndustriesFiles()) {
			industriesDataSets.put(string, ReadCSV.loadTextFileToHashMap(GlobalParams.getIndustriesDir() + string));
		}
	}
	private static void debug(String msg) {
		SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss SSS");
		System.out.println(msg + format.format(new Date()));
	}
	public static HashMap<String, HashMap<String, Integer>> getSubjectsDataSets() {
//		return subjectsDataSets;
		debug("getSubjectsDataSets started....");
		for (String string : GlobalParams.getSubjectsFiles())
			subjectsDataSets.put(string, ReadCSV.loadTextFileToHashMap(GlobalParams.getSubjectsDir() + string));
		debug("getSubjectsDataSets finished....");
		return subjectsDataSets;
	}

	public static HashMap<String, HashMap<String, Integer>> getIndustriesDataSets() {
//		return industriesDataSets;
		debug("getIndustriesDataSets started....");
		for (String string : GlobalParams.getIndustriesFiles())
			industriesDataSets.put(string, ReadCSV.loadTextFileToHashMap(GlobalParams.getIndustriesDir() + string));
		debug("getIndustriesDataSets finished....");
		return industriesDataSets;
	}

	// private static HashMap<String, String[]> subjectsDataSets = new
	// HashMap<String, String[]>();
	// private static HashMap<String, String[]> industriesDataSets = new
	// HashMap<String, String[]>();
	//
	// private GlobalWordSets() {
	// }
	//
	// static {
	// for (String string : GlobalParams.getSubjectsFiles()) {
	// subjectsDataSets.put(string,
	// ReadCSV.loadCSVFile(GlobalParams.getSubjectsDir() + string));
	// }
	// for (String string : GlobalParams.getIndustriesFiles()) {
	// industriesDataSets.put(string,
	// ReadCSV.loadCSVFile(GlobalParams.getIndustriesDir() + string));
	// }
	// }
	//
	// public static HashMap<String, String[]> getSubjectsDataSets() {
	// return subjectsDataSets;
	// }
	//
	// public static HashMap<String, String[]> getIndustriesDataSets() {
	// return industriesDataSets;
	// }

}
