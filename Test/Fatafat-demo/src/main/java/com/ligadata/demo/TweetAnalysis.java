package com.ligadata.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.ligadata.datasets.GlobalWordSets;
import com.ligadata.processors.CustomUDFs;

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
	
	public TweetAnalysis(String tweet) {

		this.tweet = tweet;
		HashMap<String, String[]> tempSubDataSets = (HashMap<String, String[]>) GlobalWordSets.getSubjectsDataSets().clone();
		HashMap<String, String[]> tempIndDataSets = (HashMap<String, String[]>) GlobalWordSets.getIndustriesDataSets().clone();
		Boolean subTemp;
		Boolean indTemp;
		int subCount = 0;
		int indCount = 0;
		String[] words;
		
		Iterator indIterator = tempIndDataSets.entrySet().iterator();
		while (indIterator.hasNext()) {
			Map.Entry pair = (Map.Entry) indIterator.next();
			words = (String[]) pair.getValue();
//			System.out.println(words.length);
			indTemp = CustomUDFs.getTokenizedBoolean(this.tweet, (String[]) pair.getValue());
			indCount = getSum(CustomUDFs.getTokenizedCounts(this.tweet, (String[]) pair.getValue()));
			if(indTemp){
				colSumIndustries++;
				industryMatch = true;
				numOfIndustryMatch += indCount;
			}
			
			indMatchingResult.put((String) pair.getKey(), indTemp);
			numOfIndMatchPerDataSet.put((String) pair.getKey(), indCount);
			indIterator.remove();
		}
		
		Iterator subIterator = tempSubDataSets.entrySet().iterator();
		while (subIterator.hasNext()) {
			Map.Entry pair = (Map.Entry) subIterator.next();
			words = (String[]) pair.getValue();
//			System.out.println(words.length);
			subTemp = CustomUDFs.getTokenizedBoolean(this.tweet, (String[]) pair.getValue());
			subCount = getSum(CustomUDFs.getTokenizedCounts(this.tweet, (String[]) pair.getValue()));
			if(subTemp){
				rowSumSubjects = 1;
				subjectMatch = true;
				numOfSubjectMatch += subCount;
			}
			
			subMatchingResult.put((String) pair.getKey(), subTemp);
			numOfSubMatchPerDataSet.put((String) pair.getKey(), subCount);
			subIterator.remove();
		}

	}
	
	private int getSum(Integer[] array){
		int count = 0;
		for (Integer integer : array) {
			count += integer.intValue();
		}
		return count;
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
