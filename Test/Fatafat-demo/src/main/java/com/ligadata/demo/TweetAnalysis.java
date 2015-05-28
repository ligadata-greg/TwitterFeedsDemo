package com.ligadata.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import main.TextFilter;
import com.ligadata.datasets.GlobalWordSets;
import com.ligadata.fatafat.wordsets.WordSets;

public class TweetAnalysis {

	private HashMap<String, Boolean> subMatchingResult = new HashMap<String, Boolean>();
	private HashMap<String, Boolean> indMatchingResult = new HashMap<String, Boolean>();
	private HashMap<String, Integer> numOfIndMatchPerDataSet = new HashMap<String, Integer>();
	private HashMap<String, Integer> numOfSubMatchPerDataSet = new HashMap<String, Integer>();
	private int rowSumSubjects = 0;
	private int colSumIndustries = 0;
	private boolean subjectMatch = false;
	private boolean industryMatch = false;
	private int numOfSubjectMatch = 0;
	private int numOfIndustryMatch = 0;
	private String tweet = null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TweetAnalysis(String tweet) {

		this.tweet = tweet;
		HashMap<String, HashMap<String, Integer>> tempSubDataSets = (HashMap<String, HashMap<String, Integer>>) GlobalWordSets
				.getSubjectsDataSets().clone();
		HashMap<String, HashMap<String, Integer>> tempIndDataSets = (HashMap<String, HashMap<String, Integer>>) GlobalWordSets
				.getIndustriesDataSets().clone();
		Boolean subTemp;
		Boolean indTemp;

		TextFilter indFilter = new TextFilter(this.tweet);
		WordSets indWS = new WordSets(tempIndDataSets);
		indFilter.countMatches(indWS);
		Iterator indIt = indFilter.getSumOfMatchCounts().entrySet().iterator();
		Map.Entry<String, Integer> indPair = null;
		
		while (indIt.hasNext()) {
			indPair = (Map.Entry<String, Integer>) indIt.next();
			if (indPair.getValue() > 0)
				indTemp = true;
			else
				indTemp = false;

			if (indTemp) {
				colSumIndustries++;
				industryMatch = true;
				numOfIndustryMatch += indFilter.getTotalMatchCount();
			}

			indMatchingResult.put((String) indPair.getKey(), indTemp);
			numOfIndMatchPerDataSet.put((String) indPair.getKey(), indPair.getValue());
			indIt.remove();
		}

		TextFilter subFilter = new TextFilter(this.tweet);
		WordSets subWS = new WordSets(tempSubDataSets);
		subFilter.countMatches(subWS);
		Iterator subIt = subFilter.getSumOfMatchCounts().entrySet().iterator();
		Map.Entry<String, Integer> subPair = null;
		while (subIt.hasNext()) {
			subPair = (Map.Entry<String, Integer>) subIt.next();
			if (subPair.getValue() > 0)
				subTemp = true;
			else
				subTemp = false;

			if (subTemp) {
				rowSumSubjects = 1;
				subjectMatch = true;
				numOfSubjectMatch += subFilter.getTotalMatchCount();
			}

			subMatchingResult.put((String) subPair.getKey(), subTemp);
			numOfSubMatchPerDataSet.put((String) subPair.getKey(), subPair.getValue());
			subIt.remove();
		}
	}

