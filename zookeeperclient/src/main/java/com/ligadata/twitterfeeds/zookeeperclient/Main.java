package com.ligadata.twitterfeeds.zookeeperclient;

public class Main {

	public static void main(String[] args) throws Exception {
		Client c = new Client();
		c.setAction("false");
		
//		if(!c.hashTagExists("#Obama")){
//			c.createHashTag("#Obama", "true");
//		}
		System.out.println(c.getAction());
		c.close();

	}

}
