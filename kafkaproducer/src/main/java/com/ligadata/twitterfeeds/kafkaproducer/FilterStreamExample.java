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

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class FilterStreamExample implements Runnable {

	private static final String COMMA_DELIMITER = ",";
	private String consumerSecret;
	private String consumerKey; 
	private String token; 
	private String secret; 
	private String hashtag;
	
	public FilterStreamExample(String consumerKey, String consumerSecret,
			String token, String secret, String hashtag)
	{
		this.consumerSecret=consumerSecret;
		this.consumerKey=consumerKey;
		this.token=token;
		this.secret=secret;
		this.hashtag=hashtag;
	}
	
	public void run(){
		final BlockingQueue<String> queue = new LinkedBlockingQueue<String>(
				10000);
		StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH-mm-ss");
		Date date = new Date();

		// add some track terms
		endpoint.trackTerms(Lists.newArrayList("twitterapi", hashtag));

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

			@SuppressWarnings("static-access")
				com.ligadata.twitterfeeds.zookeeperclient.Client c = new com.ligadata.twitterfeeds.zookeeperclient.Client();
				try {
					
					if(!c.hashTagExists(hashtag)){
						c.createHashTag(hashtag, "true");
					}else{
						c.setHashTag(hashtag, "true");
					}
					
					while (c.getHashTag(hashtag).equals("true")) {
						JSONObject json;
					    String msg = null;
						msg = queue.take();

						if (msg == null) {
							System.out.println("Did not receive a message in 5 seconds");
						} else {
							StringBuffer str = new StringBuffer();
							try {

								json = new JSONObject(msg);
								str.append("System.PlusStringStringTestMsg");
								str.append(COMMA_DELIMITER);
								if (json.has("id")) {
									str.append(json.get("id"));
								} else {
									str.append("0");
								}
								str.append(COMMA_DELIMITER);
								if (json.has("text")) {
									str.append(json.get("text"));
								} else {
									str.append("empty");
								}

								producer.send(str.toString());

							} catch (JSONException e) {
								e.printStackTrace();
								continue;
							}

							System.out.println(str.toString());
						}

					}
					c.close();
					client.stop();
					producer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
	}
}
