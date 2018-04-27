import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.chart.XYChart;

public class DataModel {
	
	private ArrayList<ImpressionEntry> impressions;
	private ArrayList<ClickEntry> clicks;
	private MetricStorage metrics;
	private String[] dates;
	private OverviewItems overview;
	
	private DBManager dbm;
	private Calculator calc;
	private String campaignName;
	

	
	public void init () {
		metrics = new MetricStorage();
		impressions = new ArrayList<ImpressionEntry>();
		clicks = new ArrayList<ClickEntry>();
		dbm = new DBManager();
		dbm.init();
		calc = new Calculator(metrics, impressions, clicks);
	}
	
	public void exportCSVs(File impressionCSV, File clickCSV, File serverCSV, String campaignName) {
		double[] progress = new double[3];
		dbm.exportData(impressionCSV, clickCSV, serverCSV, progress, campaignName);
		
		
		
	}
	public void exportCSVs(File impressionCSV, File clickCSV, File serverCSV, String campaignName, double[] progress) {
//		progress = new double[3];
		dbm.exportData(impressionCSV, clickCSV, serverCSV, progress, campaignName);
		
	}
	
	public OverviewItems selectCampaign(int ID) {
		overview = dbm.selectCampaign(ID);
		return overview;
	}
	
	@SuppressWarnings("unchecked")
	public void fetchData(Date startD, Date endD) {
		// Get data from server.
		long time = System.currentTimeMillis();
		Object[] logs = dbm.fetchData(startD, endD);
		System.out.println(System.currentTimeMillis() - time);
		impressions.clear();
		clicks.clear();
		impressions.addAll((ArrayList<ImpressionEntry>) logs[0]);
		clicks.addAll((ArrayList<ClickEntry>) logs[1]);
		
		// Set range and Calculate metrics based on new data.
		int range = DataParser.relativeDate(startD.getTime(), endD) + 1;
		calc.calcMetrics(range);
		dates = calc.calcDates(range, startD);
	}
	
	public XYChart.Series getSeries(Metric m) {
		XYChart.Series series = new XYChart.Series();
		switch (m) {
		case IMPRESSIONS:
			populateSeries(series, metrics.getImpressions());
			return series;
		case CLICKS:
			populateSeries(series, metrics.getClicks());
			return series;
		case UNIQUES:
			populateSeries(series, metrics.getUniques());
			return series;
		case BOUNCES:
			populateSeries(series, metrics.getBounces());
			return series;
		case CONVERSIONS:
			populateSeries(series, metrics.getConversions());
			return series;
		case TOTAL_COST:
			populateSeries(series, metrics.getCosts());
			return series;
		case CTR:
			populateSeries(series, metrics.getCtr());
			return series;
		case CPA:
			populateSeries(series, metrics.getCpa());
			return series;
		case CPC:
			populateSeries(series, metrics.getCpc());
			return series;
		case CPM:
			populateSeries(series, metrics.getCpm());
			return series;
		case BOUNCE_RATE:
			populateSeries(series, metrics.getBounceRate());
			return series;
		}
		return null;
	}
	
	private void populateSeries(XYChart.Series series, float[] met) {
		for(int i = 0; i < dates.length; i++) {
			series.getData().add(new XYChart.Data(dates[i], met[i]));
		}
	}
	
	private void populateSeries (XYChart.Series series, int[] met) {
		for(int i = 0; i < dates.length; i++) {
			series.getData().add(new XYChart.Data(dates[i], met[i]));
		}
	}

	public OverviewItems getOverview() {
		return overview;
	}
	
	public ArrayList<Integer> getCampaigns() {
		return dbm.getCampaigns();
	}
	
	public ArrayList<ArrayList<Object>> getCampaignNamesAndIds() {
		return dbm.getCampaignNamesAndIds();
	}
	
	/**
	 * Changes bounce criteria to pages and sets max count.
	 * 
	 * @param n - max number of pages for bounce to be registered.
	 */
	public void bouncePages(int n) {
		calc.bouncePages(n);
	}
	
	/**
	 * Changes bounce criteria to seconds and sets max count.
	 * 
	 * @param n - max number of seconds for bounce to be registered.
	 */
	public void bounceSeconds(int n) {
		calc.bounceSeconds(n);
	}
}
