package com.ligadata.twitterfeeds.zookeeperclient;

public class Main {

	public static void main(String[] args) throws Exception {
		Client c = new Client();
//		c.setAction("true");
//		c.setData("this is a sample input data");
//		if(!c.hashTagExists("#Obama")){
//			c.createHashTag("#Obama", "true");
//		}
		System.out.println(c.getData());
		c.close();

	}

}
