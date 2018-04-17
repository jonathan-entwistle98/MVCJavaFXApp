import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

public class Calculator {

	private enum BounceCrit{PAGES, SECONDS};
	private MetricStorage metrics;
	private ArrayList<ImpressionEntry> impressions;
	private ArrayList<ClickEntry> clicks;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private int range;
	
	public Calculator(MetricStorage metrics, 
			ArrayList<ImpressionEntry> impressions,
			ArrayList<ClickEntry> clicks) {
		this.metrics = metrics;
		this.impressions = impressions;
		this.clicks = clicks;
	}
	
	public void bouncePages(int n) {
		calcBounces(BounceCrit.PAGES, n);
		calcBounceRate();
	}
	
	public void bounceSeconds(int n) {
		calcBounces(BounceCrit.SECONDS, n);
		calcBounceRate();
	}
	
	
	public String[] calcDates(int range, Date date) {
		String[] dates = new String[range];
		long startTime = date.getTime();
		for(int i = 0; i < range; i++) {
			dates[i] = df.format((new Date(startTime + i * 3600000)));
		}
		return dates;
	}
	
	public void calcMetrics(int range) {
		this.range = range;
		calcImpressions();
		calcClicks();
		calcUniques();
//		calcBounces(BounceCrit.PAGES, 5);
		calcConversions();
		calcCosts();
		calcTotalCost();
		calcCTR();
		calcCPA();
		calcCPC();
		calcCPM();
//		calcBounceRate();
	}
	
	private void calcImpressions() {
		int[] arr = new int[range];
		for(ImpressionEntry e : impressions) {
			arr[(int)e.getDate()]++;
		}
		metrics.setImpressions(arr);
	}
	private void calcClicks() {
		int[] arr = new int[range];
		for(ClickEntry e : clicks) {
			arr[(int)e.getDate()]++;
		}
		metrics.setClicks(arr);
	}
	private void calcUniques() { //TODO
		int[] arr = new int[range];
 		ArrayList<HashSet> IDs = new ArrayList<HashSet>(range);
 		for(int i = 0; i < range; i++) {
 			IDs.add(new HashSet());
 		}
		for(ClickEntry e : clicks) {
			IDs.get((int)e.getDate()).add(e.getId());
		}
		
		for(int i = 0; i < range; i++) {
			arr[i] = IDs.get(i).size();
		}
		metrics.setUniques(arr);
	}
	private void calcBounces(BounceCrit crit, int n) {
		int[] arr = new int[range];
		switch (crit) {
		case PAGES:
			for(ClickEntry e : clicks) {
				if (e.getPagesViewed() < n && !e.isConverted()) {
					arr[(int)e.getDate()]++;
				}
			}
			metrics.setBounces(arr);
			break;
		case SECONDS:
			for(ClickEntry e : clicks) {
				if (e.getSecondsViewed() < n && !e.isConverted()) {
					arr[(int)e.getDate()]++;
				}
			}
			metrics.setBounces(arr);
			break;
		}
	}
	private void calcConversions() {
		int[] arr = new int[range];
		for(ClickEntry e : clicks) {
			if (e.isConverted()) {
				arr[(int)e.getDate()]++;
			}
		}
		metrics.setConversions(arr);
	}	
	private void calcCosts() {
		float[] costs = new float[range];
		for(ImpressionEntry e : impressions) {
			costs[(int) e.getDate()] += e.getImpressionCost();
		}
		for(ClickEntry e : clicks) {
			costs[(int) e.getDate()] += e.getClickCost();
		}
		metrics.setCosts(costs);
	}
	private void calcTotalCost() {
		float totalCost = 0;
		float[] costs = metrics.getCosts();
		for(int i = 0; i < range; i++) {
			totalCost += costs[i];
		}
		metrics.setTotalCost(totalCost);
	}
	private void calcCTR() {
		float[] arr = new float[range];
		int[] cl = metrics.getClicks();
		int[] im = metrics.getImpressions();
		for(int i = 0; i < range; i++) {
			if(im[i] != 0) {
				arr[i] = cl[i] / (float)im[i];
			}
		}
		metrics.setCtr(arr);
	}
	private void calcCPA() {
		float[] arr = new float[range];
		float[] costs = metrics.getCosts();
		int[] conv = metrics.getConversions();
		for(int i = 0; i < range; i++) {
			if(conv[i] != 0) {
				arr[i] = costs[i] / conv[i];
			}
		}
		metrics.setCpa(arr);
	}
	private void calcCPC() {
		float[] arr = new float[range];
		float[] costs = new float[range];
		int[] clicks = metrics.getClicks();
		for(ImpressionEntry e : impressions) {
			costs[(int) e.getDate()] += e.getImpressionCost();
		}
		for(int i = 0; i < range; i++) {
			if(clicks[i] != 0) {
				arr[i] = costs[i] / clicks[i];
			}
		}
		metrics.setCpc(arr);
	}
	private void calcCPM() {
		float[] arr = new float[range];
		float[] costs = new float[range];
		int[] impr = metrics.getImpressions();
		for(ImpressionEntry e : impressions) {
			costs[(int) e.getDate()] += e.getImpressionCost();
		}
		for(int i = 0; i < range; i++) {
			arr[i] = costs[i] / (impr[i] / 1000.0f);
		}
		metrics.setCpm(arr);
	}
	private void calcBounceRate() {
		float[] arr = new float[range];
		int[] bounces = metrics.getBounces();
		int[] clicks = metrics.getClicks();
	
		for(int i = 0; i < range; i++) {
			arr[i] = bounces[i] / (float) clicks[i];
		}
		metrics.setBounceRate(arr);
	}
	
}
