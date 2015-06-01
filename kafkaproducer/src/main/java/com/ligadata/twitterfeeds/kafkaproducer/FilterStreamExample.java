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

import com.files.utils.Utility;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import twitter4j.api.FavoritesResources;

import java.text.DateFormat;

public class FilterStreamExample implements Runnable {

	private static final String COMMA_DELIMITER = ",";
	private String consumerSecret;
	private String consumerKey;
	private String token;
	private String secret;
	private String csvHashtags;

	public FilterStreamExample(String consumerKey, String consumerSecret,
			String token, String secret, String csvHashtags) {
		this.consumerSecret = consumerSecret;
		this.consumerKey = consumerKey;
		this.token = token;
		this.secret = secret;
		this.csvHashtags = csvHashtags;
	}

	public void run() {
		final BlockingQueue<String> queue = new LinkedBlockingQueue<String>(
				10000);
		StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
		Date date = null;
		String DateToStr;
		// add some track terms
		// endpoint.trackTerms(Lists.newArrayList("twitterapi", hashtag));
		List<String> list = Arrays.asList(csvHashtags.split(","));
		List<String> langList = new ArrayList<String>();
		langList.add("en");
		System.out.println("hashtags size: " + list.size());
		System.out.println("hashtags list: " + list);
		endpoint.trackTerms(list);
		endpoint.languages(langList);
		Authentication auth = new OAuth1(consumerKey, consumerSecret, token,
				secret);
		// Authentication auth = new BasicAuth(username, password);

		// Create a new BasicClient. By default gzip is enabled.
		final Client client = new ClientBuilder().hosts(Constants.STREAM_HOST)
				.endpoint(endpoint).authentication(auth)
				.processor(new StringDelimitedProcessor(queue)).build();

		// Establish a connection
		client.connect();

		// final KafkaProducer producer = new KafkaProducer();

		// @SuppressWarnings("static-access")
		// com.ligadata.twitterfeeds.zookeeperclient.Client c = new
		// com.ligadata.twitterfeeds.zookeeperclient.Client();
		try {

			// if(!c.hashTagExists(hashtag)){
			// c.createHashTag(hashtag, "true");
			// }else{
			// c.setHashTag(hashtag, "true");
			// }
			//
			// while (c.getHashTag(hashtag).equals("true")) {
			Vector<String> tweetsList = new Vector<String>();
			Vector<String> tweetsListCopy = null;
			JSONObject json, userJson;
			JSONObject paramsJSON;
			KafkaProducer producer = new KafkaProducer();
			String fileName = null;
			int count = 0;
			Thread thread = null;
			String msg = null;
			StringBuffer str = null;
			int numOfReTweet = 0;
			int numbOfFav = 0;
			int numOfFollowers = 0;
			double nps = 0;

			while (true) {

				msg = queue.take();
				if (msg == null) {
					System.out
							.println("Did not receive a message in 5 seconds");
				} else {
					str = new StringBuffer();
					try {

						json = new JSONObject(msg);
						if (json.has("text") && json.has("user")) {
							userJson = json.getJSONObject("user");
							str.append("System.twittermsg");
							// str.append("System.twittermsg");
							str.append(COMMA_DELIMITER);
							if (json.has("id")) {
								str.append(json.get("id"));
							} else {
								str.append("0");
							}
							str.append(COMMA_DELIMITER);
							if (json.has("text")) {
								str.append(json.get("text").toString()
										.replace(",", " ").replace("\n", " "));
							} else {
								str.append("empty");
							}
							// ////////////
							str.append(COMMA_DELIMITER);
							if (userJson.has("name"))
								str.append(userJson.getString("name"));
							else
								str.append("");
							str.append(COMMA_DELIMITER);
							if (json.has("retweet_count")) {
								str.append(json.get("retweet_count"));
								numOfReTweet = Integer.parseInt(json
										.get("retweet_count").toString());
							} else {
								str.append("0");
								numOfReTweet = 0;
							}
							str.append(COMMA_DELIMITER);
							if (json.has("favorite_count")
									&& json.getBoolean("favorited")) {
								str.append(json.get("favorite_count"));
								numbOfFav = Integer.parseInt(json.get(
										"favorite_count").toString());
							} else {
								str.append("0");
								numbOfFav = 0;
							}
							str.append(COMMA_DELIMITER);
							if (userJson.has("followers_count")) {
								str.append(userJson.get("followers_count"));
								numOfFollowers = Integer.parseInt(userJson
										.get("followers_count").toString());
							} else {
								str.append("0");
								numOfFollowers = 0;
							}
							str.append(COMMA_DELIMITER);
							nps = numOfReTweet * 1 + numbOfFav * 0.5
									+ numOfFollowers * 0.01;
							str.append(nps);
							// ////////
							str.append(COMMA_DELIMITER);
							date = new Date();
							DateToStr = DateFormat.getTimeInstance(
									DateFormat.MEDIUM).format(date);
							str.append(DateToStr);

							System.out.println(str);
							// producer.send(str.toString());
//							System.out.println(str.toString());
							// System.out.println("file#: " + count
							// +" Processed tweets: " + tweetsList.size());
							if (tweetsList.size() < 10000)
								tweetsList.add(str.toString());
							else {
								count++;
								fileName = "/tmp/tweets/temp_"
										+ count + ".txt";
//								fileName = "D:\\Fatafat\\data110515\\temp_"
//										+ count + ".txt";
								// Utility.writeToFile(tweetsList, fileName);
								tweetsListCopy = (Vector<String>) tweetsList
										.clone();
								Utility util = new Utility(tweetsListCopy,
										fileName);
								thread = new Thread(util);
								thread.start();
								tweetsList.clear();
								tweetsList.add(str.toString());
							}
						}

					} catch (Exception e) {
						e.printStackTrace();
						continue;
					}

					// System.out.println(str.toString());
				}

			}
			// c.close();
			// client.stop();
			// producer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
