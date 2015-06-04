package driver;

import org.influxdb.InfluxDB;

import com.fatafat.dao.impl.AlertsDao;
import com.fatafat.dao.impl.MatchedTweetsDao;

public class InfluxDriver {
	public static void main(String[] args) {
		MatchedTweetsDao dao = new MatchedTweetsDao();
		InfluxDB connection = dao.getConnection();
//		System.out.println(dao.selectAllStoredRecsInLastSecond(connection));
		
		AlertsDao aDao = new AlertsDao();
		System.out.println(aDao.selectAllStoredRecsInLastSecond(connection));
		System.out.println(aDao.selectAllByAlertTime(connection, "1433428196280", "30"));
	}
}
