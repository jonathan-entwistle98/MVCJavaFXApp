import java.util.HashSet;
import java.util.List;

/**
 * An abstract class which contains methods required to calculate overview statistics of a campaign from log files.
 *
 * 
 */


public abstract class Calculator {

	/**
	 * Creates an instance of OverviewItems containing campaign overview information calculated from log files.
	 * 
	 * @param impressionLog list of ImpressionLog items.
	 * @param clickLog list of ClickLog items.
	 * @param serverLog list of ServerLog items.
	 * @param minPages less than that and bounce will be registered. Ignored if 0.
	 * @param minSeconds less than that and bounce will be registered. Ignored if 0.
	 * @return calculated overview of campaign.
	 */
	public static OverviewItems getOverview(List<Log> impressionLog, List<Log> clickLog, List<Log> serverLog, int minPages, int minSeconds) {
		OverviewItems items = new OverviewItems();
		int clicks = Calculator.calcClicks(clickLog);
		int impressions = Calculator.calcImpressions(impressionLog);
		float imprCost = Calculator.calcImprCost(impressionLog);
		float clickCost = Calculator.calcClickCost(clickLog);
		int converted = Calculator.calcConversions(serverLog);
		int bounces = Calculator.calcBounces(serverLog, minPages, minSeconds);
		items.setClicks(clicks);
		items.setImpressions(impressions);
		items.setUniques(Calculator.calcUniques(clickLog));
		items.setConversions(converted);
		items.setTotalCost(imprCost + clickCost);
		items.setCTR(Calculator.calcCTR(clicks, impressions));
		items.setCPA(Calculator.calcCPA(imprCost + clickCost, converted));
		items.setCPC(Calculator.calcCPC(clickCost, clicks));
		items.setCPM(Calculator.calcCPM(imprCost, impressions));
		items.setBounces(bounces);
		items.setBounceRate(Calculator.calcBounceRate(bounces, clicks));
		
		return items;
	}
	
	/**
	 *
	 * @param impressionLog
	 * @return total number of impressions.
	 */
	public static int calcImpressions (List<Log> impressionLog) {
		return impressionLog.size();
	}
	
	/**
	 * 
	 * @param clickLog
	 * @return total number of clicks.
	 */
	public static int calcClicks (List<Log> clickLog) {
		return clickLog.size();
	}
	/**
	 * 
	 * @param clickLog
	 * @return total number of unique clicks.
	 */
	public static int calcUniques (List<Log> clickLog) {
		HashSet<Long> ids = new HashSet<Long>();
		
		for(Log l : clickLog) {
			ids.add(((ClickLog)l).getId());
		}
		
		return ids.size();
	}
	/**
	 * 
	 * @param serverLog
	 * @param minPages less than that and bounce will be registered. Ignored if 0.
	 * @param minSeconds less than that and bounce will be registered. Ignored if 0.
	 * @return number of bounces based on defined criteria.
	 */
	public static int calcBounces (List<Log> serverLog, int minPages, int minSeconds) {
		int bounces = 0;
		
		for(Log l : serverLog) {
			
			// Do time check.
			if(minSeconds > 0 && ((ServerLog)l).getExitDate() != null) {
				long msSpent = ((ServerLog)l).getExitDate().getTime() - ((ServerLog)l).getEntryDate().getTime();
				if(msSpent / 1000 < minSeconds) {
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
	/**
	 * 
	 * @param serverLog
	 * @return total number of customer conversions.
	 */
	public static int calcConversions (List<Log> serverLog) {
		int conversions = 0;
		for(Log l : serverLog) {
			if(((ServerLog)l).isConverted()) {
				conversions++;
			}
		}
		return conversions;
	}
	/**
	 * 
	 * @param clickLog
	 * @return total cost of all clicks.
	 */
	public static float calcClickCost (List<Log> clickLog) {
		float cost = 0;
		for(Log l: clickLog) {
			cost += ((ClickLog)l).getClickCost();
		}
		return cost;
	}
	/**
	 * 
	 * @param impressionLog
	 * @return total cost of all impressions.
	 */
	public static float calcImprCost(List<Log> impressionLog) {
		float cost = 0;
		for(Log l : impressionLog) {
			cost += ((ImpressionLog)l).getImpressionCost();
		}
		return cost;
	}
	/**
	 * 
	 * @param clicks
	 * @param impressions
	 * @return click-through-rate: the average number of clicks per impression.
	 */
	public static float calcCTR (int clicks, int impressions) {
		return (float) clicks / impressions;
	}
	/**
	 * 
	 * @param cost
	 * @param converted
	 * @return cost-per-acquisition:The average amount of money spent on an advertising campaign for each acquisition (conversion).
	 */
	public static float calcCPA (float cost, int converted) {
		return cost / converted;
	}
	/**
	 * 
	 * @param cost
	 * @param clicks
	 * @return cost-per-click: The average amount of money spent on an advertising campaign for each click.
	 * 
	 */
	public static float calcCPC (float cost, int clicks) {
		return cost / clicks;
	}
	/**
	 * 
	 * @param cost
	 * @param impressions
	 * @return cost-per-thousand impressions: The average amount of money spent on an advertising campaign for every
	 * one thousand impressions.
	 */
	public static float calcCPM (float cost, int impressions) {
		return cost / (impressions/1000);
	}
	/**
	 * 
	 * @param bounces
	 * @param clicks
	 * @return the average number of bounces per click.
	 */
	public static float calcBounceRate (int bounces, int clicks) {
		return (float) bounces / clicks;
	}
	
}
