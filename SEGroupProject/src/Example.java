import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		 * If anything goes wrong during exporting files just delete test.db
		 * and try again.
		 * 
		 */
		
		// Create class.
		DataModel dm = new DataModel();
		
		// Call initialization method (creates storage objects, connects to database, etc.).
		dm.init();
		
		// Export campaign to the database by providing the following:
		// 	File file1 = impression_log.CSV
		// 	File file2 = click_log.CSV
		//  File file3 = server_log.CSV
		// Doing so will create a new campaign and allocate it an ID.
		dm.exportCSVs(file1, file2, file3, "SomeCampaign");
		
		// Get IDs of all existing campaigns.
		ArrayList<Integer> campaignIDs = dm.getCampaigns();
		
		// Select campaign by its ID from the database.
		// This selects campaign for loading and returns overview metrics.
		OverviewItems overview = dm.selectCampaign(1);
		
		// Gets data with set date range and stores it in DataModel.
		// Must pass two Date objects (start and end) as parameters.
		dm.fetchData(start, end);
		
		// Returns XYChart.Series for defined metric.
//		XYChart.Series series = dm.getSeries(Metric.IMPRESSIONS);
		
	}

}
