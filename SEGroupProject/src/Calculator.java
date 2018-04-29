import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

import sun.awt.image.ImageRepresentation;

public class Calculator {

	private enum BounceCrit{PAGES, SECONDS};
	private MetricStorage metrics;
	private ArrayList<ImpressionEntry> impressions;
	private ArrayList<ClickEntry> clicks;
	DateFormat dfDay = new SimpleDateFormat("d MMM HH:mmaaa");
	DateFormat dfWeek = new SimpleDateFormat("d MMM HH:mmaaa");
	DateFormat dfMonth = new SimpleDateFormat("d MMM");
	DateFormat dfYear = new SimpleDateFormat("MMM-yyyy");
	private int range;
	private int bounceSeconds = -1;
	private int bouncePages = -1;
	private BounceCrit crit;
	
	public Calculator(MetricStorage metrics, 
			ArrayList<ImpressionEntry> impressions,
			ArrayList<ClickEntry> clicks) {
		this.metrics = metrics;
		this.impressions = impressions;
		this.clicks = clicks;
	}
	
	public void bouncePages(int n) {
		bouncePages = n;
		crit = BounceCrit.PAGES;
		calcBounces(BounceCrit.PAGES);
		calcBounceRate();
	}
	
	public void bounceSeconds(int n) {
		bounceSeconds = n;
		crit = BounceCrit.SECONDS;
		calcBounces(BounceCrit.SECONDS);
		calcBounceRate();
	}
	
	
	public String[] calcDates(int range, Date date) {
		DateFormat df = null;
		df = dfWeek;
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
		calcConversions();
		calcCosts();
		calcTotalCost();
		calcCTR();
		calcCPA();
		calcCPC();
		calcCPM();
		if(crit == BounceCrit.PAGES) {
			calcBounces(BounceCrit.PAGES);
			calcBounceRate();
		} else if (crit == BounceCrit.SECONDS) {
			calcBounces(BounceCrit.SECONDS);
			calcBounceRate();
		}
	}
	
	private void calcImpressions() {
		metrics.setImpressions(calcImpressions(impressions));
	}
	private int[] calcImpressions(ArrayList<ImpressionEntry> impressions) {
		int[] arr = new int[range];
		for(ImpressionEntry e : impressions) {
			arr[(int)e.getDate()]++;
		}
		return arr;
	}
	
