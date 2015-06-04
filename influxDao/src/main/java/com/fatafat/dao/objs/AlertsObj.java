package com.fatafat.dao.objs;

import java.util.List;

import org.influxdb.InfluxDB;

import com.fatafat.dao.impl.GenericDao;

public class AlertsObj extends GenericObj {

	private String time;
	private String sequanceNumber;
	private String username;
	private String tweetText;
	private int retweetCount;
	private double nps;
	private int favoriteCount;
	private String followersCount;
	private int timeWindow;
	private int totalMatchedTweets;
	private int totalProcessedTweets;
	
	private String s_barclays_positive;
	private String s_barclays_negative;
	private String s_citibank_positive;
	private String s_citibank_negative;
	private String s_bofa_positive;
	private String s_bofa_negative;
	private String s_royalbankofscotland_positive;
	private String s_royalbankofscotland_negative;
	private String s_hsbc_positive;
	private String s_hsbc_negative;
	private String s_jpm_positive;
	private String s_jpm_negative;
	
	private String t_barclays_positive;
	private String t_barclays_negative;
	private String t_citibank_positive;
	private String t_citibank_negative;
	private String t_bofa_positive;
	private String t_bofa_negative;
	private String t_royalbankofscotland_positive;
	private String t_royalbankofscotland_negative;
	private String t_hsbc_positive;
	private String t_hsbc_negative;
	private String t_jpm_positive;
	private String t_jpm_negative;
	
