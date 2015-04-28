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

import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONException;
import org.json.JSONObject;
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
		System.out.println("hashtags size: " + list.size());
		System.out.println("hashtags list: " + list);
		endpoint.trackTerms(list);

		Authentication auth = new OAuth1(consumerKey, consumerSecret, token,
				secret);
		// Authentication auth = new BasicAuth(username, password);

		// Create a new BasicClient. By default gzip is enabled.
		final Client client = new ClientBuilder().hosts(Constants.STREAM_HOST)
				.endpoint(endpoint).authentication(auth)
				.processor(new StringDelimitedProcessor(queue)).build();

		// Establish a connection
		client.connect();

		final KafkaProducer producer = new KafkaProducer();

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
			while (true) {
				JSONObject json;
				String msg = null;
				msg = queue.take();

				if (msg == null) {
					System.out
							.println("Did not receive a message in 5 seconds");
				} else {
					StringBuffer str = new StringBuffer();
					try {

						json = new JSONObject(msg);
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
									.replace(",", " "));
						} else {
							str.append("empty");
						}
						date = new Date();
						DateToStr = DateFormat.getTimeInstance(DateFormat.MEDIUM).format(date);
						str.append(COMMA_DELIMITER);
						str.append(DateToStr);

						 producer.send(str.toString());

					} catch (JSONException e) {
						e.printStackTrace();
						continue;
					}

					System.out.println(str.toString());
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
