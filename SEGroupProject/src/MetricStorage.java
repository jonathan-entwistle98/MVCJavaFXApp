
public class MetricStorage {

	private int[] impressions;
	private int[] clicks;
	private int[] uniques;
	private int[] bounces;
	private int[] conversions;
	private float totalCost;
	private float[] costs;
	private float[] ctr;
	private float[] cpa;
	private float[] cpc;
	private float[] cpm;
	private float[] bounceRate;
	
	public int[] getImpressions() {
		return impressions;
	}
	public void setImpressions(int[] impressions) {
		this.impressions = impressions;
	}
	public int[] getClicks() {
		return clicks;
	}
	public void setClicks(int[] clicks) {
		this.clicks = clicks;
	}
	public int[] getUniques() {
		return uniques;
	}
	public void setUniques(int[] uniques) {
		this.uniques = uniques;
	}
	public int[] getBounces() {
		return bounces;
	}
	public void setBounces(int[] bounces) {
		this.bounces = bounces;
	}
	public int[] getConversions() {
		return conversions;
	}
	public void setConversions(int[] conversions) {
		this.conversions = conversions;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	public float[] getCtr() {
		return ctr;
	}
	public void setCtr(float[] ctr) {
		this.ctr = ctr;
	}
	public float[] getCpa() {
		return cpa;
	}
	public void setCpa(float[] cpa) {
		this.cpa = cpa;
	}
	public float[] getCpc() {
		return cpc;
	}
	public void setCpc(float[] cpc) {
		this.cpc = cpc;
	}
	public float[] getCpm() {
		return cpm;
	}
	public void setCpm(float[] cpm) {
		this.cpm = cpm;
	}
	public float[] getBounceRate() {
		return bounceRate;
	}
	public void setBounceRate(float[] bounceRate) {
		this.bounceRate = bounceRate;
	}
	public float[] getCosts() {
		return costs;
	}
	public void setCosts(float[] costs) {
		this.costs = costs;
	}
	
}
