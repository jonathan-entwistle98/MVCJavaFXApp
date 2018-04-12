import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.chart.XYChart;

public class Example {

	public static void main (String[] args) {
		
		File file1 = new File("impression_log.csv");
		File file2 = new File("click_log.csv");
		File file3 = new File("server_log.csv");
		
		String dateFormat = "yyyy-MM-dd HH:mm:ss";
		Date start = null;
		Date end = null;
		try {
			start = new SimpleDateFormat(dateFormat).parse("2015-01-01 12:00:00");
			end = new SimpleDateFormat(dateFormat).parse("2015-02-30 13:59:08");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Example below shows how to use DataModel class.
		 * 
		 * 
		 */
		
		// Create class.
		DataModel dm = new DataModel();
		
		// Call initialization method (creates storage objects, connects to database, etc.).
		dm.init();
		
		// Load campaign to the database by providing the following:
		// 	file1 = impression_log.CSV
		// 	file2 = click_log.CSV
		//  file3 = server_log.CSV
//		dm.exportCSVs(file1, file2, file3);
		
		// Select campaign by its ID from the database.
		// This selects campaign for loading and returns overview metrics.
		OverviewItems overview = dm.selectCampaign(1);
		
		// Gets data with set date range and stores it in DataModel.
		// Must pass two Date objects (start and end) as parameters.
		dm.fetchData(start, end);
		// Returns XYChart.Series for defined metric.
		XYChart.Series series = dm.getSeries(Metric.IMPRESSIONS);
	}

}