	private void calcClicks() {
		metrics.setClicks(calcClicks(clicks));
	}
	private int[] calcClicks(ArrayList<ClickEntry> clicks) {
		int[] arr = new int[range];
		for(ClickEntry e : clicks) {
			arr[(int)e.getDate()]++;
		}
		return arr;
	}
	private void calcUniques() {
		metrics.setUniques(calcUniques(clicks));
	}
	private int[] calcUniques(ArrayList<ClickEntry> clicks) { //TODO
		int[] arr = new int[range];
		for(ClickEntry e : clicks.stream().distinct().collect(Collectors.toList())) {
			arr[(int)e.getDate()]++;
		}
		return arr;
	}
	private void calcBounces(BounceCrit crit) {
		metrics.setBounces(calcBounces(crit, clicks));
	}
	private int[] calcBounces(BounceCrit crit, ArrayList<ClickEntry> clicks) {
		int[] arr = new int[range];
		switch (crit) {
		case PAGES:
			for(ClickEntry e : clicks) {
				if (e.getPagesViewed() < bouncePages && !e.isConverted()) {
					arr[(int)e.getDate()]++;
				}
			}
			return arr;
		case SECONDS:
			for(ClickEntry e : clicks) {
				if (e.getSecondsViewed() < bounceSeconds && !e.isConverted()) {
					arr[(int)e.getDate()]++;
				}
			}
			return arr;
		}
		return null;
	}
	private void calcConversions() {
		metrics.setConversions(calcConversions(clicks));
	}
	private int[] calcConversions(ArrayList<ClickEntry> clicks) {
		int[] arr = new int[range];
		for(ClickEntry e : clicks) {
			if (e.isConverted()) {
				arr[(int)e.getDate()]++;
			}
		}
		return arr;
	}
	private void calcCosts() {
		metrics.setCosts(calcCosts(impressions, clicks));
	}
	private float[] calcCosts(ArrayList<ImpressionEntry> impressions, ArrayList<ClickEntry> clicks) {
		float[] costs = new float[range];
		for(ImpressionEntry e : impressions) {
			costs[(int) e.getDate()] += e.getImpressionCost();
		}
		for(ClickEntry e : clicks) {
			costs[(int) e.getDate()] += e.getClickCost();
		}
		return costs;
	}
	private void calcTotalCost() {
		metrics.setTotalCost(calcTotalCost(metrics.getCosts()));
	}
	private float calcTotalCost(float[] costs) {
		float totalCost = 0;
		metrics.getCosts();
		for(int i = 0; i < range; i++) {
			totalCost += costs[i];
		}
		return totalCost;
	}
	private void calcCTR() {
		metrics.setCtr(calcCTR(metrics.getClicks(), metrics.getImpressions()));
	}
	private float[] calcCTR(int[] cl, int[] im) {
		float[] arr = new float[range];
		for(int i = 0; i < range; i++) {
			if(im[i] != 0) {
				arr[i] = cl[i] / (float)im[i];
			}
		}
		return arr;
	}
	private void calcCPA() {
		metrics.setCpa(calcCPA(metrics.getCosts(), metrics.getConversions()));
	}
	private float[] calcCPA(float[] costs, int[] conv) {
		float[] arr = new float[range];
		for(int i = 0; i < range; i++) {
			if(conv[i] != 0) {
				arr[i] = costs[i] / conv[i];
			}
		}
		return arr;
	}
	private void calcCPC() {
		metrics.setCpc(calcCPC(metrics.getClicks(), impressions));
	}
	private float[] calcCPC(int[] clicks, ArrayList<ImpressionEntry> impressions) {
		float[] arr = new float[range];
		float[] costs = new float[range];
		for(ImpressionEntry e : impressions) {
			costs[(int) e.getDate()] += e.getImpressionCost();
		}
		for(int i = 0; i < range; i++) {
			if(clicks[i] != 0) {
				arr[i] = costs[i] / clicks[i];
			}
		}
		return arr;
	}
	private void calcCPM() {
		metrics.setCpm(calcCPM(metrics.getImpressions(), impressions));
	}
	private float[] calcCPM(int[] impr, ArrayList<ImpressionEntry> impressions) {
		float[] arr = new float[range];
		float[] costs = new float[range];
		for(ImpressionEntry e : impressions) {
			costs[(int) e.getDate()] += e.getImpressionCost();
		}
		for(int i = 0; i < range; i++) {
			arr[i] = costs[i] / (impr[i] / 1000.0f);
		}
		return arr;
	}
	private void calcBounceRate() {
		metrics.setBounceRate(calcBounceRate(metrics.getBounces(), metrics.getClicks()));
	}
	private float[] calcBounceRate(int[] bounces, int[] clicks) {
		float[] arr = new float[range];
		for(int i = 0; i < range; i++) {
			if(clicks[i] != 0) {
				arr[i] = bounces[i] / (float) clicks[i];
			} else {
				arr[i] = 0;
			}
		}
		return arr;
	}
	
	public int[] calcFilterInt(DataFilter f) {
		switch(f.getMetric()) {
		case IMPRESSIONS:
			return calcImpressions(f.filterImpressions(impressions));
		case BOUNCES:
			return calcBounces(crit, f.filterClicks(clicks));
		case CLICKS:
			return calcClicks(f.filterClicks(clicks));
		case CONVERSIONS:
			return calcConversions(f.filterClicks(clicks));
		case UNIQUES:
			return calcUniques(f.filterClicks(clicks));
		default:
			break;
		}
		return new int[]{0};
	}
	
	public float[] calcFilter(DataFilter f) {
		ArrayList<ClickEntry> c;
		switch(f.getMetric()) {
		case BOUNCE_RATE:
			c = f.filterClicks(clicks);
			return calcBounceRate(calcBounces(crit, c), calcClicks(c));
		case CPA:
			c = f.filterClicks(clicks);
			return calcCPA(calcCosts(f.filterImpressions(impressions), c), calcConversions(c));
		case CPC:
			return calcCPC(calcClicks(f.filterClicks(clicks)), f.filterImpressions(impressions));
		case CPM:
			ArrayList<ImpressionEntry> i = f.filterImpressions(impressions);
			return calcCPM(calcImpressions(i), i);
		case CTR:
			return calcCTR(calcClicks(f.filterClicks(clicks)), calcImpressions(f.filterImpressions(impressions)));
		case TOTAL_COST:
			return calcCosts(f.filterImpressions(impressions), f.filterClicks(clicks));
		default:
			break;
		}
		return new float[]{0};
	}
	
}