	// public TweetAnalysis(String tweet) {
	//
	// this.tweet = tweet;
	// HashMap<String, HashMap<String, Integer>> tempSubDataSets =
	// (HashMap<String, HashMap<String, Integer>>)
	// GlobalWordSets.getSubjectsDataSets().clone();
	// HashMap<String, HashMap<String, Integer>> tempIndDataSets =
	// (HashMap<String, HashMap<String, Integer>>)
	// GlobalWordSets.getIndustriesDataSets().clone();
	// Boolean subTemp;
	// Boolean indTemp;
	// int subCount = 0;
	// int indCount = 0;
	// String[] words;
	//
	// Iterator indIterator = tempIndDataSets.entrySet().iterator();
	// while (indIterator.hasNext()) {
	// Map.Entry pair = (Map.Entry) indIterator.next();
	// words = (String[]) pair.getValue();
	// // System.out.println(words.length);
	// //below methods ill be changed to be casted to (hashmap<string,integer>)
	// indTemp = CustomUDFs.getTokenizedBoolean(this.tweet, (String[])
	// pair.getValue());
	// indCount = getSum(CustomUDFs.getTokenizedCounts(this.tweet, (String[])
	// pair.getValue()));
	//
	// if(indTemp){
	// colSumIndustries++;
	// industryMatch = true;
	// numOfIndustryMatch += indCount;
	// }
	//
	// indMatchingResult.put((String) pair.getKey(), indTemp);
	// numOfIndMatchPerDataSet.put((String) pair.getKey(), indCount);
	// indIterator.remove();
	// }
	//
	// Iterator subIterator = tempSubDataSets.entrySet().iterator();
	// while (subIterator.hasNext()) {
	// Map.Entry pair = (Map.Entry) subIterator.next();
	// words = (String[]) pair.getValue();
	// // System.out.println(words.length);
	// subTemp = CustomUDFs.getTokenizedBoolean(this.tweet, (String[])
	// pair.getValue());
	// subCount = getSum(CustomUDFs.getTokenizedCounts(this.tweet, (String[])
	// pair.getValue()));
	// if(subTemp){
	// rowSumSubjects = 1;
	// subjectMatch = true;
	// numOfSubjectMatch += subCount;
	// }
	//
	// subMatchingResult.put((String) pair.getKey(), subTemp);
	// numOfSubMatchPerDataSet.put((String) pair.getKey(), subCount);
	// subIterator.remove();
	// }
	//
	// }

	private int getSum(Integer[] array) {
		int count = 0;
		for (Integer integer : array) {
			count += integer.intValue();
		}
		return count;
	}

	private int getSumOfValues(HashMap<String, Integer> hashMap) {
		int sum = 0;
		Iterator it = hashMap.entrySet().iterator();
		Map.Entry<String, Integer> pair = (Map.Entry) it.next();
		while (it.hasNext()) {
			sum += pair.getValue();
		}
		return sum;

	}

	@Override
	public String toString() {
		return "TweetAnalysis [subMatchingResult=" + subMatchingResult
				+ ", indMatchingResult=" + indMatchingResult
				+ ", numOfIndMatchPerDataSet=" + numOfIndMatchPerDataSet
				+ ", numOfSubMatchPerDataSet=" + numOfSubMatchPerDataSet
				+ ", rowSumSubjects=" + rowSumSubjects + ", colSumIndustries="
				+ colSumIndustries + ", subjectMatch=" + subjectMatch
				+ ", industryMatch=" + industryMatch + ", numOfSubjectMatch="
				+ numOfSubjectMatch + ", numOfIndustryMatch="
				+ numOfIndustryMatch + ", tweet=" + tweet + "]";
	}

	public HashMap<String, Boolean> getSubMatchingResult() {
		return subMatchingResult;
	}

	public HashMap<String, Boolean> getIndMatchingResult() {
		return indMatchingResult;
	}

	public HashMap<String, Integer> getNumOfIndMatchPerDataSet() {
		return numOfIndMatchPerDataSet;
	}

	public HashMap<String, Integer> getNumOfSubMatchPerDataSet() {
		return numOfSubMatchPerDataSet;
	}

	public int getRowSumSubjects() {
		return rowSumSubjects;
	}

	public int getColSumIndustries() {
		return colSumIndustries;
	}

	public boolean isSubjectMatch() {
		return subjectMatch;
	}

	public boolean isIndustryMatch() {
		return industryMatch;
	}

	public int getNumOfSubjectMatch() {
		return numOfSubjectMatch;
	}

	public int getNumOfIndustryMatch() {
		return numOfIndustryMatch;
	}

	public String getTweet() {
		return tweet;
	}

}
