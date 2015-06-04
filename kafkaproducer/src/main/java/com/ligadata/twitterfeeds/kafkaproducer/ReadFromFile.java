package com.ligadata.twitterfeeds.kafkaproducer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile implements Runnable {
	private BufferedReader br;
	private StringBuilder sb;
	private String line;
	private String path;
	private KafkaProducer producer;
//	private int counter = 0;
	
	public ReadFromFile(String filePath) {
		try {
			path = filePath;
			br = new BufferedReader(new FileReader(filePath));
			line = br.readLine();
			producer = new KafkaProducer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (GlobalParamaters.isProducerActive()) {
//	    while (true) {
			if (line != null) {
				try {
//					if(line.startsWith("System.twittermsg")){
//						sb = new StringBuilder();
//						sb.append(line);
//					}else{
//						sb.append(line);
//					}
					producer.send(sb.toString().trim());
					line = br.readLine();
//					System.out.println(line);
//					counter ++;
//					if(counter > 10000){
//						counter =0;
//						Thread.sleep(50000);
//					}
				} catch (IOException e) {
					e.printStackTrace();
				}
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}else{
				System.out.println("end of " + path);
				try {
					Thread.sleep(15000);
					br = new BufferedReader(new FileReader(path));
					line = br.readLine();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
