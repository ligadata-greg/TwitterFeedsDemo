package driver;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Serie;

public class InfluxDriver {
	public static void main(String[] args) {

		
		InfluxDB influxDB = InfluxDBFactory.connect(
				"http://fatafat-dev.ligadata.com:8086", "root", "root");


		// influxDB.createDatabase("aTimeSeries");
		//
		// Serie serie1 = new Serie.Builder("serie2Name")
		// .columns("column1", "column2")
		// .values(System.currentTimeMillis(), 1)
		// .values(System.currentTimeMillis(), 2).build();

		List<Serie> series = influxDB.query("demo",
				"SELECT * from matchedTweet", TimeUnit.MILLISECONDS);


		for (Serie serie : series) {

			List<Map<String, Object>> rows = serie.getRows();

			for (Map<String, Object> row : rows) {
//				System.out.println(">>>>>>>>>>>>>>>>>>> " + row.get("cnt_4_3"));
				// System.out.println(">>>>>>>>>>>>>>>>>>> " +
				// row.get("cnt_3_1"));
			}
		}
	}
}
