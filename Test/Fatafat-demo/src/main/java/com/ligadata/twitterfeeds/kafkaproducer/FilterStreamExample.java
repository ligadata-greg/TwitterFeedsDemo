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

import org.apache.commons.lang.time.StopWatch;

import com.ligadata.demo.TweetAnalysis;
import com.ligadata.parameters.GlobalParams;
import com.ligadata.parameters.MatchingParameters;
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
import java.util.PriorityQueue;
import java.util.Queue;
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
	private String[] csvHashtags;

	public FilterStreamExample(String consumerKey, String consumerSecret,
			String token, String secret, String[] csvHashtags) {
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
		TweetAnalysis tweet = null;
		StopWatch stopWatch = new StopWatch();
		JSONObject json = null;
		Date date = null;
		String DateToStr;
		int seconds = 0;
		// add some track terms
		// endpoint.trackTerms(Lists.newArrayList("twitterapi", hashtag));
		List<String> list = Arrays.asList(csvHashtags);
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

		/**
		 * "barclays gakydgjdf udhfasdfiasdfds." "apple phones are shitty."
		 * "i wanna change my credit card credentials."
		 * "Absolute Return will be double my money with chase bank."
		 * */
		// Queue queue = new PriorityQueue();
		// queue.add("barclays gakydgjdf udhfasdfiasdfds.");
		// queue.add("apple phones are shitty.");
		// queue.add("i wanna change my credit card credentials.");
		// queue.add("Absolute Return will be double my money with chase bank.");

		try {
			stopWatch.start();
			while (GlobalParams.isProducerActived()) {
				seconds = (int) (stopWatch.getTime() / 1000);
				if (seconds >= MatchingParameters.getRefreshMatrixAfter()) {
					stopWatch.reset();
					stopWatch.start();
					MatchingParameters.refreshAlerts();
					MatchingParameters.resetSubjectsByIndustry();
				}

				String msg = null;
				msg = queue.take();
				// msg = (String) queue.poll();
				if (msg == null) {
					System.out
							.println("Did not receive a message in 5 seconds");
				} else {
					json = new JSONObject(msg);
					if (json.has("text")) {
						// System.out.println(json.get("text").toString());
						tweet = new TweetAnalysis(json.get("text").toString());
						MatchingParameters.incCounters(tweet);

						 System.out.println("Processed: "
						 + MatchingParameters.getTotalProcessedTweets());
						 System.out.println("Matched:"
						 + MatchingParameters.getTotalMatchedTweets());
						 System.out.println("Cummulative alerts over threshold: "
						 + MatchingParameters.getCummulativeNumOfAlertsOverThreshold());
						 System.out.println("Matrix: "
						 + MatchingParameters.getCummulativeResult());
						 System.out.println("Alerts: " + MatchingParameters.getAlerts());
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
