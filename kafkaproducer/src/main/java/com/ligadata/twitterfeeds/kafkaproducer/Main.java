package com.ligadata.twitterfeeds.kafkaproducer;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		if (args.length > 0) {
			List<String> list = Arrays.asList(args[0].split(","));

			for (String filepath : list) {
				ReadFromFile f = new ReadFromFile(filepath);
				Thread t = new Thread(f);
				t.start();
			}
			
			// FilterStreamExample f = new FilterStreamExample(
			// "JET4XHa2TpAdcpSKY1r6gn47Y",
			// "u0EDXjhzUeRECuV2PiPFgJgEpdZUUD79eGGcXEHmXdiHG5sYr7",
			// "334088612-wYvtA22ZnLkhNgsXheM8FJMU8Zu3guuraiEgQbiX",
			// "cM4lMELZxP0xpX2SY7tNDD7JMwkEdwc4e5ELg3Tx5HiiB",args[0]);//
			// "apple"
		} else {
			throw new IllegalArgumentException();
		}
		// new SampleStreamExample().run("JET4XHa2TpAdcpSKY1r6gn47Y",
		// "u0EDXjhzUeRECuV2PiPFgJgEpdZUUD79eGGcXEHmXdiHG5sYr7",
		// "334088612-wYvtA22ZnLkhNgsXheM8FJMU8Zu3guuraiEgQbiX",
		// "cM4lMELZxP0xpX2SY7tNDD7JMwkEdwc4e5ELg3Tx5HiiB");

	}

}
