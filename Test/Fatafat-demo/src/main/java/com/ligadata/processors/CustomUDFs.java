package com.ligadata.processors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomUDFs {
	public static Integer[] getTokenizedCounts(String tweet, String[] wordSet) {
		int count = 0;
		Integer[] ret = new Integer[wordSet.length];
		for (int i = 0; i < wordSet.length; i++) {
			try{
			count = 0;
			Pattern pattern = Pattern.compile(wordSet[i].toLowerCase());
			Matcher matcher = pattern.matcher(tweet.toLowerCase());
			while (matcher.find())
				count++;
			ret[i] = count;
			}catch(Exception e){
				
			}
		}
		return ret;
	}

	public static boolean getTokenizedBoolean(String tweet, String[] wordSet) {
		for (int i = 0; i < wordSet.length; i++) {
			if (tweet.matches("(.*)" + wordSet[i] + "(.*)"))
				return true;
		}
		return false;
	}

}
