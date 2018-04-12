import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataModel {
	
	private ArrayList<ImpressionEntry> impressions;
	private ArrayList<ClickEntry> clicks;
	private ArrayList<ServerEntry> servers;
	private MetricStorage metrics;
	private String[] dates;
	private OverviewItems overview;
	
	private DBManager dbm;
	private Calculator calc;
	
	public void init () {
		metrics = new MetricStorage();
		impressions = new ArrayList<ImpressionEntry>();
		clicks = new ArrayList<ClickEntry>();
		servers = new ArrayList<ServerEntry>();
		dbm = new DBManager();
		dbm.init();
		calc = new Calculator(metrics, impressions, clicks, servers);
	}
	
	public void exportCSVs(File impressionCSV, File clickCSV, File serverCSV) {
		dbm.exportData(impressionCSV, clickCSV, serverCSV);
	}
	
	public OverviewItems selectCampaign(int ID) {
		overview = dbm.selectCampaign(ID);
		return overview;
	}
	
	@SuppressWarnings("unchecked")
	public void fetchData(Date startD, Date endD) {
		// Get data from server.
		Object[] logs = dbm.fetchData(startD, endD);
		
		impressions.clear();
		clicks.clear();
		servers.clear();
		impressions.addAll((ArrayList<ImpressionEntry>) logs[0]);
		clicks.addAll((ArrayList<ClickEntry>) logs[1]);
		servers.addAll((ArrayList<ServerEntry>) logs[2]);
		
		// Set range and Calculate metrics based on new data.
		int range = DataParser.relativeDate(startD.getTime(), endD) + 1;
		calc.calcMetrics(range);
		dates = calc.calcDates(range, startD);
	}
	
	//TODO change return to Series
	public void getMetric(Metric m) {
		float[] met = metrics.getCpa();
		for(int i = 0; i < dates.length; i++) {
			System.out.println(dates[i] + " " + met[i]);
		}
	}

	public OverviewItems getOverview() {
		return overview;
	}
	
}
