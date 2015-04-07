package com.ligadata.twitterfeeds.zookeeperclient;

public class Main {

	public static void main(String[] args) throws Exception {
		Client c = new Client();
		if(!c.hashTagExists("#Obama")){
			c.createHashTag("#Obama", "true");
		}
		System.out.println(c.getHashTag("#Obama"));
		c.close();

	}

}
