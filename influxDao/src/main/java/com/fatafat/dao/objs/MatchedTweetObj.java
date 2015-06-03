package com.fatafat.dao.objs;

public class MatchedTweetObj extends GenericObj{

	private String time;
	private String sequanceNumber;
	private String username;
	private String tweetText;
	private String tweetDateTime;
	private int retweetCount;
	private double nps;
	private int favoriteCount;
	private String followersCount;
	private int citiBank;
	private int positive;
	private int bofa;
	private int royalBankofScotland;
	private int barclays;
	private int negative;
	private int hsbc;
	private int jpm;
	
	
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
	public String getTweetDateTime() {
		return tweetDateTime;
	}
	public void setTweetDateTime(String tweetDateTime) {
		this.tweetDateTime = tweetDateTime;
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
	
	public int getCitiBank() {
		return citiBank;
	}
	public void setCitiBank(int citiBank) {
		this.citiBank = citiBank;
	}
	public int getPositive() {
		return positive;
	}
	public void setPositive(int positive) {
		this.positive = positive;
	}
	public int getBofa() {
		return bofa;
	}
	public void setBofa(int bofa) {
		this.bofa = bofa;
	}
	public int getRoyalBankofScotland() {
		return royalBankofScotland;
	}
	public void setRoyalBankofScotland(int royalBankofScotland) {
		this.royalBankofScotland = royalBankofScotland;
	}
	public int getBarclays() {
		return barclays;
	}
	public void setBarclays(int barclays) {
		this.barclays = barclays;
	}
	public int getNegative() {
		return negative;
	}
	public void setNegative(int negative) {
		this.negative = negative;
	}
	public int getHsbc() {
		return hsbc;
	}
	public void setHsbc(int hsbc) {
		this.hsbc = hsbc;
	}
	public int getJpm() {
		return jpm;
	}
	public void setJpm(int jpm) {
		this.jpm = jpm;
	}
	@Override
	public String toString() {
		return "MatchedTweetObj [time=" + time + ", sequanceNumber="
				+ sequanceNumber + ", username=" + username + ", tweetText="
				+ tweetText + ", tweetDateTime=" + tweetDateTime
				+ ", retweetCount=" + retweetCount + ", nps=" + nps
				+ ", favoriteCount=" + favoriteCount + ", followersCount="
				+ followersCount + ", citiBank=" + citiBank + ", positive="
				+ positive + ", bofa=" + bofa + ", royalBankofScotland="
				+ royalBankofScotland + ", barclays=" + barclays
				+ ", negative=" + negative + ", hsbc=" + hsbc + ", jpm=" + jpm
				+ "]";
	}
}
