package com.ligadata.fatafat.wordsets;

import java.util.HashMap;

import com.ligadata.fatafat.iwordsets.IWordSet;
import com.ligadata.fatafat.utils.Utility;

public class WordSets implements IWordSet{

	private HashMap<String, HashMap<String, Integer>> wordSets = null;
	
	public WordSets(HashMap<String, HashMap<String, Integer>> wordSets){
		setWordSets(wordSets);
	}
	
	public WordSets(String[] filesPaths){
		wordSets = new HashMap<String, HashMap<String,Integer>>();
		HashMap<String, Integer> tempWordSet = null;
		for (String string : filesPaths) {
			tempWordSet = new HashMap<String, Integer>();
			for (String word : Utility.loadCSVFile(string)) {
				tempWordSet.put(word.toLowerCase(), 1);	
			}
			wordSets.put(string, tempWordSet);
		}
	}


	public void setWordSets(HashMap<String, HashMap<String, Integer>> wordSets) {
		this.wordSets = wordSets;
	}


	public HashMap<String, HashMap<String, Integer>> getWordSets() {
		// TODO Auto-generated method stub
		return this.wordSets;
	}

}
