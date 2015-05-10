package com.ligadata.twitterfeeds.kafkaproducer;

public class Main implements Runnable{

	String[] args;
	
	public Main(String[] args) {
		super();
		this.args = args;
	}

	public static void mainFunc(String[] args) throws Exception {
		if (args.length > 0) {

			FilterStreamExample f = new FilterStreamExample(
					"JET4XHa2TpAdcpSKY1r6gn47Y",
					"u0EDXjhzUeRECuV2PiPFgJgEpdZUUD79eGGcXEHmXdiHG5sYr7",
					"334088612-wYvtA22ZnLkhNgsXheM8FJMU8Zu3guuraiEgQbiX",
					"cM4lMELZxP0xpX2SY7tNDD7JMwkEdwc4e5ELg3Tx5HiiB",args[0]);// "apple"

			Thread t = new Thread(f);
			t.start();
		} else {
			throw new IllegalArgumentException();
		}
		// new SampleStreamExample().run("JET4XHa2TpAdcpSKY1r6gn47Y",
		// "u0EDXjhzUeRECuV2PiPFgJgEpdZUUD79eGGcXEHmXdiHG5sYr7",
		// "334088612-wYvtA22ZnLkhNgsXheM8FJMU8Zu3guuraiEgQbiX",
		// "cM4lMELZxP0xpX2SY7tNDD7JMwkEdwc4e5ELg3Tx5HiiB");
		//
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			mainFunc(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

}
