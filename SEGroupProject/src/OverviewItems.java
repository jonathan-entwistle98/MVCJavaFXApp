import java.util.ArrayList;

/**
 * Holds all essential calculated campaign overview information.
 *
 */
public class OverviewItems {

	private int impressions;
	private int clicks;
	private int uniques;
	private int bounces;
	private int conversions;
	private float totalCost;
	private float CTR;
	private float CPA;
	private float CPC;
	private float CPM;
	private float bounceRate;
	private long minDate;
	private long maxDate;
	public ArrayList<ArrayList<Object>> impressionsOverTime;
	
	public OverviewItems(int impressions, int clicks, int uniques, int conversions, float totalCost,
			float CTR, float CPA, float CPC, float CPM, long minDate, long maxDate) {
		this.impressions = impressions;
		this.clicks = clicks;
		this.uniques = uniques;
		this.conversions = conversions;
		this.totalCost = totalCost;
		this.CTR = CTR;
		this.CPA = CPA;
		this.CPC = CPC;
		this.CPM = CPM;
		this.setMinDate(minDate);
		this.setMaxDate(maxDate);
	}
	
	public int getImpressions() {
		return impressions;
	}
	public void setImpressions(int impressions) {
		this.impressions = impressions;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	public int getUniques() {
		return uniques;
	}
	public void setUniques(int uniques) {
		this.uniques = uniques;
	}
	public int getBounces() {
		return bounces;
	}
	public void setBounces(int bounces) {
		this.bounces = bounces;
	}
	public int getConversions() {
		return conversions;
	}
	public void setConversions(int conversions) {
		this.conversions = conversions;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public float getCTR() {
		return CTR;
	}
	public void setCTR(float cTR) {
		CTR = cTR;
	}
	public float getCPA() {
		return CPA;
	}
	public void setCPA(float cPA) {
		CPA = cPA;
	}
	public float getCPC() {
		return CPC;
	}
	public void setCPC(float cPC) {
		CPC = cPC;
	}
	public float getCPM() {
		return CPM;
	}
	public void setCPM(float cPM) {
		CPM = cPM;
	}
	public float getBounceRate() {
		return bounceRate;
	}
	public void setBounceRate(float bounceRate) {
		this.bounceRate = bounceRate;
	}

	public long getMinDate() {
		return minDate;
	}

	public void setMinDate(long minDate) {
		this.minDate = minDate;
	}

	public long getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(long maxDate) {
		this.maxDate = maxDate;
	}
}
