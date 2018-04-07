import java.util.ArrayList;
import java.util.Date;
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
	 * @param impressionLogs list of ImpressionLog items.
	 * @param clickLogs list of ClickLog items.
	 * @param serverLogs list of ServerLog items.
	 * @param minPages less than that and bounce will be registered. Ignored if 0.
	 * @param minSeconds less than that and bounce will be registered. Ignored if 0.
	 * @return calculated overview of campaign.
	 */
	public static OverviewItems getOverview(List<ImpressionLog> impressionLogs, List<ClickLog> clickLogs, List<ServerLog> serverLogs, int minPages, int minSeconds) {
		OverviewItems items = new OverviewItems();
		int clicks = Calculator.calcClicks(clickLogs);
		int impressions = Calculator.calcImpressions(impressionLogs);
		float imprCost = Calculator.calcImprCost(impressionLogs);
		float clickCost = Calculator.calcClickCost(clickLogs);
		int converted = Calculator.calcConversions(serverLogs);
		int bounces = Calculator.calcBounces(serverLogs, minPages, minSeconds);
		ArrayList<ArrayList<Object>> impressionsOverTime = Calculator.calcImpressionsOverTime(impressionLogs);
		items.impressionsOverTime = impressionsOverTime;
		items.setClicks(clicks);
		items.setImpressions(impressions);
		items.setUniques(Calculator.calcUniques(clickLogs));
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
	 * @param impressionLogs
	 * @return total number of impressions.
	 */
	public static int calcImpressions (List<ImpressionLog> impressionLogs) {
		return impressionLogs.size();
	}
	
	/**
	 * 
	 * @param clickLogs
	 * @return total number of clicks.
	 */
	public static int calcClicks (List<ClickLog> clickLogs) {
		return clickLogs.size();
	}
	/**
	 * 
	 * @param clickLogs
	 * @return total number of unique clicks.
	 */
	public static int calcUniques (List<ClickLog> clickLogs) {
		HashSet<Long> ids = new HashSet<Long>();
		
		for(Log l : clickLogs) {
			ids.add(((ClickLog)l).getId());
		}
		
		return ids.size();
	}
	/**
	 * 
	 * @param serverLogs
	 * @param minPages less than that and bounce will be registered. Ignored if 0.
	 * @param minSeconds less than that and bounce will be registered. Ignored if 0.
	 * @return number of bounces based on defined criteria.
	 */
	public static int calcBounces (List<ServerLog> serverLogs, int minPages, int minSeconds) {
		int bounces = 0;
		
		for(Log l : serverLogs) {
			
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
	 * @param serverLogs
	 * @return total number of customer conversions.
	 */
	public static int calcConversions (List<ServerLog> serverLogs) {
		int conversions = 0;
		for(Log l : serverLogs) {
			if(((ServerLog)l).isConverted()) {
				conversions++;
			}
		}
		return conversions;
	}
	/**
	 * 
	 * @param clickLogs
	 * @return total cost of all clicks.
	 */
	public static float calcClickCost (List<ClickLog> clickLogs) {
		float cost = 0;
		for(Log l: clickLogs) {
			cost += ((ClickLog)l).getClickCost();
		}
		return cost;
	}
	/**
	 * 
	 * @param impressionLogs
	 * @return total cost of all impressions.
	 */
	public static float calcImprCost(List<ImpressionLog> impressionLogs) {
		float cost = 0;
		for(Log l : impressionLogs) {
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
		if(impressions <= 0){
			return 0;
		}else{
			return (float) clicks / impressions;
		}
	}
	/**
	 * 
	 * @param cost
	 * @param converted
	 * @return cost-per-acquisition:The average amount of money spent on an advertising campaign for each acquisition (conversion).
	 */
	public static float calcCPA (float cost, int converted) {
		if(converted <= 0){
			return 0;
		}else{
			return cost / converted;
		}
	}
	/**
	 * 
	 * @param cost
	 * @param clicks
	 * @return cost-per-click: The average amount of money spent on an advertising campaign for each click.
	 * 
	 */
	public static float calcCPC (float cost, int clicks) {
		if(clicks <= 0){
			return 0;
		}else{
			return cost / clicks;
		}
	}
	/**
	 * 
	 * @param cost
	 * @param impressions
	 * @return cost-per-thousand impressions: The average amount of money spent on an advertising campaign for every
	 * one thousand impressions.
	 */
	public static float calcCPM (float cost, int impressions) {
		if(impressions <= 0){
			return 0;
		}else{
			return (cost/impressions)*1000;
		}
	}
	/**
	 * 
	 * @param bounces
	 * @param clicks
	 * @return the average number of bounces per click.
	 */
	public static float calcBounceRate (int bounces, int clicks) {
		if(clicks <= 0){
			return 0;
		}else{
			return (float) bounces / clicks;
		}
	}
	
	public static ArrayList<ArrayList<Object>> calcImpressionsOverTime(List<ImpressionLog> impressionLogs){
		
		ArrayList<ArrayList<Object>> allImpressionsAndTimes = new ArrayList<ArrayList<Object>>();
		int numImpressions = 0;
		System.out.println(impressionLogs.size());
		for(ImpressionLog impressionLog : impressionLogs){
			numImpressions++;
			ArrayList<Object> impressionsAndTime = new ArrayList<Object>();
			if(impressionLogs.size()<100) {
				Date impressionDate = impressionLog.getDate();
				impressionsAndTime.add(impressionDate);
				impressionsAndTime.add(numImpressions);
				allImpressionsAndTimes.add(impressionsAndTime);
			}else if(100<=impressionLogs.size() && impressionLogs.size()<1000 && numImpressions%10==0) {
				Date impressionDate = impressionLog.getDate();
				impressionsAndTime.add(impressionDate);
				impressionsAndTime.add(numImpressions);
				allImpressionsAndTimes.add(impressionsAndTime);
			}else if(1000<=impressionLogs.size() && impressionLogs.size()<10000 && numImpressions%100==0) {
				Date impressionDate = impressionLog.getDate();
				impressionsAndTime.add(impressionDate);
				impressionsAndTime.add(numImpressions);
				allImpressionsAndTimes.add(impressionsAndTime);
			}else if(10000<=impressionLogs.size() && impressionLogs.size()<100000 && numImpressions%1000==0) {
				Date impressionDate = impressionLog.getDate();
				impressionsAndTime.add(impressionDate);
				impressionsAndTime.add(numImpressions);
				allImpressionsAndTimes.add(impressionsAndTime);
			}else if(100000<=impressionLogs.size() && impressionLogs.size()<1000000 && numImpressions%10000==0) {
				Date impressionDate = impressionLog.getDate();
				impressionsAndTime.add(impressionDate);
				impressionsAndTime.add(numImpressions);
				allImpressionsAndTimes.add(impressionsAndTime);
			}
		}
		
		return allImpressionsAndTimes;
	}
	
}
