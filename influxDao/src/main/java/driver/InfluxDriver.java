package driver;

import org.influxdb.InfluxDB;

import com.fatafat.dao.impl.MatchedTweetsDao;
import com.fatafat.dao.objs.Operator;

public class InfluxDriver {
	public static void main(String[] args) {
		MatchedTweetsDao dao = new MatchedTweetsDao();
		InfluxDB connection = dao.getConnection();
		System.out.println(dao.selectAllByEntryTime(Operator.LESSTHAN, connection)); 
	}
}
