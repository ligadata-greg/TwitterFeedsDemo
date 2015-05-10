package com.ligadata.twitterfeeds.kafkaconsumer;

public class Main implements Runnable{
	public static void mainFunc() {

		String zooKeeper = "fatafat.ligadata.com:2181";
		String groupId = "group1";
		String topic = "testout_1";

		int threads = 1;

		KafkaClient example = new KafkaClient(zooKeeper, groupId, topic);

		example.run(threads);
		//System.out.println(example.run(threads));
		
		try {
			Thread.sleep(900);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		example.shutdown();

	}

	public void run() {
		// TODO Auto-generated method stub
		mainFunc();
	}
}
