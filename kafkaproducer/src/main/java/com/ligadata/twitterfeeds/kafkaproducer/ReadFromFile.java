package com.ligadata.twitterfeeds.kafkaproducer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFile implements Runnable {
	private BufferedReader br;
	private StringBuilder sb;
	private String line;
	private KafkaProducer producer;
	
	public ReadFromFile(String filePath) {
		try {
			br = new BufferedReader(new FileReader(filePath));
			line = br.readLine();
			producer = new KafkaProducer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (GlobalParamaters.isProducerActive()) {
			if (line != null) {
				try {
					if(line.startsWith("System.twittermsg")){
						sb = new StringBuilder();
						sb.append(line);
					}else{
						sb.append(line);
					}
					producer.send(sb.toString());
					System.out.println(sb.toString());
					line = br.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
