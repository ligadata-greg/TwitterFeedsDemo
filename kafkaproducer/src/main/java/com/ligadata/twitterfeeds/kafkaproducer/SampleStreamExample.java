/**
 * Copyright 2013 Twitter, Inc.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/

package com.ligadata.twitterfeeds.kafkaproducer;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;

import com.files.utils.Utility;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.BasicClient;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

//import com.ligadata.twitterfeeds.zookeeperclient.Client;

public class SampleStreamExample {

	public static void run(String consumerKey, String consumerSecret,
			String token, String secret) throws InterruptedException {
		// Create an appropriately sized blocking queue
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10000);

		final String COMMA_DELIMITER = ",";
		String originalTweet = null;
		String filteredTweet = null;
		// Client zooKeeperClient = new Client();
		Date date = null;
		String DateToStr;
		Vector<String> tweetsList = new Vector<String>();
		Vector<String> tweetsListCopy = null;
		// int counter = 0;

		// Define our endpoint: By default, delimited=length is set (we need
		// this for our processor)
		// and stall warnings are on.
		StatusesSampleEndpoint endpoint = new StatusesSampleEndpoint();
		List<String> langList = new ArrayList<String>();
		langList.add("en"); 
		endpoint.languages(langList);
		endpoint.stallWarnings(false);

		Authentication auth = new OAuth1(consumerKey, consumerSecret, token,
				secret);
		// Authentication auth = new
		// com.twitter.hbc.httpclient.auth.BasicAuth(username, password);

		// Create a new BasicClient. By default gzip is enabled.
		BasicClient client = new ClientBuilder().name("sampleExampleClient")
				.hosts(Constants.STREAM_HOST).endpoint(endpoint)
				.authentication(auth)
				.processor(new StringDelimitedProcessor(queue)).build();

		// Establish a connection
		client.connect();

		// Do whatever needs to be done with messages
		// for (int msgRead = 0; msgRead < 1000; msgRead++) {
		JSONObject json;
		JSONObject paramsJSON;
		KafkaProducer producer = new KafkaProducer();
		String fileName = null;
		Thread thread = null;
		int count = 0;
		
		while (true) {
			if (client.isDone()) {
				System.out.println("Client connection closed unexpectedly: "
						+ client.getExitEvent().getMessage());
				break;
			}

			String msg = queue.poll(5, TimeUnit.SECONDS);
			String param = null;
			if (msg == null) {
				System.out.println("Did not receive a message in 5 seconds");
			} else {
				StringBuffer str = new StringBuffer();
				try {
					// param = zooKeeperClient.getParam().toString();
					// paramsJSON = new JSONObject(param);
					// System.out.println(">>> zooKeeperData: :" + param +
					// " <<<");
					json = new JSONObject(msg);
					if (json.has("text")) {
						str.append("System.twittermsg");
						// str.append("System.PlusStringStringTestMsg");
						str.append(COMMA_DELIMITER);
						if (json.has("id")) {
							str.append(json.get("id"));
						} else {
							str.append("0");
						}
						str.append(COMMA_DELIMITER);
						if (json.has("text")) {
							filteredTweet = json.get("text").toString()
									.replace(",", " ").replace("\n", " ");
							originalTweet = json.get("text").toString();
							str.append(filteredTweet);
						} else {
							str.append("empty");
						}

						date = new Date();
						DateToStr = DateFormat.getTimeInstance(
								DateFormat.MEDIUM).format(date);
						str.append(COMMA_DELIMITER);
						str.append(DateToStr);
						tweetsList.add(str.toString());
						System.out.println(str.toString());
						
						if(tweetsList.size() < 1000)
							tweetsList.add(str.toString());
						else{
							
							count++;
							fileName = "D:\\Fatafat\\data110515\\temp_" + count + ".txt";
//							Utility.writeToFile(tweetsList, fileName);
							tweetsListCopy = (Vector<String>) tweetsList.clone();
							Utility util = new Utility(tweetsListCopy, fileName);
							thread = new Thread(util);
							thread.start();
							tweetsList.clear();
							tweetsList.add(str.toString());
							
						}
					}
					// producer.send(str.toString());

				} catch (JSONException e) {
					e.printStackTrace();
					continue;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println(filteredTweet+"***" + originalTweet);
				
				// System.out.println(counter++);
			}
		}

		System.out.println("closing clients...");
		producer.close();
		client.stop();

		// Print some stats
		System.out.printf("The client read %d messages!\n", client
				.getStatsTracker().getNumMessages());
	}
}
