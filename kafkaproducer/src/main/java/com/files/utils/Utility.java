package com.files.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Utility implements Runnable{
	
	private Vector<String> textList = null;
	private String fileName = null;
	
	public Utility(Vector<String> textList, String fileName) {
		super();
		this.textList = (Vector<String>) textList;
		this.fileName = fileName;
	}

	protected void writeToFile(Vector<String> textList, String fileName)
			throws IOException {
		// Assume default encoding.
		FileWriter fileWriter = null;
		// Always wrap FileWriter in BufferedWriter.
		BufferedWriter bufferedWriter = null;
		fileWriter = new FileWriter(fileName);
		bufferedWriter = new BufferedWriter(fileWriter);
		for (String string : textList) {
			bufferedWriter.write(string);
			bufferedWriter.newLine();
		}
		bufferedWriter.close();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			writeToFile(this.textList, this.fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
