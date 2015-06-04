package com.ligadata.fatafat.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Utility {

	public static List<String> readFromFile(String filePath) {
		BufferedReader br = null;
		String line = null;
		List<String> list = new ArrayList<String>();

		try {
			br = new BufferedReader(new FileReader(filePath));
			line = br.readLine();
			while (line != null) {
				if (line.startsWith("System.twittermsg"))
					list.add(line.split(",")[2]);
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static List<String> loadCSVFile(String path) {

		String csvFile = path;
		BufferedReader br = null;
		String line = "";
		List<String> wordSet = new ArrayList<String>();

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
		return wordSet;
	}
}
