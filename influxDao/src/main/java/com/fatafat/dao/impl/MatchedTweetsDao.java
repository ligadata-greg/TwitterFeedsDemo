package com.fatafat.dao.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Serie;

import com.fatafat.dao.objs.GenericObj;
import com.fatafat.dao.objs.MatchedTweetObj;
import com.fatafat.dao.objs.Operator;

public class MatchedTweetsDao extends GenericDao {

	public List<GenericObj> selectAllByEntryTime(Operator op, InfluxDB connection) {

		MatchedTweetObj mtObj = null;
		List<GenericObj> result = new ArrayList<GenericObj>();
		List<Serie> series = connection.query("demo",
				"SELECT * from matchedTweet where time < now()", TimeUnit.MILLISECONDS);

		for (Serie serie : series) {

			List<Map<String, Object>> rows = serie.getRows();
//			
			DecimalFormat seqFormat = new DecimalFormat("#00000000");
			DecimalFormat timeFormat = new DecimalFormat("#0000000000000");
			
			for (Map<String, Object> row : rows) {
				mtObj = new MatchedTweetObj();
				mtObj.setTime(timeFormat.format((Double) row.get("time")));
				mtObj.setSequanceNumber(seqFormat.format((Double) row.get("sequence_number")));
				mtObj.setNps(Double.parseDouble(row.get("nps").toString()));
				mtObj.setTweetText(row.get("tweet").toString());
				mtObj.setFollowersCount(row.get("followerscount").toString());
				mtObj.setTweetDateTime((String)row.get("tweet_date_time"));
				mtObj.setRetweetCount(Integer.parseInt(row.get("retweetcount").toString()));
				mtObj.setUsername(row.get("username").toString());
				mtObj.setCitiBank(Integer.parseInt(row.get("is_citibank").toString()));
				mtObj.setPositive(Integer.parseInt(row.get("is_positive").toString()));
				mtObj.setBofa(Integer.parseInt(row.get("is_bofa").toString()));
				mtObj.setRoyalBankofScotland(Integer.parseInt(row.get("is_royalBankofScotland").toString()));
				mtObj.setBarclays(Integer.parseInt(row.get("is_barclays").toString()));
				mtObj.setNegative(Integer.parseInt(row.get("is_negative").toString()));
				mtObj.setHsbc(Integer.parseInt(row.get("is_hsbc").toString()));
				mtObj.setJpm(Integer.parseInt(row.get("is_jpm").toString()));
//				System.out.println(row);
				result.add(mtObj);
			}
		}

		return result;
	}

}
