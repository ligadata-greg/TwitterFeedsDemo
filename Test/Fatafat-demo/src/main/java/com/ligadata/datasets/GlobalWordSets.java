package com.ligadata.datasets;

import java.util.HashMap;

import com.ligadata.datasets.utils.ReadCSV;

public final class GlobalWordSets {

	private static HashMap<String, String[]> subjectsDataSets = new HashMap<String, String[]>();
	private static HashMap<String, String[]> industriesDataSets = new HashMap<String, String[]>();
	private final static String rootDirectory = "D:\\Fatafat\\sample datasets\\";
	private GlobalWordSets() {
	}

	static {
		String[] subjectsFiles = {"investment", "fraud"};
		String[] indusriesFiles = {"topBanks", "Apple"};
		for (String string : subjectsFiles) {
			subjectsDataSets.put(string, ReadCSV.loadCSVFile(rootDirectory + string + ".csv"));
		}
		for (String string : indusriesFiles) {
			industriesDataSets.put(string, ReadCSV.loadCSVFile(rootDirectory + string + ".csv"));
		}
	}

	public static HashMap<String, String[]> getSubjectsDataSets() {
		return subjectsDataSets;
	}

	public static HashMap<String, String[]> getIndustriesDataSets() {
		return industriesDataSets;
	}

}
