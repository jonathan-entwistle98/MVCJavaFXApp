
public class MetricStorage {

	private int[] impressions;
	private int[] clicks;
	private int[] uniques;
	private int[] bounces;
	private int[] conversions;
	private float totalCost;
	private float[] costs;
	private float[] ctr;
	private float[] ctrD;
	private float[] ctrW;
	private float[] cpa;
	private float[] cpaD;
	private float[] cpaW;
	private float[] cpc;
	private float[] cpcD;
	private float[] cpcW;
	private float[] cpm;
	private float[] cpmD;
	private float[] cpmW;
	private float[] bounceRate;
	private float[] bounceRateD;
	private float[] bounceRateW;
	
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
	public float[] getCtr(Granularity g) {
		switch (g) {
		case HOURLY:
			return ctr;
		case DAILY:
			return ctrD;
		case WEEKLY:
			return ctrW;
		default:
			break;
		}
		return ctr;
	}
	public void setCtr(float[] ctr, Granularity g) {
		switch (g) {
		case HOURLY:
			this.ctr = ctr;
		case DAILY:
			this.ctrD = ctr;
		case WEEKLY:
			this.ctrW = ctr;
		default:
			break;
		}
	}
	public float[] getCpa(Granularity g) {
		switch (g) {
		case HOURLY:
			return cpa;
		case DAILY:
			return cpaD;
		case WEEKLY:
			return cpaW;
		default:
			break;
		}
		return cpa;
	}
	public void setCpa(float[] cpa, Granularity g) {
		switch (g) {
		case HOURLY:
			this.cpa = ctr;
		case DAILY:
			this.cpaD = ctr;
		case WEEKLY:
			this.cpaW = ctr;
		default:
			break;
		}
	}
	public float[] getCpc(Granularity g) {
		switch (g) {
		case HOURLY:
			return cpc;
		case DAILY:
			return cpcD;
		case WEEKLY:
			return cpcW;
		default:
			break;
		}
		return cpc;
	}
	public void setCpc(float[] cpc, Granularity g) {
		switch (g) {
		case HOURLY:
			this.cpc = ctr;
		case DAILY:
			this.cpcD = ctr;
		case WEEKLY:
			this.cpcW = ctr;
		default:
			break;
		}
	}
	public float[] getCpm(Granularity g) {
		switch (g) {
		case HOURLY:
			return cpm;
		case DAILY:
			return cpmD;
		case WEEKLY:
			return cpmW;
		default:
			break;
		}
		return cpm;
	}
	public void setCpm(float[] cpm, Granularity g) {
		switch (g) {
		case HOURLY:
			this.cpm = ctr;
		case DAILY:
			this.cpmD = ctr;
		case WEEKLY:
			this.cpmW = ctr;
		default:
			break;
		}
	}
	public float[] getBounceRate(Granularity g) {
		switch (g) {
		case HOURLY:
			return bounceRate;
		case DAILY:
			return bounceRateD;
		case WEEKLY:
			return bounceRateW;
		default:
			break;
		}
		return bounceRate;
	}
	public void setBounceRate(float[] bounceRate, Granularity g) {
		switch (g) {
		case HOURLY:
			this.bounceRate = bounceRate;
		case DAILY:
			this.bounceRateD = bounceRate;
		case WEEKLY:
			this.bounceRateW = bounceRate;
		default:
			break;
		}
	}
	public float[] getCosts() {
		return costs;
	}
	public void setCosts(float[] costs) {
		this.costs = costs;
	}
	
}
