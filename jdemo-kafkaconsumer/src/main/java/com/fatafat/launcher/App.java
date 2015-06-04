package com.fatafat.launcher;

import java.util.LinkedHashMap;

import com.fatafat.counters.CountersRefresher;
import com.fatafat.influx.consumer.CountersService;

public class App {

	public static void main(String[] args) {

		if (args.length != 0) {

			int tw = Integer.parseInt(args[0]);
			CountersService c = new CountersService();
//			Thread t1 = new Thread(c);
//			t1.start();
			 c.run();

			CountersRefresher c2 = new CountersRefresher(tw);
			c2.run();
		}
		else
			System.out.println("pass time window parameter in seconds please.");
		
		
		// System.out.println(GlobalWordSets.getIndustriesDataSets());
		// TODO Auto-generated method stub
		// String zooKeeper = "localhost:2181";
		// String groupId = "group1";
		// String topic = "test";
		//
		// int threads = 1;
		//
		// KafkaConsumer example = new KafkaConsumer(zooKeeper, groupId, topic);
		//
		// example.run(threads);
		// //System.out.println(example.run(threads));
		//
		// try {
		// Thread.sleep(900);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		//
		// example.shutdown();

	}

}
