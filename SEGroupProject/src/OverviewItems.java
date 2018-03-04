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
}
