package com.ligadata.datasets.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadCSV {

	public static String[] loadCSVFile(String path) {

		String csvFile = path;
		BufferedReader br = null;
		String line = "";
		ArrayList<String> wordSet = new ArrayList<String>();

		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				if (!(line.length() < 1))
					wordSet.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return wordSet.toArray(new String[wordSet.size()]);
	}

}