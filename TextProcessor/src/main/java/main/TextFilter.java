package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ligadata.fatafat.iwordsets.IWordSet;

public class TextFilter {

	private String text = null;
	private List<String> textWordsList = null;
	private HashMap<String, HashMap<String, Integer>> matchCounts = null;
	private HashMap<String, Integer> sumOfMatchCounts = null;
	private int totalMatchCount = 0;

	public TextFilter(String text) {
		super();
		this.text = text;
		this.textWordsList = getTextFiltered(this.text);
	}

	public void countMatches(IWordSet wordSet) {
		Integer matchValue = null;
		matchCounts = new HashMap<String, HashMap<String, Integer>>();
		sumOfMatchCounts = new HashMap<String, Integer>();
		HashMap<String, Integer> tempResultMap = null;
		HashMap<String, Integer> tempWordsMap = null;
		Map.Entry pair = null;
		Iterator it = wordSet.getWordSets().entrySet().iterator();
		Integer tempVal = null;
		String tempWord = null;
		int dsCount = 0;
		
		while (it.hasNext()) {
				tempResultMap = null;
				tempVal = null;
				pair = (Map.Entry) it.next();
				tempWordsMap = (HashMap<String, Integer>) pair.getValue();
				dsCount= 0;
				for (String word : textWordsList) {
					
					tempWord = word;
					matchValue = tempWordsMap.get(word);
					if (matchValue != null) {
						this.totalMatchCount += 1;
						if (tempResultMap != null)
							tempVal = tempResultMap.get(word);
						if (tempResultMap == null) {
							tempResultMap = new HashMap<String, Integer>();
							tempResultMap.put(word, 1);
							dsCount++;
						} else {
							if(tempVal != null){
								tempResultMap.put(word, tempVal + 1);
								dsCount++;
							}
							else{
								tempResultMap.put(word, 1);
								dsCount++;
							}
						}
					}
				}
				if (tempResultMap != null){
					matchCounts.put((String) pair.getKey(), tempResultMap);
					sumOfMatchCounts.put((String) pair.getKey(), dsCount);
				}
		}
	}

	protected static List<String> getTextFiltered(String text) {
		List<String> textWords = new ArrayList<String>();
		Pattern p = Pattern.compile("[\\w']+");
		Matcher m = p.matcher(text);
		String word = null;
		while (m.find()) {
			word = text.substring(m.start(), m.end());
			// System.out.println(word);
			textWords.add(word.toLowerCase());
		}
		return textWords;
	}

	public List<String> getTextWordsList() {
		return textWordsList;
	}

	public String getText() {
		return text;
	}

	public int getTotalMatchCount() {
		return totalMatchCount;
	}

	public HashMap<String, HashMap<String, Integer>> getMatchCounts() {
		return matchCounts;
	}

	public HashMap<String, Integer> getSumOfMatchCounts() {
		return sumOfMatchCounts;
	}
}
