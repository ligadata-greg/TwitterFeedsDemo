package com.fatafat.dao.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Serie;

import com.fatafat.dao.objs.AlertsObj;
import com.fatafat.dao.objs.GenericObj;

public class AlertsDao extends GenericDao {

	public List<GenericObj> selectAllStoredRecsInLastSecond(InfluxDB connection) {
		// TODO Auto-generated method stub
		AlertsObj alObj = null;
		List<GenericObj> result = new ArrayList<GenericObj>();
		List<Serie> series = connection.query("demo",
				"SELECT * from alerts where time > now() - 1s limit 20", TimeUnit.MILLISECONDS);

		for (Serie serie : series) {

			List<Map<String, Object>> rows = serie.getRows();
//			
			DecimalFormat seqFormat = new DecimalFormat("#00000000");
			DecimalFormat timeFormat = new DecimalFormat("#0000000000000");
			
			for (Map<String, Object> row : rows) {
				alObj = new AlertsObj();
				alObj.setTime(timeFormat.format((Double) row.get("time")));
				alObj.setSequanceNumber(seqFormat.format((Double) row.get("sequence_number")));
				alObj.setUsername(row.get("username").toString());
				alObj.setTweetText(row.get("tweet").toString());
				alObj.setRetweetCount(Integer.parseInt(row.get("retweetcount").toString()));
				alObj.setNps(Double.parseDouble(row.get("nps").toString()));
				alObj.setFavoriteCount(Integer.parseInt(row.get("favoritecount").toString()));
				alObj.setFollowersCount(row.get("followerscount").toString());
				alObj.setTimeWindow(Integer.parseInt(row.get("timewindow").toString()));
				alObj.setTotalMatchedTweets(Integer.parseInt(row.get("totalmatchedtweets").toString()));
				alObj.setTotalProcessedTweets(Integer.parseInt(row.get("totalprocessedtweets").toString()));
				
				alObj.setS_barclays_positive(row.get("sum_1_1").toString());
				alObj.setS_barclays_negative(row.get("sum_1_2").toString());
				alObj.setA_barclays_positive(row.get("alert_1_1").toString());
				alObj.setA_barclays_negative(row.get("alert_1_2").toString());
				alObj.setT_barclays_positive(row.get("threshold_1_1").toString());
				alObj.setT_barclays_negative(row.get("threshold_1_2").toString());
				
				alObj.setS_citibank_positive(row.get("sum_2_1").toString());
				alObj.setS_citibank_negative(row.get("sum_2_2").toString());
				alObj.setA_citibank_positive(row.get("alert_2_1").toString());
				alObj.setA_citibank_negative(row.get("alert_2_2").toString());
				alObj.setT_citibank_positive(row.get("threshold_2_1").toString());
				alObj.setT_citibank_negative(row.get("threshold_2_2").toString());
				
				alObj.setS_bofa_positive(row.get("sum_3_1").toString());
				alObj.setS_bofa_negative(row.get("sum_3_2").toString());
				alObj.setA_bofa_positive(row.get("alert_3_1").toString());
				alObj.setA_bofa_negative(row.get("alert_3_2").toString());
				alObj.setT_bofa_positive(row.get("threshold_3_1").toString());
				alObj.setT_bofa_negative(row.get("threshold_3_2").toString());

				alObj.setS_royalbankofscotland_positive(row.get("sum_4_1").toString());
				alObj.setS_royalbankofscotland_negative(row.get("sum_4_2").toString());
				alObj.setA_royalbankofscotland_positive(row.get("alert_4_1").toString());
				alObj.setA_royalbankofscotland_negative(row.get("alert_4_2").toString());
				alObj.setT_royalbankofscotland_positive(row.get("threshold_4_1").toString());
				alObj.setT_royalbankofscotland_negative(row.get("threshold_4_2").toString());
				
				alObj.setS_hsbc_positive(row.get("sum_5_1").toString());
				alObj.setS_hsbc_negative(row.get("sum_5_2").toString());
				alObj.setA_hsbc_positive(row.get("alert_5_1").toString());
				alObj.setA_hsbc_negative(row.get("alert_5_2").toString());
				alObj.setT_hsbc_positive(row.get("threshold_5_1").toString());
				alObj.setT_hsbc_negative(row.get("threshold_5_2").toString());
				
				alObj.setS_jpm_positive(row.get("sum_6_1").toString());
				alObj.setS_jpm_negative(row.get("sum_6_2").toString());
				alObj.setA_jpm_positive(row.get("alert_6_1").toString());
				alObj.setA_jpm_negative(row.get("alert_6_2").toString());
				alObj.setT_jpm_positive(row.get("threshold_6_1").toString());
				alObj.setT_jpm_negative(row.get("threshold_6_2").toString());
				
//				System.out.println(row);
				result.add(alObj);
			}
		}

		return result;
	}

}
