import java.util.HashSet;
import java.util.List;

public abstract class Calculator {

	public static OverviewItems getOverview(List<Log> impressionLog, List<Log> clickLog, List<Log> serverLog, int minPages, int minSeconds) {
		OverviewItems items = new OverviewItems();
		int clicks = Calculator.calcClicks(clickLog);
		int impressions = Calculator.calcImpressions(impressionLog);
		float cost = Calculator.calcTotalCost(impressionLog);
		int converted = Calculator.calcConversions(serverLog);
		int bounces = Calculator.calcBounces(serverLog, minPages, minSeconds);
		items.setClicks(clicks);
		items.setImpressions(impressions);
		items.setUniques(Calculator.calcUniques(clickLog));
		items.setConversions(converted);
		items.setTotalCost(cost);
		items.setCTR(Calculator.calcCTR(clicks, impressions));
		items.setCPA(Calculator.calcCPA(cost, converted));
		items.setCPC(Calculator.calcCPC(cost, clicks));
		items.setCPM(Calculator.calcCPM(cost, impressions));
		items.setBounces(bounces);
		items.setBounceRate(Calculator.calcBounceRate(bounces, clicks));
		
		return items;
	}
	
	public static int calcImpressions (List<Log> impressionLog) {
		return impressionLog.size();
	}
	
	public static int calcClicks (List<Log> clickLog) {
		return clickLog.size();
	}
	
	public static int calcUniques (List<Log> clickLog) {
		HashSet<Long> ids = new HashSet<Long>();
		
		for(Log l : clickLog) {
			ids.add(((ClickLog)l).getId());
		}
		
		return clickLog.size();
	}

	public static int calcBounces (List<Log> serverLog, int minPages, int minSeconds) {
		int bounces = 0;
		
		for(Log l : serverLog) {
			
			// Do time check.
			if(minSeconds > 0 && ((ServerLog)l).getExitDate() != null) {
				long msSpent = ((ServerLog)l).getExitDate().getTime() - ((ServerLog)l).getEntryDate().getTime();
				if(msSpent / 1000 >= minSeconds) {
					bounces++;
					continue;
				}
			}
			// Do page check.
			if(((ServerLog)l).getPagesViewed() < minPages) {
				bounces++;
			}
		}
		return bounces;
	}
	
	public static int calcConversions (List<Log> serverLog) {
		int conversions = 0;
		for(Log l : serverLog) {
			if(((ServerLog)l).isConverted()) {
				conversions++;
			}
		}
		return conversions;
	}
	
	public static float calcTotalCost (List<Log> impressionLog) {
		float totalCost = 0;
		for(Log l : impressionLog) {
			totalCost += ((ImpressionLog)l).getImpressionCost();
		}
		
		return totalCost;
	}
	
	public static float calcCTR (int clicks, int impressions) {
		return (float) clicks / impressions;
	}
	
	public static float calcCPA (float cost, int converted) {
		return cost / converted;
	}
	
	public static float calcCPC (float cost, int clicks) {
		return cost / clicks;
	}
	
	public static float calcCPM (float cost, int impressions) {
		return cost / (impressions/1000);
	}
	
	public static float calcBounceRate (int bounces, int clicks) {
		return (float) bounces / clicks;
	}
	
}