	private String a_barclays_positive;
	private String a_barclays_negative;
	private String a_citibank_positive;
	private String a_citibank_negative;
	private String a_bofa_positive;
	private String a_bofa_negative;
	private String a_royalbankofscotland_positive;
	private String a_royalbankofscotland_negative;
	private String a_hsbc_positive;
	private String a_hsbc_negative;
	private String a_jpm_positive;
	private String a_jpm_negative;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSequanceNumber() {
		return sequanceNumber;
	}
	public void setSequanceNumber(String sequanceNumber) {
		this.sequanceNumber = sequanceNumber;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTweetText() {
		return tweetText;
	}
	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}
	public int getRetweetCount() {
		return retweetCount;
	}
	public void setRetweetCount(int retweetCount) {
		this.retweetCount = retweetCount;
	}
	public double getNps() {
		return nps;
	}
	public void setNps(double nps) {
		this.nps = nps;
	}
	public int getFavoriteCount() {
		return favoriteCount;
	}
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	public String getFollowersCount() {
		return followersCount;
	}
	public void setFollowersCount(String followersCount) {
		this.followersCount = followersCount;
	}
	public int getTimeWindow() {
		return timeWindow;
	}
	public void setTimeWindow(int timeWindow) {
		this.timeWindow = timeWindow;
	}
	public int getTotalMatchedTweets() {
		return totalMatchedTweets;
	}
	public void setTotalMatchedTweets(int totalMatchedTweets) {
		this.totalMatchedTweets = totalMatchedTweets;
	}
	public int getTotalProcessedTweets() {
		return totalProcessedTweets;
	}
	public void setTotalProcessedTweets(int totalProcessedTweets) {
		this.totalProcessedTweets = totalProcessedTweets;
	}
	public String getS_barclays_positive() {
		return s_barclays_positive;
	}
	public void setS_barclays_positive(String s_barclays_positive) {
		this.s_barclays_positive = s_barclays_positive;
	}
	public String getS_barclays_negative() {
		return s_barclays_negative;
	}
	public void setS_barclays_negative(String s_barclays_negative) {
		this.s_barclays_negative = s_barclays_negative;
	}
	public String getS_citibank_positive() {
		return s_citibank_positive;
	}
	public void setS_citibank_positive(String s_citibank_positive) {
		this.s_citibank_positive = s_citibank_positive;
	}
	public String getS_citibank_negative() {
		return s_citibank_negative;
	}
	public void setS_citibank_negative(String s_citibank_negative) {
		this.s_citibank_negative = s_citibank_negative;
	}
	public String getS_bofa_positive() {
		return s_bofa_positive;
	}
	public void setS_bofa_positive(String s_bofa_positive) {
		this.s_bofa_positive = s_bofa_positive;
	}
	public String getS_bofa_negative() {
		return s_bofa_negative;
	}
	public void setS_bofa_negative(String s_bofa_negative) {
		this.s_bofa_negative = s_bofa_negative;
	}
	public String getS_royalbankofscotland_positive() {
		return s_royalbankofscotland_positive;
	}
	public void setS_royalbankofscotland_positive(
			String s_royalbankofscotland_positive) {
		this.s_royalbankofscotland_positive = s_royalbankofscotland_positive;
	}
	public String getS_royalbankofscotland_negative() {
		return s_royalbankofscotland_negative;
	}
	public void setS_royalbankofscotland_negative(
			String s_royalbankofscotland_negative) {
		this.s_royalbankofscotland_negative = s_royalbankofscotland_negative;
	}
	public String getS_hsbc_positive() {
		return s_hsbc_positive;
	}
	public void setS_hsbc_positive(String s_hsbc_positive) {
		this.s_hsbc_positive = s_hsbc_positive;
	}
	public String getS_hsbc_negative() {
		return s_hsbc_negative;
	}
	public void setS_hsbc_negative(String s_hsbc_negative) {
		this.s_hsbc_negative = s_hsbc_negative;
	}
	public String getS_jpm_positive() {
		return s_jpm_positive;
	}
	public void setS_jpm_positive(String s_jpm_positive) {
		this.s_jpm_positive = s_jpm_positive;
	}
	public String getS_jpm_negative() {
		return s_jpm_negative;
	}
	public void setS_jpm_negative(String s_jpm_negative) {
		this.s_jpm_negative = s_jpm_negative;
	}
	public String getT_barclays_positive() {
		return t_barclays_positive;
	}
	public void setT_barclays_positive(String t_barclays_positive) {
		this.t_barclays_positive = t_barclays_positive;
	}
	public String getT_barclays_negative() {
		return t_barclays_negative;
	}
	public void setT_barclays_negative(String t_barclays_negative) {
		this.t_barclays_negative = t_barclays_negative;
	}
	public String getT_citibank_positive() {
		return t_citibank_positive;
	}
	public void setT_citibank_positive(String t_citibank_positive) {
		this.t_citibank_positive = t_citibank_positive;
	}
	public String getT_citibank_negative() {
		return t_citibank_negative;
	}
	public void setT_citibank_negative(String t_citibank_negative) {
		this.t_citibank_negative = t_citibank_negative;
	}
	public String getT_bofa_positive() {
		return t_bofa_positive;
	}
	public void setT_bofa_positive(String t_bofa_positive) {
		this.t_bofa_positive = t_bofa_positive;
	}
	public String getT_bofa_negative() {
		return t_bofa_negative;
	}
	public void setT_bofa_negative(String t_bofa_negative) {
		this.t_bofa_negative = t_bofa_negative;
	}
	public String getT_royalbankofscotland_positive() {
		return t_royalbankofscotland_positive;
	}
	public void setT_royalbankofscotland_positive(
			String t_royalbankofscotland_positive) {
		this.t_royalbankofscotland_positive = t_royalbankofscotland_positive;
	}
	public String getT_royalbankofscotland_negative() {
		return t_royalbankofscotland_negative;
	}
	public void setT_royalbankofscotland_negative(
			String t_royalbankofscotland_negative) {
		this.t_royalbankofscotland_negative = t_royalbankofscotland_negative;
	}
	public String getT_hsbc_positive() {
		return t_hsbc_positive;
	}
	public void setT_hsbc_positive(String t_hsbc_positive) {
		this.t_hsbc_positive = t_hsbc_positive;
	}
	public String getT_hsbc_negative() {
		return t_hsbc_negative;
	}
	public void setT_hsbc_negative(String t_hsbc_negative) {
		this.t_hsbc_negative = t_hsbc_negative;
	}
	public String getT_jpm_positive() {
		return t_jpm_positive;
	}
	public void setT_jpm_positive(String t_jpm_positive) {
		this.t_jpm_positive = t_jpm_positive;
	}
	public String getT_jpm_negative() {
		return t_jpm_negative;
	}
	public void setT_jpm_negative(String t_jpm_negative) {
		this.t_jpm_negative = t_jpm_negative;
	}
	public String getA_barclays_positive() {
		return a_barclays_positive;
	}
	public void setA_barclays_positive(String a_barclays_positive) {
		this.a_barclays_positive = a_barclays_positive;
	}
	public String getA_barclays_negative() {
		return a_barclays_negative;
	}
	public void setA_barclays_negative(String a_barclays_negative) {
		this.a_barclays_negative = a_barclays_negative;
	}
	public String getA_citibank_positive() {
		return a_citibank_positive;
	}
	public void setA_citibank_positive(String a_citibank_positive) {
		this.a_citibank_positive = a_citibank_positive;
	}
	public String getA_citibank_negative() {
		return a_citibank_negative;
	}
	public void setA_citibank_negative(String a_citibank_negative) {
		this.a_citibank_negative = a_citibank_negative;
	}
	public String getA_bofa_positive() {
		return a_bofa_positive;
	}
	public void setA_bofa_positive(String a_bofa_positive) {
		this.a_bofa_positive = a_bofa_positive;
	}
	public String getA_bofa_negative() {
		return a_bofa_negative;
	}
	public void setA_bofa_negative(String a_bofa_negative) {
		this.a_bofa_negative = a_bofa_negative;
	}
	public String getA_royalbankofscotland_positive() {
		return a_royalbankofscotland_positive;
	}
	public void setA_royalbankofscotland_positive(
			String a_royalbankofscotland_positive) {
		this.a_royalbankofscotland_positive = a_royalbankofscotland_positive;
	}
	public String getA_royalbankofscotland_negative() {
		return a_royalbankofscotland_negative;
	}
	public void setA_royalbankofscotland_negative(
			String a_royalbankofscotland_negative) {
		this.a_royalbankofscotland_negative = a_royalbankofscotland_negative;
	}
	public String getA_hsbc_positive() {
		return a_hsbc_positive;
	}
	public void setA_hsbc_positive(String a_hsbc_positive) {
		this.a_hsbc_positive = a_hsbc_positive;
	}
	public String getA_hsbc_negative() {
		return a_hsbc_negative;
	}
	public void setA_hsbc_negative(String a_hsbc_negative) {
		this.a_hsbc_negative = a_hsbc_negative;
	}
	public String getA_jpm_positive() {
		return a_jpm_positive;
	}
	public void setA_jpm_positive(String a_jpm_positive) {
		this.a_jpm_positive = a_jpm_positive;
	}
	public String getA_jpm_negative() {
		return a_jpm_negative;
	}
	public void setA_jpm_negative(String a_jpm_negative) {
		this.a_jpm_negative = a_jpm_negative;
	}
	@Override
	public String toString() {
		return "AlertsObj [time=" + time + ", sequanceNumber=" + sequanceNumber
				+ ", username=" + username + ", tweetText=" + tweetText
				+ ", retweetCount=" + retweetCount + ", nps=" + nps
				+ ", favoriteCount=" + favoriteCount + ", followersCount="
				+ followersCount + ", timeWindow=" + timeWindow
				+ ", totalMatchedTweets=" + totalMatchedTweets
				+ ", totalProcessedTweets=" + totalProcessedTweets
				+ ", s_barclays_positive=" + s_barclays_positive
				+ ", s_barclays_negative=" + s_barclays_negative
				+ ", s_citibank_positive=" + s_citibank_positive
				+ ", s_citibank_negative=" + s_citibank_negative
				+ ", s_bofa_positive=" + s_bofa_positive + ", s_bofa_negative="
				+ s_bofa_negative + ", s_royalbankofscotland_positive="
				+ s_royalbankofscotland_positive
				+ ", s_royalbankofscotland_negative="
				+ s_royalbankofscotland_negative + ", s_hsbc_positive="
				+ s_hsbc_positive + ", s_hsbc_negative=" + s_hsbc_negative
				+ ", s_jpm_positive=" + s_jpm_positive + ", s_jpm_negative="
				+ s_jpm_negative + ", t_barclays_positive="
				+ t_barclays_positive + ", t_barclays_negative="
				+ t_barclays_negative + ", t_citibank_positive="
				+ t_citibank_positive + ", t_citibank_negative="
				+ t_citibank_negative + ", t_bofa_positive=" + t_bofa_positive
				+ ", t_bofa_negative=" + t_bofa_negative
				+ ", t_royalbankofscotland_positive="
				+ t_royalbankofscotland_positive
				+ ", t_royalbankofscotland_negative="
				+ t_royalbankofscotland_negative + ", t_hsbc_positive="
				+ t_hsbc_positive + ", t_hsbc_negative=" + t_hsbc_negative
				+ ", t_jpm_positive=" + t_jpm_positive + ", t_jpm_negative="
				+ t_jpm_negative + ", a_barclays_positive="
				+ a_barclays_positive + ", a_barclays_negative="
				+ a_barclays_negative + ", a_citibank_positive="
				+ a_citibank_positive + ", a_citibank_negative="
				+ a_citibank_negative + ", a_bofa_positive=" + a_bofa_positive
				+ ", a_bofa_negative=" + a_bofa_negative
				+ ", a_royalbankofscotland_positive="
				+ a_royalbankofscotland_positive
				+ ", a_royalbankofscotland_negative="
				+ a_royalbankofscotland_negative + ", a_hsbc_positive="
				+ a_hsbc_positive + ", a_hsbc_negative=" + a_hsbc_negative
				+ ", a_jpm_positive=" + a_jpm_positive + ", a_jpm_negative="
				+ a_jpm_negative + "]";
	}
}
