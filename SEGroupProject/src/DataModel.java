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

	public XYChart.Series getSeries(DataFilter f, Granularity g) {
		XYChart.Series series = new XYChart.Series();
		switch (f.getMetric()) {

		case UNIQUES:
		case CONVERSIONS:
		case CLICKS:
		case BOUNCES:
		case IMPRESSIONS:
			populateSeries(series, calc.calcFilterInt(f), g);
			return series;
		case BOUNCE_RATE:
		case CPC:
		case CPA:
		case CPM:
		case CTR:
		case TOTAL_COST:
			populateSeries(series, calc.calcFilter(f, g), g);
			return series;
		default:
			return series;
		}
	}
	
	public XYChart.Series getSeries(Metric m, Granularity g) {
		XYChart.Series series = new XYChart.Series();
		switch (m) {
		case IMPRESSIONS:
			populateSeries(series, metrics.getImpressions(), g);
			return series;
		case CLICKS:
			populateSeries(series, metrics.getClicks(), g);
			return series;
		case UNIQUES:
			populateSeries(series, metrics.getUniques(), g);
			return series;
		case BOUNCES:
			populateSeries(series, metrics.getBounces(), g);
			return series;
		case CONVERSIONS:
			populateSeries(series, metrics.getConversions(), g);
			return series;
		case TOTAL_COST:
			populateSeries(series, metrics.getCosts(), g);
			return series;
		case CTR:
			populateSeries(series, metrics.getCtr(g), g);
			return series;
		case CPA:
			populateSeries(series, metrics.getCpa(g), g);
			return series;
		case CPC:
			populateSeries(series, metrics.getCpc(g), g);
			return series;
		case CPM:
			populateSeries(series, metrics.getCpm(g), g);
			return series;
		case BOUNCE_RATE:
			populateSeries(series, metrics.getBounceRate(g), g);
			return series;
		}
		return null;
	}
	
	private void populateSeries(XYChart.Series series, float[] met, Granularity g) {
		int step = 1; // Number of hours between data points.
		switch (g) {
		case HOURLY:
			step = 1;
			break;
		case DAILY:
			step = 24;
			break;
		case WEEKLY:
			step = 24*7;
			break;
		default:
			break;
		}
		float acc = 0;
		for(int i = 0; i < dates.length; i++) {
			if(i != 0 && i%step == 0) {
				series.getData().add(new XYChart.Data(dates[i - step], acc));
				acc = 0;
			}
			acc += met[i];
		}
		if(acc != 0) {
			series.getData().add(new XYChart.Data(dates[dates.length - 1], acc));
		}
	}
	
	private void populateSeries (XYChart.Series series, int[] met, Granularity g) {
		int step = 1; // Number of hours between data points.
		switch (g) {
		case HOURLY:
			step = 1;
			break;
		case DAILY:
			step = 24;
			break;
		case WEEKLY:
			step = 24*7;
			break;
		default:
			break;
		}
		float acc = 0;
		for(int i = 0; i < dates.length; i++) {
			if(i != 0 && i%step == 0) {
				series.getData().add(new XYChart.Data(dates[i - step], acc));
				acc = 0;
			}
			acc += met[i];
		}
		if(acc != 0) {
			series.getData().add(new XYChart.Data(dates[dates.length - 1], acc));
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
